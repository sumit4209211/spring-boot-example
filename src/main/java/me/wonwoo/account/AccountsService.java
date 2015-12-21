package me.wonwoo.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.wonwoo.exception.AccountsNotFoundException;
import me.wonwoo.exception.DuplicateException;

@Service
@Transactional
public class AccountsService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AccountRepository accountRepository;

	public Accounts getAccount(Long id) {
		Accounts accounts = accountRepository.findOne(id);
		if (accounts == null) {
			throw new AccountsNotFoundException(id);
		}
		return accounts;
	}

	public Page<Accounts> getAccounts(Pageable pageable) {
		return accountRepository.findAll(pageable);
	}

	public Accounts saveAccounts(Accounts accounts) {
		if (getAccount(accounts.getName()) != null) {
			throw new DuplicateException(accounts.getName());
		}
		accounts.setPassword(passwordEncoder.encode(accounts.getPassword()));
		return accountRepository.save(accounts);
	}

	public Accounts updateAccounts(Long id, Accounts accounts) {
		Accounts getAccounts = getAccount(id);
		if (getAccounts == null) {
			throw new AccountsNotFoundException(id);
		}
		getAccounts.setName(accounts.getName());
		getAccounts.setPassword(passwordEncoder.encode(accounts.getPassword()));
		return accountRepository.save(getAccounts);
	}

	public Accounts getAccount(String name) {
		return accountRepository.findByname(name);
	}

	public void deleteAccounts(Long id) {
		Accounts getAccounts = getAccount(id);
		if (getAccounts == null) {
			throw new AccountsNotFoundException(id);
		}
		accountRepository.delete(id);
	}

	// public Accounts getAccountQueryDsl(Long id) {
	// return accountRepository.findOne(QAccounts.accounts.id.eq(id));
	// }
}
