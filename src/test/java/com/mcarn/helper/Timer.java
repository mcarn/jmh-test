package com.mcarn.helper;

import java.time.Duration;
import java.time.Instant;

public class Timer {

  public static long doTimed(Runnable runnable) {
    var start = Instant.now();

    runnable.run();

    var end = Instant.now();
    return Duration.between(start, end).toMillis();
  }
}
