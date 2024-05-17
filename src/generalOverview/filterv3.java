package generalOverview;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class filterv3 {

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate){
        List<T> result = new ArrayList<>();
        for(T element: list){
            if(predicate.test(element)){
                result.add(element);
            }
        }
        return result;
    }

    public static void main(String[] args) {

        List<Integer> numbers = List.of(1,2,3,4,5,6,7,8,9,10);

        //Filter square numbers
        List<Integer> pairs = filter(numbers, n-> n % 2 == 0);
        System.out.println("pair numbers: "+pairs);

        //Filter numbers greater than 5
        List<Integer> greaterThan5 = filter(numbers, n -> n > 5);
        System.out.println("Numbers greater than 5: "+ greaterThan5);

        // Filter numbers smaller or equal to 3
        List<Integer> smallerOrEqualTo3 = filter(numbers, n -> n <= 3);
    System.out.println("Numbers smaller or equal to 3: "+ smallerOrEqualTo3);

    }
}
