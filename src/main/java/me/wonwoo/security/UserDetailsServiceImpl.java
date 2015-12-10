package me.wonwoo.security;

import me.wonwoo.account.AccountRepository;
import me.wonwoo.account.Accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by wonwoo on 15. 12. 8..
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String id) {
		Accounts account = accountRepository.findByname(id);
		if (account == null) {
			throw new UsernameNotFoundException(id);
		}
		return new UserDetailsImpl(account);
	}
}
