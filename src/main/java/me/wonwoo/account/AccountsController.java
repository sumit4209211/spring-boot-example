package me.wonwoo.account;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import me.wonwoo.exception.BadRequestException;

@Slf4j
@RestController
public class AccountsController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AccountsService service;


	@RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAccount(@PathVariable @Valid Long id) {
		Accounts accounts = service.getAccount(id);
		log.debug("accounts : {} ", accounts);
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	public ResponseEntity<?> getAccounts(Pageable pageable) {
		Page<Accounts> accounts = service.getAccounts(pageable);
		log.debug("accounts : {} ", accounts);
		return new ResponseEntity<>(accounts, HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts", method = RequestMethod.POST)
	public ResponseEntity<?> saveAccounts(@RequestBody @Valid Accounts accounts, BindingResult result) {
		if(result.hasErrors()){
			log.debug("field : {} message : {} ", result.getFieldError().getField(), result.getFieldError().getDefaultMessage());
			throw new BadRequestException(result.getFieldError().getField(), result.getFieldError().getDefaultMessage()); 
		}
		Accounts saveAccounts = service.saveAccounts(accounts);
		return new ResponseEntity<>(saveAccounts, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/accounts/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateAccounts(@PathVariable Long id, @RequestBody @Valid Accounts accounts, BindingResult result) {
		if(result.hasErrors()){
			log.debug("field : {} message : {} ", result.getFieldError().getField(), result.getFieldError().getDefaultMessage());
			throw new BadRequestException(result.getFieldError().getField(), result.getFieldError().getDefaultMessage()); 
		}
		Accounts updateAccounts = service.updateAccounts(id, accounts);
		return new ResponseEntity<>(updateAccounts, HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteAccounts(@PathVariable Long id) {
		Accounts deleteAccount = new Accounts();
		service.deleteAccounts(id);
		deleteAccount.setId(id);
		return new ResponseEntity<>(deleteAccount, HttpStatus.OK);
	}
	
//	@Autowired
//	private Config config;
	// @RequestMapping(value = "/accountsdsl/{id}", method = RequestMethod.GET)
	// public ResponseEntity<?> getAccountsQueryDsl(@PathVariable Long id) {
	// Accounts saveAccounts = service.getAccountQueryDsl(id);
	// return new ResponseEntity<>(saveAccounts, HttpStatus.OK);
	// }
}
