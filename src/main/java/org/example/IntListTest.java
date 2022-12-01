package org.example;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.List;

@State(Scope.Benchmark)
public class IntListTest {

    @Param({"100", "10000", "1000000"})
    public int listSize;

    @Benchmark
    public IntList fastutilIntListInsertion() {
        IntList intList = new IntArrayList(listSize);
        for (int i = 0; i < listSize; i++) {
            intList.add(i);
        }
        return intList;
    }

    @Benchmark
    public List<Integer> genericIntListInsertion() {
        List<Integer> intList = new ArrayList<>(listSize);
        for (int i = 0; i < listSize; i++) {
            intList.add(i);
        }
        return intList;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(IntListTest.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }
}
