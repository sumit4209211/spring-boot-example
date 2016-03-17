package me.wonwoo.blog.category;

import lombok.extern.slf4j.Slf4j;
import me.wonwoo.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@Slf4j
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;


	
	@RequestMapping(value = "/category/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Category getCategory(@PathVariable Long id) {
		return categoryService.getCategory(id);
	}
	
	@RequestMapping(value = "/category", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public Page<Category> getCategorys(Pageable pageable) {
		Page<Category> categorys = categoryService.getCategorys(pageable);
		return categorys;
	}

	@RequestMapping(value = "/category", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Category createCategory(@RequestBody @Valid Category category, BindingResult result) {
		if (result.hasErrors()) {
			fieldError(result);
		}
		return categoryService.save(category);
	}
	
	@RequestMapping(value = "/category/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public Category updateCategory(@PathVariable Long id, Category category) {
		return categoryService.updateCategory(id,category);
	}
	
	@RequestMapping(value = "/category/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCategory(@PathVariable Long id) {
		categoryService.deleteCategory(id);
	}
	
	private void fieldError(BindingResult result) {
		FieldError fieldError = result.getFieldError();
		String field = fieldError.getField();
		String message = fieldError.getDefaultMessage();
		log.debug("field : {} message : {} ", field, message);
		throw new BadRequestException(field, message);
	}
}
