package koff.expense.service.implement;

import koff.expense.exception.ResourceNotFoundException;
import koff.expense.model.auth.UserAuth;
import koff.expense.model.dto.auth.AuthenticationResponse;
import koff.expense.model.entity.RefreshToken;
import koff.expense.model.entity.User;
import koff.expense.repository.IRefreshTokenRepository;
import koff.expense.service.JwtService;
import koff.expense.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    @Value("${jwt.refresh-token.expiration}")
    private long REFRESH_TOKEN_EXPIRATION;

    private final IRefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;

    @Override
    public void saveRefreshToken(String token, User user) {
        refreshTokenRepository.insert(
                new RefreshToken(
                        null,
                        user,
                        token,
                        false,
                        new Date(System.currentTimeMillis()),
                        new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION)
                )
        );
    }

    @Override
    public AuthenticationResponse refreshToken(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new ResourceNotFoundException("Refresh token not found"));
        if (!isValidToken(refreshToken)) {
            throw new ResourceNotFoundException("Invalid refresh token");
        }
        UserAuth userAuth = new UserAuth(refreshToken.getUser());   // Generate new access token
        String accessToken = jwtService.generateToken(userAuth);
        String newRefreshToken = jwtService.generateRefreshToken();
        refreshToken.setToken(newRefreshToken);                     // Update refresh token
        refreshToken.setCreatedDate(new Date(System.currentTimeMillis()));
        refreshToken.setExpiryDate(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION));
        refreshTokenRepository.save(refreshToken);
        return new AuthenticationResponse(accessToken, newRefreshToken);
    }

    private boolean isValidToken(RefreshToken refreshToken) {
        return !refreshToken.getIsBlacklisted() && refreshToken.getExpiryDate().after(new Date());
    }
}
