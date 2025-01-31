package com.jagadeesh.registrationwebsite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("jagadeesh.11235@gmail.com");  // Your email
        message.setTo(to);  // Recipient email
        message.setSubject(subject);  // Email subject
        message.setText(text);  // Email body text

        javaMailSender.send(message);  // Send the email
    }
}
