package com.springProject.friends.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.springProject.friends.util.ErrorMessage;
import com.springProject.friends.util.FieldErrorMessage;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler
	ErrorMessage exceptionHandler(ValidationException e){
		return new ErrorMessage("400", e.getMessage());
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e) {
		List<FieldError> fieldErros = e.getBindingResult().getFieldErrors();

		List<FieldErrorMessage> fieldErroMessages = fieldErros.stream()
				.map(fieldError -> new FieldErrorMessage(
						fieldError.getField(), 
						fieldError.getDefaultMessage()))
				.collect(Collectors.toList());
		return fieldErroMessages;
	}
}
