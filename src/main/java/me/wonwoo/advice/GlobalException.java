package me.wonwoo.advice;

import me.wonwoo.exception.AccountsNotFoundException;
import me.wonwoo.exception.BadRequestException;
import me.wonwoo.exception.DuplicateException;
import me.wonwoo.exception.bean.AdviceErrorRespones;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalException {

	@ExceptionHandler(DuplicateException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public AdviceErrorRespones handleDuplicateKeyException(DuplicateException e) {
		return setAdviceErrorRespones("duplicate.key", e.getKey() + " duplicate key", e.getMessage());
	}

	@ExceptionHandler(AccountsNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public AdviceErrorRespones handleAccountNotFoundException(AccountsNotFoundException e) {
		return setAdviceErrorRespones("account.not.found", e.getId() + " not found", e.getMessage());
	}

	@ExceptionHandler(BadRequestException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public AdviceErrorRespones handleBadRequestException(BadRequestException e) {
		return setAdviceErrorRespones("invalid.parameters", e.getValue(), e.getValue() + " :" + e.getMessage());
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public AdviceErrorRespones handleException(Exception e) {
		return setAdviceErrorRespones("unknown.excpetion", "unknown excpetion", e.getMessage());
	}

	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public AdviceErrorRespones handleNullPointerException(NullPointerException e) {
		return setAdviceErrorRespones("null.pointer", "null pointer", e.getMessage());
	}

	private AdviceErrorRespones setAdviceErrorRespones(String code, String message, String detail) {
		return new AdviceErrorRespones(code, message, detail);
	}
}
