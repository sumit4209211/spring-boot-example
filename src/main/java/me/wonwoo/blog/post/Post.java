package me.wonwoo.blog.post;

import java.util.Date;

import javax.persistence.*;

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

	@Column(columnDefinition="TEXT")
	private String content;

	@Temporal(TemporalType.TIMESTAMP)
	private Date regDate;

	private String email;

	@ManyToOne(fetch = FetchType.EAGER, optional=false)
	@JoinColumn(name = "categoryId", insertable = true, updatable = false)
	private Category category;

}
