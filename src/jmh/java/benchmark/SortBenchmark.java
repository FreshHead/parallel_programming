package benchmark;

import org.openjdk.jmh.annotations.*;
import ru.univeralex.sorter.MergeSorter;

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

    private List<Integer> list;

    @Setup
    public void setUp(){
        list = new Random().ints(10_000).boxed().collect(Collectors.toList());
    }

    @Benchmark
    public List<Integer> sequentialSort() {
        return MergeSorter.sort(list);
    }
}
