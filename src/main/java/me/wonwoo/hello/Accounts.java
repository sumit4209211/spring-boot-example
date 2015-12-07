package me.wonwoo.hello;

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
}
