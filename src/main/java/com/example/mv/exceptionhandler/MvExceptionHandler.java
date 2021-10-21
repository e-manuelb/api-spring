package com.example.mv.exceptionhandler;

import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

@ControllerAdvice
public class MvExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

//	Tratar a entrada de campos inexistentes.

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		String userMessage = messageSource.getMessage("invalid.request", null, LocaleContextHolder.getLocale());
		String developerMessage = ex.getCause().toString();

		List<Error> errors = Arrays.asList(new Error(userMessage, developerMessage));

		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}

//	Tratar as más entradas: int em String, null, etc.

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<Error> errors = createErrorsList(ex.getBindingResult());

		return handleExceptionInternal(ex, errors, headers, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler({ EmptyResultDataAccessException.class })
	public ResponseEntity<Object> handleEmptyResultDataAcessException(EmptyResultDataAccessException ex,
			WebRequest request) {

		String userMessage = messageSource.getMessage("recurso.nao-encontrado", null, LocaleContextHolder.getLocale());
		String developerMessage = ex.toString();

		List<Error> errors = Arrays.asList(new Error(userMessage, developerMessage));

		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
//	Campos inválidos

	@ExceptionHandler({ DataIntegrityViolationException.class })
	public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex,
			WebRequest request) {
		String userMessage = messageSource.getMessage("campos-invalidos", null, LocaleContextHolder.getLocale());
		String developerMessage = ex.toString();

		List<Error> errors = Arrays.asList(new Error(userMessage, developerMessage));

		return handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	private List<Error> createErrorsList(BindingResult bindingResult) {
		List<Error> errors = new ArrayList<>();

		for (FieldError fieldError : bindingResult.getFieldErrors()) {
			String userMessage = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
			String developerMessage = fieldError.toString();

			errors.add(new Error(userMessage, developerMessage));
		}

		return errors;

	}

	public static class Error {
		private String userMessage;
		private String developerMessage;

		public Error(String userMessage, String developerMessage) {
			super();
			this.userMessage = userMessage;
			this.developerMessage = developerMessage;
		}

		public String getUserMessage() {
			return userMessage;
		}

		public String getDeveloperMessage() {
			return developerMessage;
		}

	}

}
