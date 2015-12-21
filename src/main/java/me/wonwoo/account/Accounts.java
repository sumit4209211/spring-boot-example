package me.wonwoo.account;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	
	@Size(max = 20, min = 5)
	private String name;
	
	@Size(max = 128, min = 5)
	private String password;

	public Accounts(String name, String password) {
		this.name = name;
		this.password = password;

	}
}
