package me.wonwoo.blog.post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.wonwoo.blog.category.Category;

@Repository
public interface PostRepository extends JpaRepository<Post, Long>{

	Page<Post> findByCategory(Category category, Pageable pageable);

}
