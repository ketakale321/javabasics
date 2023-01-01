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
        // learnDates();
        // learnTimeZones();
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
