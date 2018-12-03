package ru.univeralex.sorter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static java.util.concurrent.ForkJoinPool.defaultForkJoinWorkerThreadFactory;

/**
 * Пока
 */
public class ForkJoinMergeSorter extends RecursiveTask {

    private List<Integer> list;

    public ForkJoinMergeSorter(List<Integer> list) {
        this.list = list;
    }

    public List<Integer> sort() {
        ForkJoinPool pool = new ForkJoinPool();
//        ForkJoinPool pool = new ForkJoinPool(4, defaultForkJoinWorkerThreadFactory, null, true);
        return (List<Integer>) pool.invoke(this);
    }

    @Override
    protected List<Integer> compute() {
//        System.out.println(Thread.currentThread().getName());
        if (list.size() <= 1000) {
            return SequentialMergeSorter.sort(list);
        }
        List<? extends Integer> sub = list.subList(0, list.size() / 2);
        List<Integer> two = new ArrayList<>(sub);
        sub.clear();
        ForkJoinTask<List<Integer>> t1 = new ForkJoinMergeSorter(list);
        ForkJoinTask<List<Integer>> t2 = new ForkJoinMergeSorter(two);
        invokeAll(t1, t2);
        return merge(t1.join(), t2.join()); // Выгоднее джойнить в обратном порядке от fork.
    }

    private static List<Integer> merge(List<Integer> firstList, List<Integer> secondList) {
        List<Integer> result = new ArrayList<>();
        while (firstList.size() > 0 && secondList.size() > 0) {
            if (firstList.get(0) < secondList.get(0)) {
                result.add(firstList.remove(0));
            } else {
                result.add(secondList.remove(0));
            }
        }
        if (firstList.isEmpty()) {
            result.addAll(secondList);
        } else {
            result.addAll(firstList);
        }
        return result;
    }

}
