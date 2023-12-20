package com.Henry.Expenses.Controller;

import com.Henry.Expenses.Excepcions.InvalidAmountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class InvalidAmountExceptionController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(InvalidAmountException.class)
    public ResponseEntity<Object> handleInvalidAmountException(
            InvalidAmountException ex, WebRequest request) {
        String errorMessage = ex.getMessage();
        CustomErrorResponse errorResponse = new CustomErrorResponse(errorMessage);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
