package rmit.com.sept.sept;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "worker")
public class Worker {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "worker_id")
	public int worker_id;


	@NotNull(message="Worker name is compulsory")
	@Column(name = "worker_name")
	public String worker_name;
	
	
	
	@Column(name = "company_name")
	public String company_name;
	
	@NotNull(message="Contact Number is compulsory")
	@Column(name = "number")
	public String number;
	
	
	@Column(name = "status")
	public boolean status;
	
	@NotNull(message="Email is compulsory")
	@Column(name = "email")
	private String email;

	@NotNull(message="Password is compulsory")
	@Length(min=5, message="Password should be at least 5 characters")
	@Column(name = "password")
	private String password;
	
	
	public Worker() {
		
	}
	public Worker(int worker_id,String worker_name,String company_name, String number,boolean status,String email,String password) {
		this.worker_id = worker_id;
		this.worker_name = worker_name;
		this.company_name = company_name;
		this.number = number;
		this.status = status;
		this.email = email;
		this.password = password;
		
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setCompanyName(String company_name) {
		this.company_name = company_name;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	public String getNumber() {
		return number;
	}
	
	public String getCompanyName() {
		return company_name;
	}
	
	public String getWorkerName() {
		return worker_name;
	}

	public void setWorkerName(String worker_name) {
		this.worker_name = worker_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}




}
