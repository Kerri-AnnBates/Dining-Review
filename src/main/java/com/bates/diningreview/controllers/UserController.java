package com.bates.diningreview.controllers;

import com.bates.diningreview.models.User;
import com.bates.diningreview.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Register a user
    @PostMapping("")
    public User registerUser(@RequestBody User newuser) {
        return userRepository.save(newuser);
    }

    // Update user profile
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userOpt = userRepository.findById(id);

        if (userOpt.isEmpty()) {
            return null;
        }

        User userToUpdate = userOpt.get();

        if (user.getInterestInDiaryAllergy() != null) {
            userToUpdate.setInterestInDiaryAllergy(user.getInterestInDiaryAllergy());
        }

        if (user.getInterestInEggAllergy() != null) {
            userToUpdate.setInterestInEggAllergy(user.getInterestInEggAllergy());
        }

        if (user.getInterestInPeanutAllergy() != null) {
            userToUpdate.setInterestInPeanutAllergy(user.getInterestInPeanutAllergy());
        }

        if (user.getCity() != null) {
            userToUpdate.setCity(userToUpdate.getCity());
        }

        if (user.getState() != null) {
            userToUpdate.setState(user.getState());
        }

        if (user.getZipcode() != null) {
            userToUpdate.setZipcode(user.getZipcode());
        }

        return userRepository.save(userToUpdate);
    }

    // Get user by username
    @GetMapping("/{name}")
    public User getUserByUsername(@PathVariable String name) {
        return userRepository.findByUsername(name);
    }
}
