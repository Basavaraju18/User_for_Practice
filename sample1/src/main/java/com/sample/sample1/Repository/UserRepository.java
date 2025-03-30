package com.sample.sample1.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.sample1.Entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query methods (if needed)
	Optional<User> findByEmail(String email);
	
	List<User> findByNameContainingIgnoreCase(String name);
	
	
}
