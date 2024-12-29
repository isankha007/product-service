package com.sankha.productService.exceptions;

import com.sankha.productService.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFound.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ErrorResponse handleProductNotFound(HttpServletRequest request, Exception exception) {
        return new ErrorResponse(request.getRequestURI(), exception.getLocalizedMessage());
    }

    @ExceptionHandler(PermissionDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    ErrorResponse handlePermissionDenied(HttpServletRequest request, Exception exception) {
        return new ErrorResponse(request.getRequestURI(), exception.getLocalizedMessage());
    }
}
