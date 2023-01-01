package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        learnDates();
        learnTimeZones();
        learnStreams();
    }

    private static void learnStreams() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 8, 9, 10);

        // printing using streams
        list.forEach(e -> {
            System.out.println(e);
        });
        list.stream()
            .forEach(System.out::print);

        // infinite streams
        System.out.println(Stream.iterate(1, e -> e + 1)
                                 .mapToInt(e -> e)
                                 .limit(100)
                                 .sum()
        );

        // generate series of numbers from the streams
        List<Integer> list2 = Stream.iterate(1, e -> e + 1)
                                    .limit(100)
                                    .collect(Collectors.toList());
        list2.forEach(System.out::println);

        // filter example : generate series of numbers divisible by 3
        List<Integer> list3 = Stream.iterate(1, e -> e + 1)
                                    .limit(100)
                                    .filter(e -> e % 3 == 0)
                                    .collect(Collectors.toList());
        list3.forEach(System.out::println);

        //    generating a map
        // generate person list
        List<Person> personList = generatePersonsList();
        // personList.forEach(System.out::println);

        // we sorted on the age then inserted the elements to map
        // we grouped by names and converted to list and stored as value
        System.out.println("Values grouped by names: " + personList.stream()
                                                                   .sorted((p1, p2) -> p1.age - p2.age)
                                                                   .collect(Collectors.groupingBy(Person::getName,
                                                                           Collectors.toList()
                                                                   )));

        System.out.println("Even aged persons: " + personList.stream()
                                                             .sorted((p1, p2) -> p1.age - p2.age)
                                                             .filter((person) -> person.age % 2 == 0)
                                                             .collect(Collectors.groupingBy(Person::getName,
                                                                     Collectors.toList()
                                                             )));


    }

    private static List<Person> generatePersonsList() {
        return Arrays.asList(
                new Person("Ketan", Gender.MALE, 26),
                new Person("Amy", Gender.FEMALE, 17),
                new Person("Ei", Gender.FEMALE, 24),
                new Person("Jhon Lee", Gender.MALE, 150),
                new Person("Ei", Gender.FEMALE, 27),
                new Person("Ei", Gender.FEMALE, 30),
                new Person("Jhon Lee", Gender.MALE, 30),
                new Person("Jhon Lee", Gender.MALE, 25),
                new Person("Jhon Lee", Gender.MALE, 55)
        );
    }

    private static void learnTimeZones() {
        Set<String> zones = ZoneId.getAvailableZoneIds();
        zones.forEach((zone) -> System.out.println("zone:" + zone));


        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        System.out.println("local time" + zonedDateTime);
        ZonedDateTime newInstance = zonedDateTime.withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println("UTC time" + newInstance.toString() + ", offset:" + newInstance.getOffset());
    }


    private static void learnDates() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);
        System.out.println(LocalDate.parse("2022-12-14"));
        System.out.println(LocalDate.now()
                                    .plus(5, ChronoUnit.YEARS));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime moment = LocalDateTime.parse("30/04/1996 13:15:20", formatter);
        System.out.println("year:" + moment.getYear());
        System.out.println("month:" + moment.getMonth());
        System.out.println("date:" + moment.getDayOfMonth());
        System.out.println("hour:" + moment.getHour());
        System.out.println("minutes:" + moment.getMinute());
        System.out.println("seconds:" + moment.getSecond());

        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);
    }

}

class Person {
    String name;
    Integer age;
    Gender gender;

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }


    Person(String name, Gender gender, Integer age) {
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return this.name + "-" + this.gender + "-" + this.age;
    }
}

enum Gender {
    MALE,
    FEMALE


};