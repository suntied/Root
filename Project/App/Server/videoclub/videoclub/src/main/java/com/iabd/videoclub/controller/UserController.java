package com.iabd.videoclub.controller;

import com.iabd.videoclub.exception.ResourceNotFoundException;
import com.iabd.videoclub.models.User;
import com.iabd.videoclub.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getuserById(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + userId));
        return ResponseEntity.ok().body(user);
    }
    @PostMapping("/users")
    public User createUser(@ Valid @RequestBody User user) {
        return userRepository.save(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long userId,
                                                   @Valid @RequestBody User userDetails) throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + userId));

        user.setEmail(userDetails.getEmail());
        final User updateduser = userRepository.save(user);
        return ResponseEntity.ok(updateduser);
    }

    @DeleteMapping("/users/{id}")
    public Map<String, Boolean> deleteuser(@PathVariable(value = "id") Long userId)
            throws ResourceNotFoundException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("user not found for this id :: " + userId));

        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
