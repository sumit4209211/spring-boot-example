package me.wonwoo.function;

import java.util.function.Function;

//엥 다있다.
@FunctionalInterface
public interface FunctionIntegertoString extends Function<Integer, String> {

	String apply(Integer integer);

}