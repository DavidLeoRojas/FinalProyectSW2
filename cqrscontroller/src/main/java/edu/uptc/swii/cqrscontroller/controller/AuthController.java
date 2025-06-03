package edu.uptc.swii.cqrscontroller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import edu.uptc.swii.cqrscontroller.model.User;
import edu.uptc.swii.cqrscontroller.service.AuthService;
import edu.uptc.swii.cqrscontroller.utils.JwtUtil;

@CrossOrigin(origins = "*")
@RestController
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User authenticated = authService.authenticate(user.getUsername(), user.getPassword());
        if (authenticated != null) {
            return JwtUtil.generateToken(user.getUsername());
        } else {
            return "Credenciales inválidas";
        }
    }
}