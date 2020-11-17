package rmit.com.sept.sept.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rmit.com.sept.sept.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	
}
