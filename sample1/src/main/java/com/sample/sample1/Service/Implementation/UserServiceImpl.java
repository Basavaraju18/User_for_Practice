package com.sample.sample1.Service.Implementation;

import org.hibernate.validator.internal.util.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.sample.sample1.Entity.User;
import com.sample.sample1.Repository.UserRepository;
import com.sample.sample1.Service.UserService;
import com.sample.sample1.customexception.UserNotFoundException;
import com.sample.sample1.dto.UserResponse;

import org.slf4j.Logger;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Validated
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository; // Inject Repository

    
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(UserServiceImpl.class);
    
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserById(Long userId) {
		
//		if(userRepository.findById(userId))
//		return userRepository.findById(userId)
//				.orElseThrow( () -> new RuntimeException("user not found with the Id"+ userId));
		  Optional<User> userOptional = userRepository.findById(userId);

		    if (userOptional.isPresent()) {
		        return userOptional.get();
		    } else {
		        throw new UserNotFoundException("User not found with ID: " + userId);
		    }
			
		
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}
	
	@Override
	public UserResponse updateUser(Long id, String name, String email) throws IllegalArgumentException {
		List<String> logMessages = new ArrayList<>();
		
		logMessages.add("Updating user with the Id: "+ id);
		
		logger.info("Updateing user with Id : {}", id);
		
        User user = userRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("User not found"));
        		  .orElseThrow(() -> {
        			String errorMsg = "User not found";
        			logMessages.add(errorMsg);
        			logger.error(errorMsg);
        			return new RuntimeException(errorMsg);
        		  });
        
        if (name != null && !name.isBlank()) {
        	if(name.length() < 3) {
//        		logger.warn("Invalid name provided for user ID {}: {}", id, name);
        		String warningsMsg = "Invalid name probvided for user ID "+ id + ": "+ name;
        		logMessages.add(warningsMsg);
        		logger.warn(warningsMsg);
        		throw new IllegalArgumentException("Name must be at least 3 characters long.");
        	}
            user.setName(name);
        }
        if (email != null && !email.isBlank()) {
        	//Check if the new email is already taken by another user.
        	Optional<User> existingUser = userRepository.findByEmail(email);
        	
        	if(existingUser.isPresent() && !existingUser.get().getId().equals(id)) {
//        		logger.warn("Dupicate Email attempt : {}", email);
        		String warningMsg = "Duplicate email attemp: "+ email;
        		logMessages.add(warningMsg);
        		logger.warn(warningMsg);
        		
        		throw new IllegalArgumentException("Email is already in use.Please choose another one email");
        		
        	}
            user.setEmail(email);
        }

        User savedUser =  userRepository.save(user);
        logMessages.add("Usser wiith ID "+ id +" updated successfully.");
        logger.info("User with ID {} updated successfully", id);
        
        return new UserResponse(savedUser, logMessages);
    }
}


