package me.wonwoo.blog.post;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.wonwoo.blog.category.Category;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

	@Id
	@GeneratedValue
	private Long id;
	
	private String title;
	private String content;
	private Date regDate;
	private String email;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="categoryId", insertable = true, updatable = false)
	private Category category;
	
}
