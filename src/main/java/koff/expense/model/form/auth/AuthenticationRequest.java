package koff.expense.model.form.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthenticationRequest {

    @NotBlank(message = "{username.not.blank}")
    private String username;

    @NotBlank(message = "{password.not.blank}")
    private String password;
}
