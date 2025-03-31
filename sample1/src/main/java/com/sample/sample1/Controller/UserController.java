package com.sample.sample1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sample.sample1.Entity.User;
import com.sample.sample1.Service.UserService;
import com.sample.sample1.dto.ApiResponse;
import com.sample.sample1.dto.UserResponse;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        
    	 List<User> allUsers = userService.getAllUsers();
    	 return ResponseEntity.ok(new ApiResponse<>(true, allUsers, "UsersRetrived Successfully"));
    }
    
    @PostMapping
    public ResponseEntity<ApiResponse<User>> saveUser(@Valid @RequestBody User user) {
         User saveUser = userService.saveUser(user);
         
         return ResponseEntity
        		.status(HttpStatus.CREATED)
        		.body(new ApiResponse<>(true, saveUser, "User Created Successfully"));
    }
    
    
    // Fetch user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long userId) {
         User userById = userService.getUserById(userId);
         
         return ResponseEntity.ok(new ApiResponse<>(true, userById, "User Retrived Successfully"));
    }
    
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(
    				@PathVariable("userId") Long id, 
    				@RequestParam(required = false) String name, 
    				@RequestParam(required = false) String email) throws Exception {
    	UserResponse reponse = userService.updateUser(id, name, email);
        return ResponseEntity.ok(reponse);
    }
    
    //find User name by name.
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<User>>> serchUser(@RequestParam String query){
    	List<User> user = userService.serchUsersByName(query);
    	return ResponseEntity.ok(new ApiResponse<>(true, user, "user found successfully"));
    }
    
}
