package com.sample.sample1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sample.sample1.Entity.User;
import com.sample.sample1.Service.UserService;
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
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    
    @PostMapping
    public User saveUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }
    
    
    // Fetch user by ID
    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }
    
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(
    				@PathVariable("userId") Long id, 
    				@RequestParam(required = false) String name, 
    				@RequestParam(required = false) String email) throws Exception {
    	UserResponse reponse = userService.updateUser(id, name, email);
        return ResponseEntity.ok(reponse);
    }
    
}
