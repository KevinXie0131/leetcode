package com.answer.sliding_window;

import java.util.*;

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
        int[] cardPoints = {100,40,17,9,73,75};
        int k = 3;
        System.out.println(maxScore5(cardPoints, k));
    }
    /**
     * 滑动窗口 / 逆向思维
     * 拿走 k 张，剩下 n−k 张。这里 n 是 cardPoints 的长度。为了最大化拿走的点数和，应当最小化剩下的点数和。
     * 由于只能从开头或末尾拿牌，所以最后剩下的牌必然是连续的。
     * 至此，问题变成：计算长为 n−k 的连续子数组和的最小值。
     */
    static public int maxScore1(int[] cardPoints, int k) {
        int min = 0, sum = 0, total = 0;
        int len = cardPoints.length;

        for(int i = 0; i < len; i++){
            total += cardPoints[i];
        }

        for(int i = 0; i < len - k; i++){
            sum += cardPoints[i];
        }

        min = sum;

        for(int i = len - k; i < len; i++){
            sum += cardPoints[i];
            sum -= cardPoints[i - (len - k)];
            min = Math.min(min, sum);
        }
        return total - min;
    }
    /**
     * another form
     * 原问题等价为：从 cardPoints 中找长度为 n - k 的连续段，使其总和最小。
     */
    public int maxScore3(int[] cardPoints, int k) {
        int min = 0, sum = 0, total = 0;
        int len = cardPoints.length;

        for(int i = 0; i < len - k; i++){
            sum += cardPoints[i];
        }

        min = sum;
        total = sum;

        for(int i = len - k; i < len; i++){
            total += cardPoints[i];
            sum += cardPoints[i] - cardPoints[i - (len - k)];
            min = Math.min(min, sum);
        }
        return total - min;
    }
    /**
     * 正向思维
     * 答案等于如下结果的最大值：
     *  前 k 个数的和。
     *  前 k−1 个数以及后 1 个数的和。
     *  前 k−2 个数以及后 2 个数的和。
     *  ……
     *  前 2 个数以及后 k−2 个数的和。
     *  前 1 个数以及后 k−1 个数的和。
     *  后 k 个数的和。
     */
    public int maxScore4(int[] cardPoints, int k) {
        int s = 0;
        for (int i = 0; i < k; i++) {
            s += cardPoints[i]; // 计算前 k 个数的和
        }
        int ans = s;
        for (int i = 1; i <= k; i++) { // 从 i=1 开始枚举到 i=k
            s += cardPoints[cardPoints.length - i] - cardPoints[k - i]; // 每次枚举，把 s 增加 cardPoints[n−i]−cardPoints[k−i]，然后更新 ans 的最大值
            ans = Math.max(ans, s);
        }
        return ans;
    }
    /**
     * preSum前缀和
     * 抽走的卡牌点数之和 = cardPoints 所有元素之和 - 剩余的中间部分元素之和。
     * 利用 preSum 数组，可以在 O(1) 的时间内快速求出 nums 任意区间 [i, j]  (两端都包含) 的各元素之和
     * sum(i, j) = preSum[j + 1] - preSum[i]
     */
    static public int maxScore5(int[] cardPoints, int k) {
        int len = cardPoints.length;
        int[] preSum = new int[len + 1];
        for(int i = 0; i < len; i++){
            preSum[i + 1] = preSum[i] + cardPoints[i]; // 先求 preSum
        }
        // cards:     [100, 40,  17,  9,   73,  75]
        // preSum: [0, 100, 140, 157, 166, 239, 314]
        int min = Integer.MAX_VALUE;
        int size = len - k;
        // 使用一个 0 ~ k 的遍历表示从左边拿走的元素数，然后根据窗口大小 windowSize = len - k ，
        // 利用 preSum 快速求窗口内元素之和
        for(int i = 0; i <= k; i++){
            min = Math.min(min, preSum[i + size] - preSum[i]);
        }
        return preSum[len] - min;
    }
    /**
     * Approach 3: Sliding Window 正向思维
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
     * 和 643. 子数组最大平均数 I 代码很相似
     */
    public static int maxScore_1(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int windowSize = n - k; // 滑动窗口大小为 n-k
        int sum = 0;

        for (int i = 0; i < windowSize; ++i) {// 选前 n-k 个作为初始值
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
