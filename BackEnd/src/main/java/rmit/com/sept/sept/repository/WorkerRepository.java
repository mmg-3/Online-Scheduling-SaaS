package rmit.com.sept.sept.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rmit.com.sept.sept.Worker;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {

}
