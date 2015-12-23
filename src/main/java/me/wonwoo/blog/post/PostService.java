package me.wonwoo.blog.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.wonwoo.blog.category.Category;
import me.wonwoo.blog.category.CategoryRepository;

@Service
@Transactional
public class PostService {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public Post save(Post post){
		return postRepository.save(post);
	}
	
	public Post findOne(Long id){
		return postRepository.findOne(id);
	}

	public Page<Post> findByCategory(Long categoryId , Pageable pageable) {
		Category category = new Category();
		category.setCategoryId(categoryId);
		Page<Post> posts = postRepository.findByCategory(category, pageable);
		return posts;
	}

	public Page<Post> findAll(Pageable pageable) {
		return postRepository.findAll(pageable);
	}
	
}
