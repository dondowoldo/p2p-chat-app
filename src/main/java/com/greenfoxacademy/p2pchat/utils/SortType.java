package com.greenfoxacademy.p2pchat.utils;

public enum SortType {
    TIME_ASCENDING("Oldest First"),
    TIME_DESCENDING("Newest First"),;
    private String name;

    SortType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}