package com.dev.SocialMedia.auth;

import com.dev.SocialMedia.common.ApiResponse;
import com.dev.SocialMedia.exception.CustomException;
import com.dev.SocialMedia.user.User;
import com.dev.SocialMedia.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    public String generateRefreshToken(Long userId) {
        RefreshToken refreshToken = RefreshToken
                .builder()
                .user(userRepository.findById(userId).get())
                .expiryDate(LocalDateTime.now().plusDays(30))
                .token(UUID.randomUUID().toString())
                .build();
        refreshTokenRepository.save(refreshToken);
        return refreshToken.getToken();
    }

    public String verifyExpiration(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new CustomException("invalid token"));
        if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            refreshTokenRepository.delete(refreshToken);
            throw new CustomException("refresh token expired, please sign in to renew");
        }
        return refreshToken.getToken();
    }

    public void deleteToken(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException("user id not found"));
        RefreshToken refreshToken = refreshTokenRepository.findByUserId(userId)
                .orElseThrow(() -> new CustomException("refresh token not found"));
        refreshTokenRepository.delete(refreshToken);
    }
}
