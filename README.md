```shell
$ mvn clean install
```

```shell
$ java -jar target/benchmarks.jar BenchmarkLoop
```

```shell
$ mvn clean install -DskipTest jmh:benchmark
```

```shell
Benchmark                      (iter)  (n)  Mode  Cnt     Score     Error  Units
BenchmarkLoopTest.collections     100    8  avgt   15    11.425 ?   0.734  us/op
BenchmarkLoopTest.collections     500    8  avgt   15    63.935 ?   4.628  us/op
BenchmarkLoopTest.stream          100    8  avgt   15    96.749 ?   9.386  us/op
BenchmarkLoopTest.stream          500    8  avgt   15  2249.570 ? 162.159  us/op
```