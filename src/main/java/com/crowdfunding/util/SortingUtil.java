package com.crowdfunding.util;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingUtil {
    public static <T> void sortList(List<T> list, Comparator<T> comparator) {
        Collections.sort(list, comparator);
    }
}