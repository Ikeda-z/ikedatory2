package catstech.studentmanagement.controller;

import catstech.studentmanagement.exception.TestException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptionHandle {

  @ExceptionHandler(TestException.class)
  @ResponseBody
  public ResponseEntity<String> handleTestException(TestException ex) {
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseBody
  public ResponseEntity<String> handleTestExceptionMessage(ConstraintViolationException ex) {
    String errorMessage = ex.getConstraintViolations().iterator().next().getMessage();
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
  }

}
