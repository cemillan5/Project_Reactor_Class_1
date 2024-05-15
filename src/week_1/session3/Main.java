package week_1.session3;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Predicate<Integer> isEven = n -> n % 2 == 0; // Get to know the function included in the Predicate
        var evenNumbers = filter(numbers,isEven);
        var negateEvenNumbers = filter(numbers, isEven.negate());
        System.out.println("Even numbers: " + evenNumbers);
        System.out.println("It Is Not An Even numbers: " + negateEvenNumbers);

        List<String> names = Arrays.asList("Alice", "Bob", "Raoul", "Andy", "Camilo");
        Predicate<String> starsWithA = name -> name.startsWith("A");
        var startsWithA = filter(names, starsWithA);
        System.out.println("Starts With The Letter A: "+startsWithA);

        //Function<String, String> toUpperCase = s -> s.toUpperCase();
        Function<String, String> toUpperCase = String::toUpperCase;
        Function<String, String> subString = s -> s.substring(1);
        System.out.println("Uppercase: "+ toUpperCase.apply("raoul"));
        System.out.println("Uppercase: "+ toUpperCase
                .andThen(s -> s.substring(1))
                .apply("raoul")); // Concatenate Functions

        System.out.println("Uppercase: "+ toUpperCase
                .compose(subString)
                .apply("raoul")); // Concatenate


        //Consumer<String> printNames = input -> System.out.println(input);
        //names.forEach(printNames);
        Consumer<String> printNames = System.out::println;
        names.forEach(printNames);

        Supplier<List<Integer>> getNumbers = () -> Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        System.out.println(getNumbers.get());

        //Stream.builder().add().add().build();

    }


    // Generic filter - Reusable
    public static <T> List<T> filter(List<T> list, Predicate<T> predicate){
        return list
                .stream()
                .filter(predicate)
                .toList();
    }
}
