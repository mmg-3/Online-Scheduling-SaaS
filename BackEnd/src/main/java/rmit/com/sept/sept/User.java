package rmit.com.sept.sept;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

//import com.webencyclop.demo.model.Role;
//import com.sun.istack.NotNull;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotBlank;
//

@Entity
//@IdClass(CompositeKey.class)
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;

	@NotNull(message="First name is compulsory")
	@Column(name = "firstname")
	private String name;

	@NotNull(message="Last name is compulsory")
	@Column(name = "lastname")
	private String lastName;

	@NotNull(message="Email is compulsory")
//	@Email(message = "Email is invalid")
	@Column(name = "email")
	private String email;

	@NotNull(message="Password is compulsory")
	@Length(min=5, message="Password should be at least 5 characters")
	@Column(name = "password")
	private String password;

	@Column(name = "isCompany")
	private boolean isCompany;
	
	@Column(name = "isWorker")
	private boolean isWorker;
	
//	@Column(name = "workerStatus")
//	private boolean workerStatus;
	
	@Column(name = "status")
	private String status;
	
//	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "company_id")
	public int company_id;

//	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "worker_id")
	public int worker_id;

//	@NotNull(message="Company name is compulsory")
	@Column(name = "company_name")
	public String company_name;

//	@NotNull(message="Service name is compulsory")
	@Column(name = "service_name")
	public String service_name;
	
	@Column(name = "number")
	public String number;
	
	public String userType;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "auth_user_role", joinColumns = @JoinColumn(name = "auth_user_id"), inverseJoinColumns = @JoinColumn(name = "auth_role_id"))
	private Set<Role> roles;
	
	public User(String firstname, String lastname, String email) {
		this.name = firstname;
		this.lastName = lastname;
		this.email = email;
    }
    
    public User(String email) {
		this.email = email;
    }
    
    public User(int company_id, int worker_id) {
 		this.company_id = company_id;
 		this.worker_id = worker_id;
     }
     
     
    public User(String email, String userType ) {
    	this.email = email;
    	this.userType = userType;
    }
    
    
	
	public User() {
		
	}

	public int getId() {
		return id;
	}

	public boolean getIsCompany() {
		return isCompany;
	}
	
	public void setIsCompany(boolean isCompany) {
		this.isCompany = isCompany;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public String getCompanyName() {
		return company_name;
	}
	
	public String getServiceName() {
		return service_name;
	}

	public String getNumber() {
		return number;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	public void setCompanyName(String company_name) {
		this.company_name = company_name;
	}
	public void setServiceName(String service_name) {
		this.service_name = service_name;
	}
	public void setNumber(String number) {
		this.number = number;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	
	public boolean getWorker() {
		return isWorker;
	}
	
	public void setWorker(boolean worker) {
		this.isWorker = worker;
	}
	
	public void setCompanyID(int companyID) {
		this.company_id = companyID;
	}
	
	public void setWorkerID(int workerID) {
		this.worker_id = workerID;
	}
	
	
//	public void setWorkerStatus(boolean accepted) {
//		this.workerStatus = accepted;
//	}
//	
//	public boolean getWorkerStatus() {
//		return workerStatus;
//	}

	public void setUserType(String userType){
		this.userType = userType;
	}

	public String getUserType(){
		return this.userType;
	}
	public String toString() { 
		
	      return "User [ name: " + email+", password: " + password + " , :user_type :admin]"; 
	 }  
	

}