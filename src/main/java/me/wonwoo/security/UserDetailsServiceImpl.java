package me.wonwoo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import me.wonwoo.blog.accounts.Accounts;
import me.wonwoo.blog.accounts.AccountsRepository;

/**
 * Created by wonwoo on 15. 12. 8..
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private AccountsRepository accountRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		Accounts account = accountRepository.findByemail(username);
		if (account == null) {
			throw new UsernameNotFoundException(username);
		}
		return new UserDetailsImpl(account);
	}
}
