package koff.expense.service;

import koff.expense.model.dto.auth.AuthenticationResponse;
import koff.expense.model.form.auth.AuthenticationRequest;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
