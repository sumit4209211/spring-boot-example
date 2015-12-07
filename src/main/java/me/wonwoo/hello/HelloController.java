package me.wonwoo.hello;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private AccountsService service;

	@RequestMapping("/helloWorld")
	public ResponseEntity<?> hello() {
		Accounts map = new Accounts();
		map.setName("my wonwoo");
		return new ResponseEntity<Accounts>(map, HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAccounts(@PathVariable Long id) {
		Accounts saveAccounts = service.getAccounts(id);
		return new ResponseEntity<Accounts>(saveAccounts, HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts", method = RequestMethod.POST)
	public ResponseEntity<?> saveAccounts(@RequestBody Accounts accounts) {
		Accounts saveAccounts = service.saveAccounts(accounts);
		return new ResponseEntity<Accounts>(saveAccounts, HttpStatus.OK);
	}

}
