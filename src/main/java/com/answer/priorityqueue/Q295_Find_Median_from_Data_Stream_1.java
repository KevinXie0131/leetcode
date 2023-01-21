package com.answer.priorityqueue;

import java.util.*;

public class Q295_Find_Median_from_Data_Stream_1 {

    TreeMap<Integer, Integer> head = new TreeMap<>(), tail = new TreeMap<>();
    int[] arr = new int[101];
    int a, b, c;

    public void addNum(int num) {
        if (num >= 0 && num <= 100) {
            arr[num]++;
            b++;
        } else if (num < 0) {
            head.put(num, head.getOrDefault(num, 0) + 1);
            a++;
        } else if (num > 100) {
            tail.put(num, tail.getOrDefault(num, 0) + 1);
            c++;
        }
    }

    public double findMedian() {
        int size = a + b + c;
        if (size % 2 == 0) return (find(size / 2) + find(size / 2 + 1)) / 2.0;
        return find(size / 2 + 1);
    }

    int find(int n) {
        if (n <= a) {
            for (int num : head.keySet()) {
                n -= head.get(num);
                if (n <= 0) return num;
            }
        } else if (n <= a + b) {
            n -= a;
            for (int i = 0; i <= 100; i++) {
                n -= arr[i];
                if (n <= 0) return i;
            }
        } else {
            n -= a + b;
            for (int num : tail.keySet()) {
                n -= tail.get(num);
                if (n <= 0) return num;
            }
        }
        return -1; // never
    }
}
