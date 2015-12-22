package me.wonwoo.blog.category;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {

	@Id
	@GeneratedValue
	private Long categoryId;
	
	private String name;
	
//	@DateTimeFormat(iso = ISO.DATE_TIME)
//	private LocalDateTime reg_date;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date regDate;
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = { CascadeType.ALL })
//	private List<Post> posts = new ArrayList<Post>();;
	
}
