package com.greenfoxacademy.p2pchat.utils;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Settings {

    public static final List<Integer> PAGE_SIZES = List.of(5, 8);
    public static Integer currentPageSize = PAGE_SIZES.get(0);
    public static SortType currentSortType = SortType.TIME_DESCENDING;
}