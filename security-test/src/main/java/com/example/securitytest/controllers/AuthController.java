package com.example.securitytest.controllers;


import com.example.securitytest.auth.JwtTokenService;
import com.example.securitytest.dtos.auth.LoginRequestDto;
import com.example.securitytest.dtos.auth.LoginResponseDto;
import com.example.securitytest.dtos.auth.RegisterRequestDto;
import com.example.securitytest.models.User;
import com.example.securitytest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RequestMapping("/auth")
@RestController
@CrossOrigin
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;


    @Autowired
    private JwtTokenService jwtTokenService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginRequestDto loginRequestDto) {
        var userPass = new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword());
        var auth = this.authenticationManager.authenticate(userPass);

        String token = this.jwtTokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequestDto registerRequestDto, BindingResult bindingResult) {
        if (bindingResult.getErrorCount() > 0) return ResponseEntity.badRequest().body(bindingResult.getAllErrors());
        if (this.userService.getUserByUsername(registerRequestDto.getUsername()) != null)
            return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerRequestDto.getPassword());
        registerRequestDto.setPassword(encryptedPassword);
        var user = registerRequestDto.toEntity();
        user.setName("Some name");
        user.setEmail("yoshi_email@mail.com");
        this.userService.post(user);
        return ResponseEntity.status(201).build();
    }
}