package com.learn.template;

public class Binary_Search_Greedy {
    private static final int MINIMUM_POSSIBLE_ANSWER = 1;
    private static final int MAXIMUM_POSSIBLE_ANSWER = 2;
    /**
     * 二分查找: 贪心问题
     */
    /**
     * 寻找最小值
     */
    public int fn(int[] arr) {
        int left = MINIMUM_POSSIBLE_ANSWER;
        int right = MAXIMUM_POSSIBLE_ANSWER;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (check(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    /**
     * 寻找最大值
     */
    public int fn1(int[] arr) {
        int left = MINIMUM_POSSIBLE_ANSWER;
        int right = MAXIMUM_POSSIBLE_ANSWER;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (check(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    public boolean check(int x) {
        // 这个函数的具体实现取决于问题
        return true;
    }
}
