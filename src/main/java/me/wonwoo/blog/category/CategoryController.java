package me.wonwoo.blog.category;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;
import me.wonwoo.exception.BadRequestException;

@Controller
@Slf4j
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "/category", method = RequestMethod.POST)
	public ResponseEntity<?> createCategory(@RequestBody @Valid Category category, BindingResult result) {
		if (result.hasErrors()) {
			log.debug("field : {} message : {} ", result.getFieldError().getField(),
					result.getFieldError().getDefaultMessage());
			throw new BadRequestException(result.getFieldError().getField(),
					result.getFieldError().getDefaultMessage());
		}
		Category createCategory = categoryService.save(category);
		return new ResponseEntity<>(createCategory, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCategory(@PathVariable Long id) {
		Category category = categoryService.getCategory(id);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}
	
}
