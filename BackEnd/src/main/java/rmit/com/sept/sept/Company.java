package rmit.com.sept.sept;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "company")
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "company_id")
	public int company_id;


	@NotNull(message="Company name is compulsory")
	@Column(name = "company_name")
	public String company_name;

	@NotNull(message="Service name is compulsory")
	@Column(name = "service_name")
	public String service_name;
	
	@Column(name = "number")
	public String number;
	
	public Company(int company_id,String company_name, String service_name) {
		this.company_id = company_id;
		this.company_name = company_name;
		this.service_name = service_name;
	}
	
	public Company() {
		
	}
	
	public Company(String company_name) {
		this.company_name = company_name;
	}
	
	public String getCompanyName() {
		return company_name;
	}


}