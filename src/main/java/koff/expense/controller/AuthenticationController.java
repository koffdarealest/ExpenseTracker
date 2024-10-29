package koff.expense.controller;

import jakarta.validation.constraints.NotBlank;
import koff.expense.model.dto.auth.AuthenticationResponse;
import koff.expense.model.form.auth.AuthenticationRequest;
import koff.expense.service.AuthenticationService;
import koff.expense.service.JwtService;
import koff.expense.service.RefreshTokenService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
@Log4j2
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public AuthenticationResponse login(
           @Valid @RequestBody AuthenticationRequest request
    ) {
        return authenticationService.authenticate(request);
    }

    @PostMapping("/refresh")
    public AuthenticationResponse refresh(
            @NotBlank @RequestBody String refreshToken
    ) {
        return refreshTokenService.refreshToken(refreshToken);
    }
}
