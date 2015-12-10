package me.wonwoo.exception.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdviceErrorRespones {
	private String code;
	private String message;
	private String detail;

}
