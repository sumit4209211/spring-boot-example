package me.wonwoo.blog.category;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
			fieldError(result);
		}
		Category createCategory = categoryService.save(category);
		return new ResponseEntity<>(createCategory, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCategory(@PathVariable Long id) {
		Category category = categoryService.getCategory(id);
		return new ResponseEntity<>(category, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/category/", method = RequestMethod.GET)
	public ResponseEntity<?> getCategorys(Pageable pageable) {
		Page<Category> categorys = categoryService.getCategorys(pageable);
		return new ResponseEntity<>(categorys, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/category/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateCategory(@PathVariable Long id, Category category) {
		Category updateCategory = categoryService.updateCategory(id,category);
		return new ResponseEntity<>(updateCategory, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	private void fieldError(BindingResult result) {
		FieldError fieldError = result.getFieldError();
		String field = fieldError.getField();
		String message = fieldError.getDefaultMessage();
		log.debug("field : {} message : {} ", field, message);
		throw new BadRequestException(field, message);
	}
}
