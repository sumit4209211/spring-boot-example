package me.wonwoo.hello;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Autowired
	private ModelMapper modelMapper;

	@RequestMapping("/helloWorld")
	public ResponseEntity<?> hello() {
		Accounts map = new Accounts();
		map.setId("wonwoo");
		map.setName("my wonwoo");
		return new ResponseEntity<Accounts>(map, HttpStatus.OK);
	}

}

class Accounts {
	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}