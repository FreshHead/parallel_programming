package ru.univeralex.sorter;

import org.junit.Before;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class ForkJoinMergeSorterTest {

    private List<Integer> list;

    @Before
    public void setUp(){
        list = new Random().ints(100_000).boxed().collect(Collectors.toList());
    }

    @org.junit.Test
    public void sort() {
        List<Integer> expectedList = list.stream().sorted().collect(Collectors.toList());
        ForkJoinMergeSorter forkJoinMergeSorter = new ForkJoinMergeSorter(list);
        ForkJoinPool pool = new ForkJoinPool();
        List<Integer> sortedList = (List<Integer>) pool.invoke(forkJoinMergeSorter);
        sortedList.forEach(System.out::println);
        assertEquals(expectedList, sortedList);
    }
}