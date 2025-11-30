package com.example.dps_backend.service;

import com.example.dps_backend.model.Users;
import com.example.dps_backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Users createUser(Users user) {
        return userRepository.save(user);
    }

    public Optional<Users> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public Users getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Users updateUser(Long id, Users user) {
        Optional<Users> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            user.setId(id);
            return userRepository.save(user);
        }
        return null;
    }

    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

