package generalOverview;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class practiceExercises {

    public static int duplicate(int number){
        return number * 2;
    }

    public static boolean isPair(int number){
        return number % 2 == 0;
    }


    public static List<Integer> duplicateAndFilterPairs(List<Integer> numbers){
        Function<Integer, Integer> duplicateFunction = practiceExercises::duplicate;
        Predicate<Integer> isPairFunction = practiceExercises::isPair;

        return numbers.stream()
                .map(duplicateFunction)
                .filter(isPairFunction)
                .toList();

    }


    public static void main(String[] args) {


        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9,10);
        System.out.println("Original List: "+ numbers);

        List<Integer> results = duplicateAndFilterPairs(numbers);
        System.out.println("Results: "+ results);


    }
}
