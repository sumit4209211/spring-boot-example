package me.wonwoo.blog.post;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PostController {

	@RequestMapping(value = "/posts", method = RequestMethod.GET)
	public ResponseEntity<?> getAccounts(Pageable pageable) {
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
