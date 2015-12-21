package me.wonwoo.blog.accounts;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accounts {

	@Id
	@GeneratedValue
	private Long id;

	@Size(max = 100, min = 5)
	@Column(unique = true)
	private String email;
	
	@Size(max = 128, min = 5)
	private String password;

	@Size(max = 100, min = 5)
	private String first_name;

	@Size(max = 100, min = 1)
	private String last_name;

	@Column(length = 3)
	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date joined;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updated;
}
