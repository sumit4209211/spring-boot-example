package me.wonwoo.account;

import me.wonwoo.account.QAccounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountsService {

	@Autowired
	private AccountRepository accountRepository;

	public Accounts getAccount(Long id) {
		return accountRepository.findOne(id);
	}

	public Page<Accounts> getAccounts(Pageable pageable) {
		return accountRepository.findAll(pageable);
	}

	public Accounts getAccountQueryDsl(Long id) {
		return accountRepository.findOne(QAccounts.accounts.id.eq(id));
	}

	public Accounts saveAccounts(Accounts accounts) {
		return accountRepository.save(accounts);
	}

	public Accounts updateAccounts(Long id, Accounts accounts) {
		Accounts getAccounts = getAccount(id);
		getAccounts.setName(accounts.getName());
		getAccounts.setPassword(accounts.getPassword());
		return accountRepository.save(getAccounts);
	}

	public void deleteAccounts(Long id) {
		 accountRepository.delete(id);
	}
}
