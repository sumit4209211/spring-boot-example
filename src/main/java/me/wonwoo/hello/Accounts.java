package me.wonwoo.hello;

import javax.persistence.*;

@Entity
public class Accounts {
	@Id
	@GeneratedValue
	private Long id;
	private String name;

	public Accounts() {
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
