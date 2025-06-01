package com.answer.sliding_window;

import java.util.Arrays;

public class Q1423_Maximum_Points_You_Can_Obtain_from_Cards {
    /**
     * There are several cards arranged in a row, and each card has an associated number of points.
     * The points are given in the integer array cardPoints.
     * In one step, you can take one card from the beginning or from the end of the row. You have to take exactly k cards.
     * Your score is the sum of the points of the cards you have taken.
     * Given the integer array cardPoints and the integer k, return the maximum score you can obtain.
     * 可获得的最大点数: 几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
     * 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。你的点数就是你拿到手中的所有卡牌的点数之和。
     * 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
     * 示例 1：
     *  输入：cardPoints = [1,2,3,4,5,6,1], k = 3
     *  输出：12
     *  解释：第一次行动，不管拿哪张牌，你的点数总是 1 。但是，先拿最右边的卡牌将会最大化你的可获得点数。最优策略是拿右边的三张牌，最终点数为 1 + 6 + 5 = 12 。
     * 示例 5：
     *  输入：cardPoints = [1,79,80,1,1,1,200,1], k = 3
     *  输出：202
     */
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
