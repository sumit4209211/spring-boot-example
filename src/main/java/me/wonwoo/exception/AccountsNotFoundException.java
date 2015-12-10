package me.wonwoo.exception;

public class AccountsNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4013009075081629571L;

	private Long id;

	public AccountsNotFoundException(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}
}
