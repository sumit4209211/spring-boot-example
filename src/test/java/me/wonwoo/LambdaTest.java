package me.wonwoo;

import me.wonwoo.function.FunctionIntegertoString;
import me.wonwoo.function.FunctionStringtoInteger;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;
import static me.wonwoo.function.util.FunctionalUtil.functionApply;
import static me.wonwoo.function.util.FunctionalUtil.predicateTest;

public class LambdaTest {


    public <T> List<T> predicateTest1(Predicate<T> predicate, List<T> list) {
        List<T> result = new ArrayList<>();
        for(T t : list){
            if(predicate.test(t)){
                result.add(t);
            }
        }
        return result;
    }
    @Test
    public void lambda() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        System.out.println(
                numbers.stream().filter(a -> a > 3).map(b -> String.valueOf(b)).collect(Collectors.joining(",")));

        FunctionStringtoInteger stringtoint = str -> Integer.parseInt(str);
        System.out.println(stringtoint.apply("19099"));
        FunctionIntegertoString inttostring = integer -> String.valueOf(integer);
        System.out.println(inttostring.apply(123123));

//		System.out.println(functionApply(a -> String.valueOf(a), 123));

//		System.out.println(functionApply((String)h -> String.valueOf(h) , "123");
        System.out.println(predicateTest(a -> a > 10, 5));


        Supplier<Item> supplier = this::produceUser;

        Item item = supplier.get();
        System.out.println(item.getName());

        Supplier<String> str = this::str;
        System.out.println(str.get());

        Function<Integer, Function<Integer, Integer>> functional = a -> b -> a * b;
        Function<Integer, Integer> a = functional.apply(6);
        System.out.println(a.apply(5));


        Function<String, Integer> f1 = ff1 -> {
            if (ff1.equals("10")) {
                ff1 = "11";
            }
            return Integer.parseInt(ff1);
        };
        final Integer akk = f1.apply("123");


        List<Integer> collect1 = new ArrayList<>();

        for (Integer number : numbers) {
            if (number > 3) {
                collect1.add(number);
            }
        }

//        System.out.println(collect1);
        final String collect = numbers.stream()
                .filter(i -> i > 3)
                .map(aa -> String.valueOf(aa))
                .collect(joining(",", "[", "]"));

        System.out.println(collect);


        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5);

        int[] t = new int[] { 2 };

        Stream<Integer> integerStream = numbers1.stream().map(i -> i * t[0]);

        t[0] = 10;

        integerStream.forEach(System.out::println);





//
//        final List<Integer> collect = predicateTest1(i -> i > 3, numbers).stream().collect(toList());
//        System.out.println(collect);


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
