package me.wonwoo.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountsService {

	@Autowired
	private AccountRepository accountRepository;

	public Accounts saveAccounts(Accounts accounts) {
		return accountRepository.save(accounts);
	}

	public Accounts getAccounts(Long id) {
		return accountRepository.findOne(id);
	}
}
