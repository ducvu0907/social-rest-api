package com.dev.SocialMedia.auth;

import com.dev.SocialMedia.common.ApiResponse;
import com.dev.SocialMedia.common.AuthUtil;
import com.dev.SocialMedia.config.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;
    private final AuthUtil authUtil;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@Valid @RequestBody TokenRefreshRequest request) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String refreshToken = refreshTokenService.verifyExpiration(request.getRefreshToken());
        String accessToken = jwtService.generateToken(userDetails);
        var authResponse = new AuthResponse();
        authResponse.setRefreshToken(refreshToken);
        authResponse.setAccessToken(accessToken);
        return ResponseEntity.ok(authResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        Long userId = authUtil.getCurrentUserId();
        refreshTokenService.deleteToken(userId);
        return ResponseEntity.ok(Collections.singletonMap("message", "logout successfully"));
    }

}
