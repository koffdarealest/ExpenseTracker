package koff.expense.service.implement;

import koff.expense.exception.ResourceNotFoundException;
import koff.expense.model.auth.UserAuth;
import koff.expense.model.dto.auth.AuthenticationResponse;
import koff.expense.model.form.auth.AuthenticationRequest;
import koff.expense.repository.IUserRepository;
import koff.expense.service.AuthenticationService;
import koff.expense.service.JwtService;
import koff.expense.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final IUserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        UserAuth userAuth = new UserAuth(user);
        String accessToken = jwtService.generateToken(userAuth);
        String refreshToken = jwtService.generateRefreshToken();
        refreshTokenService.saveRefreshToken(refreshToken, user);
        return new AuthenticationResponse(accessToken, refreshToken);
    }

}
