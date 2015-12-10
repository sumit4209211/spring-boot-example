package me.wonwoo.function;

import java.util.function.Function;

@FunctionalInterface
public interface FunctionStringtoInteger extends Function<String, Integer> {

	Integer apply(String str);
}
