package rmit.com.sept.sept;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private CustomLoginSuccessHandler sucessHandler;

	@Autowired
	private DataSource dataSource;

	@Value("${spring.queries.users-query}")
	private String usersQuery;

	@Value("${spring.queries.roles-query}")
	private String rolesQuery;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	
		auth.jdbcAuthentication().usersByUsernameQuery(usersQuery).authoritiesByUsernameQuery(rolesQuery)
				.dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable().authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/register").permitAll()
                .antMatchers("/registerWorker").permitAll()
                .antMatchers("/bookings").permitAll()
                .antMatchers("/createUser").permitAll()
                .antMatchers("/getUserBooking").permitAll()
                .antMatchers("/getPastBooking").permitAll()
                .antMatchers("/getWorkerBooking").permitAll()
                .antMatchers("/deleteBooking/{id}").permitAll()
                .antMatchers("/users/{id}").permitAll()
                .antMatchers("/acceptWorker/{id}").permitAll()
                .antMatchers("/editUser/{id}").permitAll()
                .antMatchers("/createBooking").permitAll()
                .antMatchers("/createWorker").permitAll()
                .antMatchers("/loginUser").permitAll()
				.antMatchers("/logoutUser").permitAll()
                .antMatchers("/profile").permitAll()                
				.antMatchers(HttpMethod.GET,"/bookings").permitAll()
				.antMatchers(HttpMethod.DELETE,"/booking/**").permitAll()
				.antMatchers("/home/**").hasAnyAuthority("SUPER_USER", "ADMIN_USER", "SITE_USER")
				.antMatchers("/admin/**").hasAnyAuthority("SUPER_USER","ADMIN_USER")
				.anyRequest().authenticated()
				.and()
                .httpBasic()
				.and()
				.csrf().disable().formLogin().permitAll()
				.loginPage("/login")			
				.failureUrl("/login?error=true")
				.successHandler(sucessHandler)			
				.usernameParameter("email")
				.passwordParameter("password")
				.and()
                .httpBasic()
				.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/").and()
				.exceptionHandling()				
				.accessDeniedPage("/access-denied");
		  		
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**") ;
	}

}