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