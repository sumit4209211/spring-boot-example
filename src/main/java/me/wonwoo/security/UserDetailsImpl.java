package me.wonwoo.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import me.wonwoo.account.Accounts;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * Created by wonwoo on 15. 12. 8..
 */
public class UserDetailsImpl extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2144213171926616839L;

	public UserDetailsImpl(Accounts account) {
		super(account.getName(), account.getPassword(), authorities(account));
	}

	private static Collection<? extends GrantedAuthority> authorities(Accounts account) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		return authorities;
	}
}
