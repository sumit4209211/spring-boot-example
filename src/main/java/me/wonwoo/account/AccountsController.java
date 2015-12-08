package me.wonwoo.account;

import lombok.extern.slf4j.Slf4j;
import me.wonwoo.config.Config;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class AccountsController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AccountsService service;

	@Autowired
	private Config config;

	@RequestMapping(value = "/accounts", method = RequestMethod.POST)
	public ResponseEntity<?> saveAccounts(@RequestBody Accounts accounts) {
		Accounts saveAccounts = service.saveAccounts(accounts);
		List<String> servers = config.getServers();
		log.debug("servers {}", servers);
		return new ResponseEntity<>(saveAccounts, HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAccount(@PathVariable Long id) {
		Accounts saveAccounts = service.getAccount(id);
		return new ResponseEntity<>(saveAccounts, HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	public ResponseEntity<?> getAccounts(Pageable pageable) {
		Page<Accounts> accounts = service.getAccounts(pageable);
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateAccounts(@PathVariable Long id, @RequestBody Accounts accounts) {
		Accounts updateAccounts = service.updateAccounts(id, accounts);
		return new ResponseEntity<>(updateAccounts, HttpStatus.OK);
	}

	@RequestMapping(value = "/accountsdsl/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAccountsQueryDsl(@PathVariable Long id) {
		Accounts saveAccounts = service.getAccountQueryDsl(id);
		return new ResponseEntity<>(saveAccounts, HttpStatus.OK);
	}

	// TODO 데이터베이스 방언 (dialect) 셋팅 공부
}
