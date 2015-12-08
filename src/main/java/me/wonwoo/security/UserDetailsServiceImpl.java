package me.wonwoo.security;

import me.wonwoo.account.Accounts;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Created by wonwoo on 15. 12. 8..
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String id) {
		Accounts accounts = new Accounts();
		accounts.setName("user");
		accounts.setPassword("password");
		return new UserDetailsImpl(accounts);
	}
}
