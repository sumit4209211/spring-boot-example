package me.wonwoo.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accounts {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String password;

	public Accounts(String name, String password) {
		this.name = name;
		this.password = password;

	}
}
