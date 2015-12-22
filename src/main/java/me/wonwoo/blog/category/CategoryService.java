package me.wonwoo.blog.category;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.wonwoo.exception.DataNotFoundException;

@Service
@Transactional
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category save(Category category) {
		category.setRegDate(new Date());
		return categoryRepository.save(category);
	}

	public Category getCategory(Long id) {
		return categoryRepository.findOne(id);
	}

	public Page<Category> getCategorys(Pageable pageable) {
		return categoryRepository.findAll(pageable);
	}

	public Category updateCategory(Long id, Category category) {
		Category getCategory = getCategory(id);
		if (getCategory == null) {
			throw new DataNotFoundException(id);
		}
		getCategory.setName(category.getName());
		return categoryRepository.save(getCategory);
	}

	public void deleteCategory(Long id) {
		categoryRepository.delete(id);
	}
}
