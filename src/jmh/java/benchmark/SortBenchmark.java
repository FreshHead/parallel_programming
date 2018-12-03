package benchmark;

import org.openjdk.jmh.annotations.*;
import ru.univeralex.sorter.ForkJoinMergeSorter;
import ru.univeralex.sorter.SequentialMergeSorter;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(jvmArgs = {"-Xms4G", "-Xmx4G"})
public class SortBenchmark {

    private List<Integer> list10;
    private List<Integer> list100;
    private List<Integer> list1000;
    private List<Integer> list10_000;
    private List<Integer> list100_000;

    @Setup
    public void setUp(){
        list10 = new Random().ints(10).boxed().collect(Collectors.toList());
        list100 = new Random().ints(100).boxed().collect(Collectors.toList());
        list1000 = new Random().ints(1000).boxed().collect(Collectors.toList());
        list10_000 = new Random().ints(10_000).boxed().collect(Collectors.toList());
        list100_000 = new Random().ints(100_000).boxed().collect(Collectors.toList());
    }

//    @Benchmark
//    public List<Integer> sequentialSort_10() {
//        return SequentialMergeSorter.sort(list10);
//    }
//
//    @Benchmark
//    public List<Integer> sequentialSort_100() {
//        return SequentialMergeSorter.sort(list100);
//    }
//
//    @Benchmark
//    public List<Integer> sequentialSort_1000() {
//        return SequentialMergeSorter.sort(list1000);
//    }
//
    @Benchmark
    public List<Integer> sequentialSort_10_000() {
        return SequentialMergeSorter.sort(list10_000);
    }
//
//    @Benchmark
//    public List<Integer> sequentialSort_100_000() {
//        return SequentialMergeSorter.sort(list100_000);
//    }
//
//    @Benchmark
//    public List<Integer> parallelSort_10() {return new ForkJoinMergeSorter(list10).sort();}
//
//    @Benchmark
//    public List<Integer> parallelSort_100() {
//        return new ForkJoinMergeSorter(list100).sort();
//    }
//
//    @Benchmark
//    public List<Integer> parallelSort_1000() {
//        return new ForkJoinMergeSorter(list1000).sort();
//    }
//
    @Benchmark
    public List<Integer> parallelSort_10_000() {
        return new ForkJoinMergeSorter(list10_000).sort();
    }
//
//    @Benchmark
//    public List<Integer> parallelSort_100_000() {
//        return new ForkJoinMergeSorter(list100_000).sort();
//    }

}
