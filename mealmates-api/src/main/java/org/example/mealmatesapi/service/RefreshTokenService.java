package org.example.mealmatesapi.service;

import jakarta.transaction.Transactional;
import org.example.mealmatesapi.exception.TokenRefreshException.TokenRefreshException;
import org.example.mealmatesapi.model.RefreshToken;
import org.example.mealmatesapi.model.User;
import org.example.mealmatesapi.repository.RefreshTokenRepository;
import org.example.mealmatesapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {

    @Value("${jwt.refreshExpiration}")
    private Long refreshTokenDurationMs;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = new RefreshToken();

        System.out.println(user.getUsername());
        System.out.println(user.getId());

        System.out.println("Setting user");
        refreshToken.setUser(user);
        System.out.println("Setting expiry");
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        System.out.println("Setting id");
        refreshToken.setToken(UUID.randomUUID().toString());


        refreshToken = refreshTokenRepository.save(refreshToken);
        System.out.println("token saved!");
        System.out.println(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) {

        System.out.println("VERIFYING EXPIRATION");
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    @Transactional
    public int deleteByUserId(Long userId) {
        return refreshTokenRepository.deleteByUser(userRepository.findById(userId).get());
    }
}