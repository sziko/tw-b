package com.example.twb.service;

import com.example.twb.dto.UserDTO;
import com.example.twb.entity.User;
import com.example.twb.repository.UserRepository;
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
        if(isUsernameValid(user.getUsername())) {
            this.userRepository.save(user);
            return true;
        }

        return false;
    }

    public boolean loginUser(UserDTO userDTO) {

        return true;
    }

    private boolean isUsernameValid(String username) {
        return this.userRepository.findByUsername(username) == null;
    }
}
