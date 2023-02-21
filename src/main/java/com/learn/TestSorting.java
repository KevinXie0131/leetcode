package com.learn;

import java.util.Arrays;
import java.util.Comparator;

public class TestSorting {
    public static void main(String[] args) {
        String[] array = {"aaa", "b", "cc"};

        // Sorts a list of string by length of each string
        Arrays.sort(array, new StringCompare());

        System.out.println(Arrays.toString(array));
    }
}

class StringCompare implements Comparator<String> {
    public int compare(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return 1;
        } else if (s1.length() < s2.length()) {
            return -1;
        }
        return 0;
    }
}
