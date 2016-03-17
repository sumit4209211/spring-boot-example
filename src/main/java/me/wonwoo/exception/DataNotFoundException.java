package me.wonwoo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class DataNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4013009075081629571L;

	private Long id;

	public DataNotFoundException(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
