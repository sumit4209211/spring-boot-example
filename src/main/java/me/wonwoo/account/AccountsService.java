package me.wonwoo.account;

import me.wonwoo.exception.AccountsNotFoundException;
import me.wonwoo.exception.DuplicateException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

//	public Accounts getAccountQueryDsl(Long id) {
//		return accountRepository.findOne(QAccounts.accounts.id.eq(id));
//	}

	public Accounts saveAccounts(Accounts accounts) {
		if (getAccount(accounts.getName()) != null) {
			throw new DuplicateException(accounts.getName());
		}
		accounts.setPassword(passwordEncoder.encode(accounts.getPassword()));
		return accountRepository.save(accounts);
	}

	public Accounts updateAccounts(Long id, Accounts accounts) {
		Accounts getAccounts = getAccount(id);
		if(getAccounts == null){
			throw new AccountsNotFoundException(id);
		}
		getAccounts.setName(accounts.getName());
		getAccounts.setPassword(accounts.getPassword());
		return accountRepository.save(getAccounts);
	}

	public Accounts getAccount(String name) {
		return accountRepository.findByname(name);
	}

	// TODO TEST
	public void deleteAccounts(Long id) {
		accountRepository.delete(id);
	}
}
