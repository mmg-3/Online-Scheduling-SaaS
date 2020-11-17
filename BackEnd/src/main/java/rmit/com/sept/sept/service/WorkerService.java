package rmit.com.sept.sept.service;

import java.sql.SQLException;
import java.util.List;

import rmit.com.sept.sept.Company;
import rmit.com.sept.sept.Worker;

public interface WorkerService {

	
	public void saveWorker(Worker worker);
	
	public List<Company> list() throws SQLException;
}
