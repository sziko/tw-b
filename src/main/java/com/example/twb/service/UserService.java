package com.example.twb.service;

import com.example.twb.dto.UserDTO;
import com.example.twb.entity.User;
import com.example.twb.repository.UserRepository;
import com.example.twb.security.Security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean registerUser(User user) {
        if (!isUsernameValid(user.getUsername())) {
            user.setPassword(Security.hash(user.getPassword()));
            userRepository.save(user);

            return true;
        }

        return false;
    }

    public boolean loginUser(UserDTO userDTO) {
        if (isUsernameValid(userDTO.getUsername())) {
            return isPasswordValid(userDTO);
        }

        return false;
    }

    private boolean isUsernameValid(String username) {
        return userRepository.findByUsername(username) != null;
    }

    // finds user by username and checks if password matches
    private boolean isPasswordValid(UserDTO userDTO) {
        return userRepository.findByUsername(userDTO.getUsername())
                .getPassword().equals(Security.hash(userDTO.getPassword()));
    }
}
