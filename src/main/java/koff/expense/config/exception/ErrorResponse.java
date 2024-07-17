package koff.expense.config.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;


@Data
@AllArgsConstructor
public class ErrorResponse {

    @NonNull
    private String message;

    @NonNull
    private String detailMessage;

    @NonNull
    private Integer errorCode;

    @NonNull
    private Object exception;
}
