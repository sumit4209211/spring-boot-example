package me.wonwoo.blog.post;

import javax.validation.Valid;

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
public class PostController {

	@Autowired
	private PostService postService;
	
	@RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPost(@PathVariable Long id) {
		Post post = postService.findOne(id);
		return new ResponseEntity<>(post, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public ResponseEntity<?> getPosts(Pageable pageable) {
		Page<Post> post = postService.findAll(pageable);
		return new ResponseEntity<>(post, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/post/category/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPostCategory(@PathVariable Long id, Pageable pageable) {
		Page<Post> posts = postService.findByCategory(id, pageable);
		return new ResponseEntity<>(posts, HttpStatus.OK);
	}

	@RequestMapping(value = "/post", method = RequestMethod.POST)
	public ResponseEntity<?> createPost(@RequestBody @Valid Post post, BindingResult result) {
		if (result.hasErrors()) {
			log.debug("field : {} message : {} ", result.getFieldError().getField(),
					result.getFieldError().getDefaultMessage());
			throw new BadRequestException(result.getFieldError().getField(),
					result.getFieldError().getDefaultMessage());
		}
		Post createPost = postService.save(post);
		return new ResponseEntity<>(createPost, HttpStatus.OK);
	}

}
