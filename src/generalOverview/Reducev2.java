package generalOverview;

import java.util.List;
import java.util.function.BinaryOperator;

public class Reducev2 {

    public static <T> T reduce(List<T> list, T identity, BinaryOperator<T> operator){
        T result = identity;
        for(T element: list){
            result = operator.apply(result, element);
        }
        return result;
    }
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1,2,3,4,5);

        //sum all numbers
        int sum = reduce(numbers,0,(a,b)-> a + b);
        System.out.println("Sum: "+sum);

        // multiply all numbers
        int product = reduce(numbers,1,(a,b)-> a * b);
        System.out.println("Product: "+product);
    }
}
