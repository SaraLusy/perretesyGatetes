package com.empresa.perretesGatetes.rest.exception;

import java.sql.Timestamp;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;


@ControllerAdvice
public class AppRestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<AppRestException> handleException(ResponseStatusException exception) {
		AppRestException error = new AppRestException(exception.getStatus(),
				exception.getReason(),
				new Timestamp(System.currentTimeMillis()).toString());
		
		return new ResponseEntity<>(error, error.getStatus());
	}
}
