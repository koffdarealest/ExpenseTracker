package koff.expense.config.exception;

import koff.expense.exception.NoAuthorityException;
import koff.expense.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.MessageSource;
import org.springframework.http.*;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@ControllerAdvice
@Log4j2
@AllArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(Exception ex) {
        String message = "Unexpected error occurred";
        String detailMessage = ex.getLocalizedMessage();
        int errorCode = 1;
        System.out.println("Unexpected error occurred");
        ErrorResponse error = new ErrorResponse(
                message,
                detailMessage,
                errorCode,
                ex
        );
        log.error(detailMessage, ex);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        String message = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
        String detailMessage = ex.getLocalizedMessage();
        int errorCode = 2;
        System.out.println("Error 2 handled");
        ErrorResponse error = new ErrorResponse(
                message,
                detailMessage,
                errorCode,
                ex
        );
        log.error(detailMessage, ex);
        return new ResponseEntity<>(error, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException exception,
            HttpHeaders headers, HttpStatusCode status, WebRequest request
    ) {
        String message = getMessageFromHttpRequestMethodNotSupportException(exception);
        String detailMessage = exception.getLocalizedMessage();
        int code = 3;
        System.out.println("Error 3 handled");
        ErrorResponse response = new ErrorResponse(
                message,
                detailMessage,
                code,
                exception);
        log.error(detailMessage, exception);
        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        String message = getMessageSource("error.notfound");
        String detailMessage = ex.getLocalizedMessage();
        int errorCode = 4;
        System.out.println("Error 4 handled");
        ErrorResponse error = new ErrorResponse(
                message,
                detailMessage,
                errorCode,
                ex
        );
        log.error(detailMessage, ex);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {
        String message = getMessageSource("error.method.argument.not.valid");
        String detailMessage = ex.getLocalizedMessage();
        int errorCode = 5;
        System.out.println("Error 5 handled");
        ErrorResponse error = new ErrorResponse(
                message,
                detailMessage,
                errorCode,
                ex
        );
        log.error(detailMessage, ex);
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler({NoAuthorityException.class})
    public ResponseEntity<Object> handleNoAuthorityException(NoAuthorityException ex) {
        String message = getMessageSource("error.no.authority");
        String detailMessage = ex.getLocalizedMessage();
        int errorCode = 6;
        System.out.println("Error 6 handled");
        ErrorResponse error = new ErrorResponse(
                message,
                detailMessage,
                errorCode,
                ex
        );
        log.error(detailMessage, ex);
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }



    private String getMessageFromHttpRequestMethodNotSupportException(
            HttpRequestMethodNotSupportedException exception
    ) {
        String message = exception.getMethod() + " method is not supported for this request. Support methods are";
        for (HttpMethod method : exception.getSupportedHttpMethods()) {
            message += " " + method;
        }
        return message;
    }

    private String getMessageSource(String key) {
        return messageSource.getMessage(key, null, Locale.getDefault());
    }


}
