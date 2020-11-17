package rmit.com.sept.sept.service;

import rmit.com.sept.sept.User;
import rmit.com.sept.sept.repository.UserRepository;

import java.util.List;

public interface UserService {
	
	public void saveUser(User user);
	
	public List<User> getUserDetails(int id);
	
	public boolean isUserPresent(int id);

	public UserRepository getUserRepository();

    public int findByUsername(String username);
    
    public int findIdLogin(String username);

    public String findIdName(String username);

	public String findUserTypeByID(int id);

    public String findUserType(int id);

    public List<User> getList();
//	void getDetailsForProfile(int id);
    
    public List<User> getRegisteredCompanyID();
    
    public List<User> getRegisteredWorkerID();

    

}
