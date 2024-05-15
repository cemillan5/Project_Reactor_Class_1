package workshop;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(new Person("John", 25,"Male"),
                new Person("Mary",30,"Female"),
                new Person("Peter",40,"Male"),
                new Person("Anna",20,"Female"));


        System.out.println(persons);

        //Filter people with ages over 25

        List<Person> peopleWithAgesOver25 = persons
                .stream()
                .filter(person -> person.getAge() > 25)
                .toList();

        System.out.println(peopleWithAgesOver25);

        // Obtain a list with all the names using map

        List<String> namesList = persons
                .stream()
                .map(person -> person.getName()) // Using method reference instead of lambda
                .collect(Collectors.toList());

        System.out.println(namesList);

        // Obtain the sum of the ages of the people

        int sumAges = persons
                .stream()
                .reduce(0, (accumulator, person) -> accumulator + person.getAge(), (a, b) -> a + b);

        System.out.println("The sum of the ages of the people: " + sumAges);


        // Count the amount of people by gender

        long femaleCount = persons
                .stream()
                .filter(person -> person.getGender().equals("Female"))
                .count();

        System.out.println("The count of people that is female: " + femaleCount);

        long maleCount = persons
                .stream()
                .filter(person -> person.getGender().equals("Male"))
                .count();

        System.out.println("The count of people that is male: " + maleCount);


        // Age Averages

        double averageAge = persons
                .stream()
                .mapToDouble(person -> person.getAge())
                .average()
                .getAsDouble();

        System.out.println("The average age of the people is: " + averageAge);

        // Find the older person

        int olderPerson = persons
                .stream()
                .mapToInt(person -> person.getAge())
                .max()
                .getAsInt();

        System.out.println("The oldest person is "+ olderPerson + " years old.");
    }
}
