package com.sample.sample1.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sample.sample1.Entity.User;
import com.sample.sample1.Service.UserService;
import com.sample.sample1.customexception.UserNotFoundException;
import com.sample.sample1.dto.ApiResponse;
import com.sample.sample1.dto.UserResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

@Tag(name = "User Management", description = "APIs for managing users")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Get all users", description = "Retrieves a list of all users from the database.")
    @GetMapping
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
    	 List<User> allUsers = userService.getAllUsers();
    	 return ResponseEntity.ok(new ApiResponse<>(true, allUsers, "UsersRetrived Successfully"));
    }
    
    @Operation(summary = "Create a new user", description = "Saves a new user to the database and returns the saved user object.")
    @PostMapping
    public ResponseEntity<ApiResponse<User>> saveUser(@Valid @RequestBody User user) {
         User saveUser = userService.saveUser(user);
         
         return ResponseEntity
        		.status(HttpStatus.CREATED)
        		.body(new ApiResponse<>(true, saveUser, "User Created Successfully"));
    }
    
    // Fetch user by ID
    @Operation(summary = "Get a user by ID", description = "Retrieves a specific user using their unique ID.")
    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse<User>> getUserById(@PathVariable Long userId) {
         User userById = userService.getUserById(userId);
         
         return ResponseEntity.ok(new ApiResponse<>(true, userById, "User Retrived Successfully"));
    }
    
    @Operation(summary = "Update user details", description = "Updates a user's name and/or email based on their ID.")
    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(
    				@PathVariable("userId") Long id, 
    				@RequestParam(required = false) String name, 
    				@RequestParam(required = false) String email) throws Exception {
    	UserResponse reponse = userService.updateUser(id, name, email);
        return ResponseEntity.ok(reponse);
    }
    
    @Operation(summary = "Search users by name", description = "Finds users whose names contain the specified query (case-insensitive).")
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<User>>> searchUser(@RequestParam String query){
    	List<User> user = userService.serchUsersByName(query);
    	return ResponseEntity.ok(new ApiResponse<>(true, user, "user found successfully"));
    }
    
    
    
    
    @Operation(summary = "Delete a user", description = "Deletes a user by ID and returns a confirmation message.")
    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
            return ResponseEntity.ok(new ApiResponse<>(true, "User deleted with ID: " + userId + " successfully."));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponse<>(false, "User not found with ID: " + userId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse<>(false, "An error occurred while deleting the user."));
        }
    }
    
}
