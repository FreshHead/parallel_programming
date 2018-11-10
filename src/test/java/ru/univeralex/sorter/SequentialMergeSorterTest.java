package ru.univeralex.sorter;

import org.junit.Before;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class SequentialMergeSorterTest {

    private List<Integer> list;

    @Before
    public void setUp(){
        list = new Random().ints(100_000).boxed().collect(Collectors.toList());
    }

    @org.junit.Test
    public void sort() {
        List<Integer> sortedList = SequentialMergeSorter.sort(list);
        sortedList.forEach(System.out::println);
        List<Integer> expectedList = list.stream().sorted().collect(Collectors.toList());
        assertEquals(expectedList, sortedList);
    }
}