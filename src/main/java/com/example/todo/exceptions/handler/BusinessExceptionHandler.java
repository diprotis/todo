package com.example.todo.exceptions.handler;

import com.example.todo.exceptions.BusinessException;
import com.example.todo.model.FieldError;
import com.example.todo.response.ErrorResponse;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
@NoArgsConstructor
public class BusinessExceptionHandler {

    /**
     * Method that handles the exception {@link BindException}
     *
     * @param e exception
     * @return error response
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> validationException2(final BindException e) {
        return handleGenericException(e);
    }

    /**
     * Method that handles the exception {@link BusinessException}
     *
     * @param e exception
     * @return error response
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> systemException(final BusinessException e) {
        return ResponseEntity.status(e.getErrorCode()).body(ErrorResponse.builder()
                .errorCode(e.getErrorCode()).errorMessage(e.getErrorDescription()).build());
    }

    /**
     * Method that handles the exception {@link MethodArgumentNotValidException}
     *
     * @param e exception
     * @return error response
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> validationException(final MethodArgumentNotValidException e) {
        return handleGenericException(e);

    }

    private ResponseEntity<ErrorResponse> handleGenericException(final BindException e) {
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = new ArrayList<>();
        for (org.springframework.validation.FieldError error : result.getFieldErrors()) {
            fieldErrors.add(FieldError.builder().message(error.getDefaultMessage())
                    .field(error.getField()).build());
        }
        return ResponseEntity.badRequest().body(ErrorResponse.builder().fieldErrors(fieldErrors).build());
    }

}