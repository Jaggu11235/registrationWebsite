package com.jagadeesh.registrationwebsite.service;

import com.jagadeesh.registrationwebsite.entity.User;
import com.jagadeesh.registrationwebsite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not found"));
    }

    @Override
    public User findUserByUserEmailAndUserPassword(String email, String password) {
        User userByUserEmailAndUserPassword = userRepository.findUserByUserEmailAndUserPassword(email, password);
        if(userByUserEmailAndUserPassword != null) {
            return userByUserEmailAndUserPassword;
        } else {
            throw new RuntimeException("User with Email not found");
        }
    }

    @Override
    public void saveUser(User user) {
        User userByUserEmail = userRepository.findUserByUserEmail(user.getUserEmail());
        if(userByUserEmail != null) {
            throw new RuntimeException("Email already registered");
        } else {
            userRepository.save(user);
            emailService.sendEmail(user.getUserEmail(), "Account Creation", "Successfully registered to portal");
        }
    }
}
