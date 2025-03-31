package com.sample.sample1.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sample.sample1.Entity.User;
import com.sample.sample1.dto.UserResponse;

@Service
public interface UserService {

	User getUserById(Long userId);
	List<User> getAllUsers();  //read
	User saveUser(User user); //add
	UserResponse updateUser(Long userId, String name, String email) throws  Exception;
	List<User> serchUsersByName(String name);

}
