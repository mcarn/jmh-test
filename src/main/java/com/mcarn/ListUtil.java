package com.mcarn;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class ListUtil {

  public static <T> List<T> functionalList(Collection<T> list1, Collection<T> list2) {
    return list1.stream()
        .filter(l -> !list2.contains(l))
        .collect(Collectors.toList());
  }

  public static <T> List<T> functionalSet(Collection<T> list1, Collection<T> list2) {
    var set = new HashSet<>(list2);
    return list1.stream()
        .filter(l -> !set.contains(l))
        .collect(Collectors.toList());
  }
}
