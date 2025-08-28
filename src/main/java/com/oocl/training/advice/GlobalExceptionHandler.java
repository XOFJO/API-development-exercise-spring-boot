package com.oocl.training.advice;
import exception.OutsideAgeRangeEmployee;
import exception.Over30YearsOldSalaryLessThan20000;
import exception.UpdateInactiveEmployee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UpdateInactiveEmployee.class)
    public ResponseEntity<?> handleResourceNotFoundException(UpdateInactiveEmployee ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "message", "Resource not found.",
                        "error", ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                ));
    }


    public ResponseEntity<?> handleOutsideAgeRangeEmployee(OutsideAgeRangeEmployee ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "message", "Employee is younger than 18.",
                        "error", ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                ));
    }


    public ResponseEntity<?> handleOver30YearsOldSalaryLessThan20000(Over30YearsOldSalaryLessThan20000 ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "message", "Employee is older than 30 years old but earning less than 20000 which is not allowed.",
                        "error", ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handle(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        "message", "Internal system error.",
                        "error", ex.getMessage(),
                        "timestamp", LocalDateTime.now()
                ));
    }
}
