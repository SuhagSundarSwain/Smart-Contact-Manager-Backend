package com.SCM.ErrorHandlers;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

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
    private Map<String, String> error;

    public ErrorResponse(String errorType, BindingResult rBindingResult) {
        this.errorType = errorType;
        this.error = rBindingResult.getFieldErrors().stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    }

    public ErrorResponse(String duplicateField) {
        this.errorType = "Duplicate error entry";
        this.error = Map.of(duplicateField, duplicateField + " is already in use.");
    }
}
