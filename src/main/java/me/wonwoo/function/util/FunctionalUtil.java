package me.wonwoo.function.util;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class FunctionalUtil {

	public static <T> boolean predicateTest(Predicate<T> predicate, T t) {
		return predicate.test(t);
	}

	public static <T, R> R functionApply(Function<T, R> function, T t) {
		return function.apply(t);
	}

	public static <T> void consumerAccept(Consumer<T> consumer, T t) {
		consumer.accept(t);
	}
	
}
