package me.wonwoo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class DuplicateException extends RuntimeException{


	private static final long serialVersionUID = -6958128182491079251L;

	public DuplicateException() {
		super();
	}

	private String key;

	public DuplicateException(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

}
