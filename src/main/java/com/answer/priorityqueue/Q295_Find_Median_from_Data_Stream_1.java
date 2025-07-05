package com.answer.priorityqueue;

import java.util.*;

public class Q295_Find_Median_from_Data_Stream_1 {
    /**
     * Follow up:
     * If all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
     * 1. 如果数据流中所有整数都在 0 到 100 范围内，你将如何优化你的算法？
     * If 99% of all integer numbers from the stream are in the range [0, 100], how would you optimize your solution?
     * 2. 如果数据流中 99% 的整数都在 0 到 100 范围内，你将如何优化你的算法？
     */
    // 1. 可以使用建立长度为 101 的桶，每个桶分别统计每个数的出现次数，同时记录数据流中总的元素数量，每次查找中位数时，
    //    先计算出中位数是第几位，从前往后扫描所有的桶得到答案。
    // 2. 和上一问解法类似，对于 1% 采用哨兵机制进行解决即可，在常规的最小桶和最大桶两侧分别维护一个有序序列，即建立一个代表负无穷和正无穷的桶。
    TreeMap<Integer, Integer> head = new TreeMap<>(), tail = new TreeMap<>();
    int[] arr = new int[101];
    int a, b, c;
    // 上述两个进阶问题的代码如下，但注意由于真实样例的数据分布不是进阶所描述的那样（不是绝大多数都在 [0,100] 范围内），会 Time Limit Exceeded。
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
        if (size % 2 == 0) {
            return (find(size / 2) + find(size / 2 + 1)) / 2.0;
        }
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
