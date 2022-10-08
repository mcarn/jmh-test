package com.mcarn;

import org.apache.commons.lang3.RandomStringUtils;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
@Warmup(iterations = 2)
@Measurement(iterations = 3)
public class BenchmarkLoopTest {

    @Param({"100", "500", "1000", "5000", "10000", "50000", "100000"})
    private int iter;

    @Param({"8"})
    private int n;

    private List<String> l1;
    private List<String> l2;

    public static void main(String[] args) throws RunnerException {

        Options opt = new OptionsBuilder()
                .include(BenchmarkLoopTest.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(opt).run();
    }

    @Setup
    public void setup() {
        l1 = createData();
        l2 = createData();
    }

    @Benchmark
    public void not(Blackhole bh) {
        var toAdd = l1.stream().filter(f -> !l2.contains(f)).collect(Collectors.toList());
        var toRemove = l2.stream().filter(f -> !l1.contains(f)).collect(Collectors.toList());

        bh.consume(toAdd);
        bh.consume(toRemove);
    }

    @Benchmark
    public void set(Blackhole bh) {
        var s1 = new HashSet<>(l1);
        var s2 = new HashSet<>(l2);

        var toAdd = l1.stream().filter(f -> !s2.contains(f)).collect(Collectors.toList());
        var toRemove = l2.stream().filter(f -> !s1.contains(f)).collect(Collectors.toList());

        bh.consume(toAdd);
        bh.consume(toRemove);
    }

    private List<String> createData() {
        return IntStream.range(0, iter)
                .mapToObj(i -> RandomStringUtils.randomAlphabetic(n))
                .distinct()
                .collect(Collectors.toList());
    }
}
