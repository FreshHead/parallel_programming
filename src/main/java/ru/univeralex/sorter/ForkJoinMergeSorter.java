package ru.univeralex.sorter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

/**
 * Пока
 */
public class ForkJoinMergeSorter extends RecursiveTask {

    private List<Integer> list;

    ForkJoinMergeSorter(List<Integer> list) {
        this.list = list;
    }

    @Override
    protected List<Integer> compute() {
        if (list.size() == 1) {
            return list;
        }
        List<? extends Integer> sub = list.subList(list.size() / 2, list.size());
        List<Integer> two = new ArrayList<>(sub);
        sub.clear();
            List<Integer> first = (List<Integer>) new ForkJoinMergeSorter(list).invoke();
            List<Integer> second = (List<Integer>) new ForkJoinMergeSorter(two).invoke();
            return merge(first, second);
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
