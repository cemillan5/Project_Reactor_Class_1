# Class 1

## Introduction To Functional Programming With Java

### What's Functional Programming?

**Functional programming** is a programming paradigm in which we try to
bind everything in pure mathematical functions style. It is a declarative
type of programming style. Its main focus is on “what to solve” in contrast
to an imperative style where the main focus is “how to solve”. It uses expressions
instead of statements. An expression is evaluated to produce a value whereas a
statement is executed to assign variables.

source [Functional Programming Paradigm - geeksforgeeks](https://www.geeksforgeeks.org/functional-programming-paradigm/)

## Why Functional Programming?

**Functional programming** is all about treating computation as the evaluation of mathematical functions and avoiding
changing-state and mutable data. So, why should Java developers consider this paradigm?

- **Concise and Expressive Code**: Functional programming promotes shorter, more concise code, making it easier to
  understand and maintain. This can lead to increased productivity and fewer bugs.
- **Immutable Data**: It encourages immutability, which means once data is created, it cannot be changed. This can
  improve code reliability and make it easier to reason about.
- **Parallel and Concurrent Programming**: Functional programming is an excellent fit for parallel and concurrent
  programming, where tasks are executed simultaneously, and shared data is managed safely.
- **Higher-Order Functions**: Java’s support for higher-order functions is quite powerful. It allows you to pass functions
  as arguments and return functions from other functions.
- **Declarative Approach**: You describe what your code should achieve, rather than listing the steps to achieve it.
  This leads to more expressive and less error-prone code.
- **Java’s Lambda Expressions**: With the introduction of lambda expressions in Java 8, functional programming in Java
  became much more practical and accessible. You can define and use functions more concisely than ever before.

source: [Java Functional Programming Essentials - Medium](https://medium.com/@furkanalniak/java-functional-programming-essentials-efb9aef1290e)

## Basic Concepts

1. Higher-Order Functions

**Higher-order functions are capable of receiving functions as arguments and returning a function as a result.**

source: [Functional Programming in Java - Baeldung](https://www.baeldung.com/java-functional-programming)

### Higher Order function Example

```java
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        Function<Integer, Integer> square = x -> x * x;
        Function<Integer, Integer> doubles = x -> x * 2;

        //Function Compositions
        Function<Integer, Integer> doubleSquare = square.compose(doubles);
        System.out.println(doubleSquare.apply(5));
        }
}
```

2. Data Inmutability

In functional programming, we can’t modify a variable after it’s been initialized. We can create new variables – but we 
can’t modify existing variables, and this really helps to maintain state throughout the runtime of a program. Once we 
create a variable and set its value, we can have full confidence knowing that the value of that variable will never change.  

source [Functional Programming Paradigm - geeksforgeeks](https://www.geeksforgeeks.org/functional-programming-paradigm/)

Variables Inmutability examples

```java
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1,2,3,4,5);

        // Map Operation to create a new list with square values of each number

        List<Integer> squares = numbers.stream()
                .map(x -> x * x)
                .toList();
        
        System.out.println(squares);
        
        }
}
```

3. Pure Functions

A function is called **pure function** if it always returns the same result for same argument values and it has no side 
effects like modifying an argument (or global variable) or outputting something. The only result of calling a pure 
function is the return value.


Pure Function Example

```java
import java.util.List;

public class Main {
    public static void main(String[] args) {

        int result = sum(3,3);
        System.out.println(result);

        }

    public static int sum(int a, int b){
        return a + b;
    }

}
```

## Summary

- Functional programming promotes the composition of functions, data immutability, and pure functions. 
- These concepts help in writing more concise, predictable, and easily understandable code. 
- Java offers support for functional programming through features such as lambdas, streams, and higher-order functions.

# Map, Reduce and Filter

## Map

The map is a well-known functional programming concept that is incorporated into Java 8. Map is a function defined in 
java.util.stream.Streams class, which is used to transform each element of the stream by applying a function to each 
element. Because of this property, you can use a map() in Java 8 to transform a Collection, List, Set, or Map.

source: [Java 8 Stream map() function Example with Explanation](https://www.java67.com/2015/01/java-8-map-function-examples.html)

```java
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Main {

    public static <T,R> List<R> map(List<T> list, Function<T,R> function){
        List<R> result = new ArrayList<>();
        for(T element: list){
            result.add(function.apply(element));
        }
        return result;
    }

    public static void main(String[] args) {

    List<Integer> numbers = List.of(1,2,3,4,5);

    // Duplicate numbers
    List<Integer> duplicates = map(numbers, n -> n * 2);
        System.out.println("Duplicates: " + duplicates);

    List<Integer> negatives = map(numbers, n -> -n);
        System.out.println("Negatives: "+ negatives);
    }
}
```

## Reduce

The reduce method, also known as fold in functional programming lingo, accumulates the elements of the stream with a 
BinaryOperator<T> and reduces them to a single value: