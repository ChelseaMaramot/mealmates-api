package org.example.mealmatesapi.controller;


import jakarta.validation.Valid;
import org.example.mealmatesapi.exception.TokenRefreshException.TokenRefreshException;
import org.example.mealmatesapi.model.User;
import org.example.mealmatesapi.payload.request.TokenRefreshRequest;
import org.example.mealmatesapi.payload.response.TokenRefreshResponse;
import org.example.mealmatesapi.payload.response.UserResponse;
import org.example.mealmatesapi.repository.UserRepository;
import org.example.mealmatesapi.service.RefreshTokenService;
import org.example.mealmatesapi.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import org.example.mealmatesapi.model.RefreshToken;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    RefreshTokenService refreshTokenService;
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody User user) {
        System.out.println("Authenticating user...");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String accessToken = jwtUtils.generateToken(userDetails.getUsername());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);


        LinkedHashMap<String, String> tokens = new LinkedHashMap<>();
        tokens.put("access", accessToken);
        tokens.put("refresh", refreshToken.getToken());

        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("email", user.getEmail());
        response.put("tokens", tokens);

        return ResponseEntity.ok(response);
    }
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        System.out.println("Creating a new user");
        if (userRepository.existsByUsername(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username already taken");
        }
//        if (userRepository.existsByEmail(signupRequest.getEmail())) {
//            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
//        }


        // Create new user's account
        User newUser = new User(
                user.getUsername(),
                encoder.encode(user.getPassword()),
                user.getEmail()
        );
        userRepository.save(newUser);

        String accessToken = jwtUtils.generateToken(newUser.getUsername());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(newUser);

        UserResponse userResponse = new UserResponse(newUser.getId(), newUser.getUsername(), newUser.getEmail());

        LinkedHashMap<String, String> tokens = new LinkedHashMap<>();
        tokens.put("access", accessToken);
        tokens.put("refresh", refreshToken.getToken());

        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("id", userResponse.getId());
        response.put("username", userResponse.getUsername());
        response.put("email", userResponse.getEmail());
        response.put("tokens", tokens);

        return ResponseEntity.ok(response);
    }
    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateToken(user.getUsername());
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }
}