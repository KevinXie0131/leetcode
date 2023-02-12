package com.answer.sliding_window;

import java.util.Arrays;

public class Q1423_Maximum_Points_You_Can_Obtain_from_Cards {
    public static void main(String[] args) {
        int[] cardPoints = {1,2,3,4,5,6,1};
        int k = 3;
        System.out.println(maxScore_1(cardPoints, k));
    }

    /**
     * Approach 3: Sliding Window
     *
     * At each point, we choose a card either from the beginning or from the end of the array.
     */
    public static int maxScore(int[] cardPoints, int k) {
        int res = 0;
        int sum = 0;
        int n = cardPoints.length;
        for (int i = 0; i < k; i++) {
            sum += cardPoints[i];
        }
        res = Math.max(res, sum);
        for (int j = 1; j <= k; j++) {
            sum += cardPoints[n - j] - cardPoints[k - j];
            res = Math.max(res, sum);
        }
        return res;
    }
    /**
     * Official answer
     */
    public static int maxScore_1(int[] cardPoints, int k) {
        int n = cardPoints.length;
        // 滑动窗口大小为 n-k
        int windowSize = n - k;
        // 选前 n-k 个作为初始值
        int sum = 0;
        for (int i = 0; i < windowSize; ++i) {
            sum += cardPoints[i];
        }
        int minSum = sum;
        for (int i = windowSize; i < n; ++i) {
            // 滑动窗口每向右移动一格，增加从右侧进入窗口的元素值，并减少从左侧离开窗口的元素值
            sum += cardPoints[i] - cardPoints[i - windowSize];
            minSum = Math.min(minSum, sum);
        }
        return Arrays.stream(cardPoints).sum() - minSum;
    }
}
