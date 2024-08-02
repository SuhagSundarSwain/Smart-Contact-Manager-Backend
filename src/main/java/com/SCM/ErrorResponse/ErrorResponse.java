package com.SCM.ErrorResponse;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.SCM.Helper.AppConstants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class ErrorResponse {

    private String errorType;
    private Object error;

    public ErrorResponse(String errorType, BindingResult rBindingResult) {
        this.errorType = errorType;
        this.error = rBindingResult.getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    }

    public ErrorResponse(String errorType, String error) {
        if (errorType == AppConstants.DUPLICATE_ERROR) {
            this.errorType = errorType;
            error = error.toLowerCase();
            this.error = Map.of(error, error + " is already exist.");
        } else if (errorType == AppConstants.INVALID_CREDENTIAL) {
            this.errorType = errorType;
            this.error = error;
        }
    }
}
