package rmit.com.sept.sept;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
@EnableAutoConfiguration
@SpringBootApplication
public class SeptApplication {
	private static WebMvcConfig web;
	private static String password = "KG@7291979369";
	public static void main(String[] args) {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
		String encodedPassword = passwordEncoder.encode("password");

		try { 
			String url = "jdbc:mysql://127.0.0.1:3306/sept?useLegacyDatetimeCode=false&serverTimezone=UTC&createDatabaseIfNotExist=true"; 
	        Connection conn = DriverManager.getConnection(url,"root",password);
	        Statement st = conn.createStatement(); 
	        st.executeUpdate("INSERT IGNORE INTO role(role_id,role_desc,role_name)VALUES(3,'WORKER_USER','WORKER_USER')");

//	        st.executeUpdate("CREATE TABLE role(role_id INT(50) NOT NULL AUTO_INCREMENT,role_desc VARCHAR(50), role_name VARCHAR(50), CONSTRAINT role_pk PRIMARY KEY(role_id))");

	        st.executeUpdate("INSERT IGNORE INTO role(role_id,role_desc,role_name)VALUES(1,'ADMIN_USER','ADMIN_USER')");
	        st.executeUpdate("INSERT IGNORE INTO role(role_id,role_desc,role_name)VALUES(2,'SITE_USER','SITE_USER')");
	        st.executeUpdate("INSERT IGNORE INTO role(role_id,role_desc,role_name)VALUES(3,'WORKER_USER','WORKER_USER')");


	        conn.close();
		} catch (Exception e) { 
            System.err.println("Got an exception! "); 
            System.err.println(e.getMessage()); 
        }
		SpringApplication.run(SeptApplication.class, args);
	}
	
	public String getPassword() {
		return password;
	}

}
