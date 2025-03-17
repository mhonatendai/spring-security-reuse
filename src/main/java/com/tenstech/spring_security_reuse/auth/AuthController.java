package com.tenstech.spring_security_reuse.auth;

import com.tenstech.spring_security_reuse.user.AuthService;
import com.tenstech.spring_security_reuse.user.JpaUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@Validated
@Slf4j
public class AuthController {


    private final AuthService authService;

    private final AuthenticationManager authenticationManager;

    private final JpaUserDetailsService jpaUserDetailsService;

    public AuthController(AuthService authService, AuthenticationManager authenticationManager, JpaUserDetailsService jpaUserDetailsService) {
        this.authService = authService;
        this.authenticationManager = authenticationManager;
        this.jpaUserDetailsService = jpaUserDetailsService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthDTO.UserRequest userRequest) throws IllegalAccessException {
        Authentication authentication =
                authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(
                                userRequest.getUsername(),
                                userRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = authService.generateToken(authentication);

        AuthDTO.Response response = new AuthDTO.Response("User logged in successfully", token);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody AuthDTO.UserRequest userRequest) throws IllegalAccessException {

        return ResponseEntity.ok(jpaUserDetailsService.createUser(userRequest));
    }
}