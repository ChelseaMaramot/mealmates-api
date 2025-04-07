package org.example.mealmatesapi.controller;

import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.example.mealmatesapi.dto.UserDTO;
import org.example.mealmatesapi.exception.TokenRefreshException.TokenRefreshException;
import org.example.mealmatesapi.model.User;
import org.example.mealmatesapi.payload.request.TokenRefreshRequest;
import org.example.mealmatesapi.service.RefreshTokenService;
import org.example.mealmatesapi.service.UserService;
import org.example.mealmatesapi.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.LinkedHashMap;

@RestController
@RequestMapping("api/user")
public class UserController {
    private UserService userService;
    private JwtUtils jwtUtils;

    @Autowired
    private RefreshTokenService refreshTokenService;

    // get user by id
    @GetMapping("/api/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    // create user
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        System.out.println("-----------CREATING USER IN CONTROLLER--------");
        UserDTO newUser = userService.createUser(userDTO);
        return ResponseEntity.ok(newUser);
    }

    // delete user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

//    @PostMapping("/refresh")
//    public String refreshToken(@RequestBody String refreshToken) {
//        System.out.println("Refreshing token...");
//        if (jwtUtils.validateJwtToken(refreshToken)) {
//            String username = jwtUtils.getUsernameFromToken(refreshToken);
//            String newAccessToken = jwtUtils.generateToken(username);
//            return "New Access Token: " + newAccessToken;
//        } else {
//            return "Invalid Refresh Token!";
//        }
//    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser(@RequestBody TokenRefreshRequest request) {
        String refreshToken = request.getRefreshToken();

        System.out.println("logging out");

        return refreshTokenService.findByToken(refreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(token -> {
                    // Delete the refresh token from the database
                    System.out.println("THIS TOKEN");
                    System.out.println(token);
                    refreshTokenService.deleteByUserId(token.getUser().getId());
                    return ResponseEntity.noContent().build();
                })
                .orElseThrow(() -> new TokenRefreshException(refreshToken,
                        "Refresh token is not in database!"));
    }
}


