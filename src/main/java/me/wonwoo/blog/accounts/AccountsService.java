package me.wonwoo.blog.accounts;

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
	private AccountsRepository accountRepository;

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
		if (getAccount(accounts.getEmail()) != null) {
			throw new DuplicateException(accounts.getEmail());
		}
		accounts.setPassword(passwordEncoder.encode(accounts.getPassword()));
		accounts.setStatus("001");
		return accountRepository.save(accounts);
	}

	public Accounts updateAccounts(Long id, Accounts accounts) {
		Accounts getAccounts = getAccount(id);
		if (getAccounts == null) {
			throw new AccountsNotFoundException(id);
		}
		if(accounts.getPassword() != null){
			getAccounts.setPassword(passwordEncoder.encode(accounts.getPassword()));
		}
		if(accounts.getFirst_name() != null){
			getAccounts.setFirst_name(accounts.getFirst_name());
		}
		if(accounts.getLast_name() != null){
			getAccounts.setLast_name(accounts.getLast_name());
		}
		return accountRepository.save(getAccounts);
	}

	public Accounts getAccount(String username) {
		return accountRepository.findByemail(username);
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
