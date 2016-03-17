package me.wonwoo.blog.post;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import me.wonwoo.exception.BadRequestException;

@Slf4j
@RestController
@RequestMapping("/post")
public class PostController {

	@Autowired
	private PostService postService;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Post getPost(@PathVariable Long id) {
		return postService.findOne(id);
	}

	
	@RequestMapping(value = {"/" , ""}, method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Page<Post> getPosts(Pageable pageable) {
		return postService.findAll(pageable);
	}

	@RequestMapping(value = "/search/", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Page<Post> getSearchPosts(Pageable pageable, @Param("title") String title) {
		Page<Post> posts = postService.findByTitleStartingWith(title, pageable);
		return posts;
	}

	
	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Page<Post> getPostCategory(@PathVariable Long id, Pageable pageable) {
		return postService.findByCategory(id, pageable);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Post createPost(@RequestBody @Valid Post post, BindingResult result) {
		if (result.hasErrors()) {
			log.debug("field : {} message : {} ", result.getFieldError().getField(),
					result.getFieldError().getDefaultMessage());
			throw new BadRequestException(result.getFieldError().getField(),
					result.getFieldError().getDefaultMessage());
		}
		return postService.save(post);
	}
}

