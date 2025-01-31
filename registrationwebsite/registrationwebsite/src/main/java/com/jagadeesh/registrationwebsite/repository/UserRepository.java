package com.jagadeesh.registrationwebsite.repository;

import com.jagadeesh.registrationwebsite.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUserEmail(String email);
    User findUserByUserEmailAndUserPassword(String email, String password);
}
