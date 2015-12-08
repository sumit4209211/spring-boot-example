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

	public Accounts saveAccounts(Accounts accounts) {
		return accountRepository.save(accounts);
	}

	public Accounts getAccount(Long id) {
		return accountRepository.findOne(id);
	}

	public Page<Accounts> getAccounts(Pageable pageable){
		return accountRepository.findAll(pageable);
	}

	public Accounts getAccountQueryDsl(Long id) {
		return accountRepository.findOne(QAccounts.accounts.id.eq(id));
	}
}
