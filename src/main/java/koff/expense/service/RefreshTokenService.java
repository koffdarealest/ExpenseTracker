package koff.expense.service;

import koff.expense.model.dto.auth.AuthenticationResponse;
import koff.expense.model.entity.User;

public interface RefreshTokenService {

    void saveRefreshToken(String token, User user);

    AuthenticationResponse refreshToken(String refreshToken);
}
