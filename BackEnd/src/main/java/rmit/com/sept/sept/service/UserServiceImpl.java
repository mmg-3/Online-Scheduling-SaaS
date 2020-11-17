package rmit.com.sept.sept.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import rmit.com.sept.sept.Role;
import rmit.com.sept.sept.SeptApplication;
import rmit.com.sept.sept.User;
import rmit.com.sept.sept.repository.RoleRepository;
import rmit.com.sept.sept.repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

//import rmit.com.sept.sept.repository.CompanyRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Autowired
	public SeptApplication main;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
    JdbcTemplate template;
	 
	List<User> userList = new ArrayList<User>();
	
	private int sessionUserID;
	
	@Override
	public void saveUser(User user) {

		user.setPassword(encoder.encode(user.getPassword()));
		user.setStatus("VERIFIED");
		System.out.println(user.getCompanyName());
		if(user.getCompanyName().isEmpty()) {
			Role userRole = roleRepository.findByRole("SITE_USER");
			user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		}
		else {
			user.setIsCompany(true);
			Role userRole = roleRepository.findByRole("ADMIN_USER");
			user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
		}
		userRepository.save(user);
		
	}

	@Override
	public boolean isUserPresent(int id) {
		// TODO Auto-generated method stub
		if(userRepository.existsById(id)){
			return true;
		}		//return false;
		return false;
	}

	public int getSessionUserID() {
		return sessionUserID;
	}

	public void setSessionUserID(int sessionUserID) {
		this.sessionUserID = sessionUserID;
	}
	
	@Override
	public List<User> getUserDetails(int id) {
	
		String sql = "select firstname,lastname,email from user where user.user_id = '" + id + "'";
        RowMapper<User> rm = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User(resultSet.getString("firstname"),
                        resultSet.getString("lastname"),
                        resultSet.getString("email"));
                user.setName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setEmail(resultSet.getString("email"));
                System.out.println(user.getName());
                System.out.println(user.getLastName());
               userList.add(user);
                return user;
            }
        };

        return template.query(sql, rm);
    }
	

    @Override
    public int findIdLogin(String email){
    
        int intId = 0;
		try {
			String url = "jdbc:mysql://127.0.0.1:3306/sept?useLegacyDatetimeCode=false&serverTimezone=UTC&createDatabaseIfNotExist=true"; 
	        Connection conn = DriverManager.getConnection(url,"root",main.getPassword());
	        Statement st = conn.createStatement(); 
	        ResultSet rs;
	        
            rs = st.executeQuery("SELECT user_id FROM user WHERE user.email ='"+email+"'");
            while ( rs.next() ) {
                String id = rs.getString("user_id");
                intId=Integer.parseInt(id);  
                System.out.println(intId);
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
		
		return intId;
      
    }

    @Override
    public String findUserType(int id){

        String userType = "";
		try {
			String url = "jdbc:mysql://127.0.0.1:3306/sept?useLegacyDatetimeCode=false&serverTimezone=UTC&createDatabaseIfNotExist=true"; 
	        Connection conn = DriverManager.getConnection(url,"root",main.getPassword());
	        Statement st = conn.createStatement(); 
	        ResultSet rs;
	        
            rs = st.executeQuery("SELECT auth_role_id FROM auth_user_role WHERE auth_user_role.auth_user_id ='"+id+"'");
            while ( rs.next() ) {
                String type = rs.getString("auth_role_id");
                int userId =Integer.parseInt(type);  
                if(userId == 1 ){
                    userType = "ADMIN_USER";
                }
                if(userId == 2 ){
                    userType = "SITE_USER";
                }
                if(userId == 3 ) {
                	userType = "WORKER_USER";
                }
                System.out.println(userType);
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
		
		return userType;
      
    }

	public String findUserTypeByID(int id){
		for(User user : userList){
			if(user.getId()==id)
			{
				return user.getUserType();
			}
		}
		return " ";
	}


	@Override
	public int findByUsername(String username) {
		int intId = 0;
		try {
			String url = "jdbc:mysql://127.0.0.1:3306/sept?useLegacyDatetimeCode=false&serverTimezone=UTC&createDatabaseIfNotExist=true"; 
	        Connection conn = DriverManager.getConnection(url,"root",main.getPassword());
	        Statement st = conn.createStatement(); 
	        ResultSet rs;
	        
            rs = st.executeQuery("SELECT user_id FROM user WHERE email ='"+username+"'");
            while ( rs.next() ) {
                String id = rs.getString("user_id");
                intId=Integer.parseInt(id);  
                System.out.println(intId);
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
		
		return intId;
		
	}



	public UserRepository getUserRepository(){
		return this.userRepository;

	}

	@Override
	public List<User> getList() {
		return userList;
	}

	@Override
	public List<User> getRegisteredCompanyID() {
		String sql = "select user.company_id from user";
        RowMapper<User> rm = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User(resultSet.getInt("company_id"), 0);
                return user;
            }
        };

        return template.query(sql, rm);
	}

	@Override
	public List<User> getRegisteredWorkerID() {
		String sql = "select user.worker_id from user";
        RowMapper<User> rm = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User(0, resultSet.getInt("worker_id"));
                return user;
            }
        };

        return template.query(sql, rm);
	}

	@Override
	public String findIdName(String name) {
        String id = "";
		try {
			String url = "jdbc:mysql://127.0.0.1:3306/sept?useLegacyDatetimeCode=false&serverTimezone=UTC&createDatabaseIfNotExist=true"; 
	        Connection conn = DriverManager.getConnection(url,"root",main.getPassword());
	        Statement st = conn.createStatement(); 
	        ResultSet rs;
	        
            rs = st.executeQuery("SELECT user_id FROM user WHERE user.firstname ='"+name+"'");
            while ( rs.next() ) {
                 id = rs.getString("firstname");
                 
                System.out.println(id);
            }
            conn.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
		
		return id;
      
	}


	




}
