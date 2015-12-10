package me.wonwoo;

import static me.wonwoo.function.util.FunctionalUtil.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.junit.Test;

import me.wonwoo.function.FunctionIntegertoString;
import me.wonwoo.function.FunctionStringtoInteger;

public class LambdaTest {

	@Test
	public void lambda() {
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
		System.out.println(
				numbers.stream().filter(a -> a > 3).map(b -> String.valueOf(b)).collect(Collectors.joining(",")));

		FunctionStringtoInteger stringtoint = str -> Integer.parseInt(str);
		System.out.println(stringtoint.apply("19099"));
		FunctionIntegertoString inttostring = integer -> String.valueOf(integer);
		System.out.println(inttostring.apply(123123));

		System.out.println(functionApply(a -> String.valueOf(a), 123));
		System.out.println(predicateTest(a -> a > 10, 5));

		Supplier<Item> supplier = this::produceUser;

		Item item = supplier.get();
		System.out.println(item.getName());

		Supplier<String> str = this::str;
		System.out.println(str.get());

		Function<Integer, Function<Integer, Integer>> functional = a -> b -> a * b;
		Function<Integer, Integer> a = functional.apply(6);
		System.out.println(a.apply(5));

	}

	private Item produceUser() {
		return new Item("1");
	}

	private String str() {
		return "username";

	}

	// private <R> R functuonal(Function<String, R> function) {
	// return function.apply("100");
	// }
	//
	// private <T> boolean predicateTest(Predicate<T> predicate, T t) {
	// return predicate.test(t);
	// }
}

class Item {
	private String name;

	public Item() {
	}

	public Item(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
