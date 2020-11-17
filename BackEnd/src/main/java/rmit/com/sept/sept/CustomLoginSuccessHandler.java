package rmit.com.sept.sept;

import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	@Override
	protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException {

		String targetUrl = determineTargetUrl(authentication);
		System.out.println("here1");
		if (response.isCommitted()) {
			return;
		}
		RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
		redirectStrategy.sendRedirect(request, response, targetUrl);
	}
	
	protected String determineTargetUrl(Authentication authentication) {
		String url = "/login?error=true";
		System.out.println("here2");
		// Fetch the roles from Authentication object
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority a : authorities) {
			System.out.println(a.getAuthority());
			roles.add(a.getAuthority());
		}
		
		System.out.println(roles);

		// check user role and decide the redirect URL
		if (roles.contains("ADMIN_USER")) {
			url = "/admin";
		} 
		else if (roles.contains("SITE_USER")) {
			url = "/home";
		}
		
		return url;
    }
    
    protected String userType(Authentication authentication) {
		String userType = "";
		
		// Fetch the roles from Authentication object
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		List<String> roles = new ArrayList<String>();
		for (GrantedAuthority a : authorities) {
			System.out.println(a.getAuthority());
			roles.add(a.getAuthority());
		}
		
		System.out.println(roles);

		// check user role and decide the redirect URL
		if (roles.contains("ADMIN_USER")) {
			userType = "ADMIN_USER";
		} 
		else if (roles.contains("SITE_USER")) {
			userType = "SITE_USER";
		}
		
		return userType;
	}
}