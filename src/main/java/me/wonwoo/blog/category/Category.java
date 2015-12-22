package me.wonwoo.blog.category;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.wonwoo.blog.post.Post;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

	@Id
	@GeneratedValue
	private Long categoryId;
	
	private String name;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date reg_date;
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = { CascadeType.ALL })
//	private List<Post> posts = new ArrayList<Post>();;
	
}
