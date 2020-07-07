package com.example.sample.exceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.sample.model.ExceptionHandlerModel;
import com.example.sample.model.SQLIntegrityConstraintViolationException;

@ControllerAdvice
public class CustomExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationError(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getField() + " " + error.getDefaultMessage()).collect(Collectors.toList());
		return new ResponseEntity<>(ExceptionHandlerModel.builder().staus(400).message("Validation error")
				.errors(errors).timestamp(new Date()).build(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<ExceptionHandlerModel> handleSQLIntegrityConstraintViolationException(
			SQLIntegrityConstraintViolationException ex) {
		return new ResponseEntity<>(
				ExceptionHandlerModel.builder().staus(400).message(ex.getMessage()).timestamp(new Date()).build(),
				HttpStatus.BAD_REQUEST);
	}
}
