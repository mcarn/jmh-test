package com.mcarn.util;

import com.mcarn.helper.Timer;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ListUtilTest {

  int length = 100000;

  double overlap = 0.1;


  List<String> list1;
  List<String> list2;

  @BeforeAll
  void setup() {
    List<String> list1 = new ArrayList<>();
    List<String> list2 = new ArrayList<>();
    for (int i = 0; i < length; i++) {
      if ((i % (100 * overlap)) == 0) {
        var newItem = UUID.randomUUID().toString();
        list1.add(newItem);
        list2.add(newItem);
      } else {
        list1.add(UUID.randomUUID().toString());
        list2.add(UUID.randomUUID().toString());
      }
    }
    this.list1 = list1;
    this.list2 = list2;
  }

  @Test
  void list() {
    var elapsed = Timer.doTimed(() -> ListUtil.functionalList(list1, list2));

    assertThat(elapsed)
        .isNotZero()
        .isGreaterThan(100);
  }

  @Test
  void set() {
    var elapsed = Timer.doTimed(() -> ListUtil.functionalSet(list1, list2));

    assertThat(elapsed)
        .isNotZero()
        .isLessThan(100);
  }
}