package me.wonwoo.blog.accounts;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

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
	@ResponseStatus(HttpStatus.OK)
	public Accounts getAccount(@PathVariable @Valid Long id) {
		Accounts accounts = service.getAccount(id);
		log.debug("accounts : {} ", accounts);
		return accounts;
	}

	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Page<Accounts> getAccounts(Pageable pageable) {
		Page<Accounts> accounts = service.getAccounts(pageable);
		log.debug("accounts : {} ", accounts);
		return accounts;
	}

	@RequestMapping(value = "/accounts", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Accounts saveAccounts(@RequestBody @Valid Accounts accounts, BindingResult result) {
		if (result.hasErrors()) {
			fieldError(result);
		}
		return service.saveAccounts(accounts);
	}

	@RequestMapping(value = "/accounts/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public Accounts updateAccounts(@PathVariable Long id, @RequestBody @Valid Accounts accounts,
			BindingResult result) {
		if (result.hasErrors()) {
			fieldError(result);
		}
		return service.updateAccounts(id, accounts);
	}

	@RequestMapping(value = "/accounts/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteAccounts(@PathVariable Long id) {
		service.deleteAccounts(id);
	}

	private void fieldError(BindingResult result) {
		FieldError fieldError = result.getFieldError();
		String field = fieldError.getField();
		String message = fieldError.getDefaultMessage();
		log.debug("field : {} message : {} ", field, message);
		throw new BadRequestException(field, message);
	}

	// @Autowired
	// private Config config;
	// @RequestMapping(value = "/accountsdsl/{id}", method = RequestMethod.GET)
	// public ResponseEntity<?> getAccountsQueryDsl(@PathVariable Long id) {
	// Accounts saveAccounts = service.getAccountQueryDsl(id);
	// return new ResponseEntity<>(saveAccounts, HttpStatus.OK);
	// }
}
