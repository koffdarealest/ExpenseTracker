package koff.expense.service;

import io.jsonwebtoken.Claims;
import koff.expense.model.auth.UserAuth;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    String extractUsername(String token);

    <T> T extractClaims(String token, Function<Claims, T> claimsResolver);

    String generateToken(UserAuth userAuth);

    String generateRefreshToken();

    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);

    boolean isValidToken(String token, UserDetails userDetails);

    String getIdFromToken();
}
