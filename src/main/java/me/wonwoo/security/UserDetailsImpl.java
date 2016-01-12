package me.wonwoo.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import me.wonwoo.blog.accounts.Accounts;

/**
 * Created by wonwoo on 15. 12. 8..
 */
public class UserDetailsImpl extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2144213171926616839L;

	public UserDetailsImpl(Accounts account) {
		super(account.getEmail(), account.getPassword(), authorities(account));
	}

	private static Collection<? extends GrantedAuthority> authorities(Accounts account) {
		return Arrays.asList(() -> "ROLE_ADMIN");
	}
}
