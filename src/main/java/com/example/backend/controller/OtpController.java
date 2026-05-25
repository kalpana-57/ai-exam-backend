package com.example.backend.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/otp")
@CrossOrigin(origins = "*")
public class OtpController {

    @Autowired
    private JavaMailSender mailSender;

    private Map<String, String> otpStorage = new HashMap<>();

    @GetMapping("/send")
    public String sendOtpGet(@RequestParam String email) {
        return sendOtp(email);
    }

    @PostMapping("/send")
    public String sendOtp(@RequestParam String email) {
        Random random = new Random();
        String otp = String.valueOf(100000 + random.nextInt(900000));

        otpStorage.put(email, otp);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Interview Exam OTP Verification");
        message.setText("Your OTP is: " + otp);

        mailSender.send(message);

        return "OTP Sent Successfully";
    }

    @GetMapping("/verify")
    public String verifyOtpGet(
            @RequestParam String email,
            @RequestParam String otp
    ) {
        return verifyOtp(email, otp);
    }

    @PostMapping("/verify")
    public String verifyOtp(
            @RequestParam String email,
            @RequestParam String otp
    ) {
        String storedOtp = otpStorage.get(email);

        if (storedOtp != null && storedOtp.equals(otp)) {
            return "OTP Verified";
        }

        return "Invalid OTP";
    }
}