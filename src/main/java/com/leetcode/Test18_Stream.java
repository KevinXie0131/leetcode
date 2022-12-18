package com.leetcode;

import java.util.Arrays;

public class Test18_Stream {

    public static void main(String[] args) {

        int [] nums = { 1, 3, 5, 6, 9};

        int sum = Arrays.stream(nums).sum();

        System.out.println(sum);
    }
}
