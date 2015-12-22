package me.wonwoo.advice;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import me.wonwoo.exception.DataNotFoundException;
import me.wonwoo.exception.BadRequestException;
import me.wonwoo.exception.DuplicateException;
import me.wonwoo.exception.bean.AdviceErrorRespones;

@ControllerAdvice
@RestController
public class GlobalException {

	@ExceptionHandler(DuplicateException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public AdviceErrorRespones handleDuplicateKeyException(DuplicateException e) {
		return setAdviceErrorRespones("duplicate.key", e.getKey() + " duplicate key");
	}

	@ExceptionHandler(DataNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public AdviceErrorRespones handleAccountNotFoundException(DataNotFoundException e) {
		return setAdviceErrorRespones("account.not.found", e.getId() + " not found");
	}

	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public AdviceErrorRespones handleBadRequestException(BadRequestException e) {
		return setAdviceErrorRespones("invalid.parameters", e.getValue() + " :" + e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public AdviceErrorRespones handleException(Exception e) {
		return setAdviceErrorRespones("unknown.excpetion",  e.getMessage());
	}

	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public AdviceErrorRespones handleNullPointerException(NullPointerException e) {
		return setAdviceErrorRespones("null.pointer", e.getMessage());
	}

	private AdviceErrorRespones setAdviceErrorRespones(String error, String error_description) {
		return new AdviceErrorRespones(error, error_description);
	}
}
