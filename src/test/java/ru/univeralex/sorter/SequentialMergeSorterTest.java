package ru.univeralex.sorter;

import org.junit.Before;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class SequentialMergeSorterTest {

    private List<Integer> list;

    @Before
    public void setUp(){
        list = new Random().ints(1000_000).boxed().collect(Collectors.toList());
    }

    @org.junit.Test
    public void sort() {
        Instant before = Instant.now();
        List<Integer> sortedList = SequentialMergeSorter.sort(list);
        Instant after = Instant.now();
        Duration duration = Duration.between(before, after);
        System.out.println(duration.toMillis());
//        sortedList.forEach(System.out::println);
        List<Integer> expectedList = list.stream().sorted().collect(Collectors.toList());
        assertEquals(expectedList, sortedList);
    }
}