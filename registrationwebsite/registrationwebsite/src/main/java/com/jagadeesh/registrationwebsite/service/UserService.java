package com.jagadeesh.registrationwebsite.service;

import com.jagadeesh.registrationwebsite.entity.User;
import com.jagadeesh.registrationwebsite.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface UserService {

    User findUserById(Long userId);

    User findUserByUserEmailAndUserPassword(String email, String password);

    void saveUser(User user);

}
