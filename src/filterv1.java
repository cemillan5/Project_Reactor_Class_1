import java.util.Arrays;
import java.util.List;

public class filterv1 {
    public static void main(String[] args) {
        List<Integer> listOfNumbers = Arrays.asList(1, 2, 3, 4, 5, 6, 12, 18);

        Integer lcm = listOfNumbers.stream()
                .filter(i -> i % 2 == 0)
                .filter(i -> i % 3 == 0)
                .findFirst().get();

        System.out.println(lcm);
    }
}
