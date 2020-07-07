package com.example.sample.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SQLIntegrityConstraintViolationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	String message;

}
