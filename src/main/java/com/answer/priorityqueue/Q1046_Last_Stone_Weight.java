package com.answer.priorityqueue;

public class Q1046_Last_Stone_Weight {
    /**
     * 最后一块石头的重量
     * 有一堆石头，每块石头的重量都是正整数。
     * You are given an array of integers stones where stones[i] is the weight of the ith stone.
     * 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
     *  如果 x == y，那么两块石头都会被完全粉碎；
     *  如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
     * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
     * On each turn, we choose the heaviest two stones and smash them together. Suppose the heaviest two stones have weights x and y with x <= y. The result of this smash is:
     *  If x == y, both stones are destroyed, and
     *  If x != y, the stone of weight x is destroyed, and the stone of weight y has new weight y - x.
     * At the end of the game, there is at most one stone left.
     * Return the weight of the last remaining stone. If there are no stones left, return 0.
     *
     * 示例：
     *  输入：[2,7,4,1,8,1]
     *  输出：1
     *  解释：先选出 7 和 8，得到 1，所以数组转换为 [2,4,1,1,1]，
     *      再选出 2 和 4，得到 2，所以数组转换为 [2,1,1,1]，
     *      接着是 2 和 1，得到 1，所以数组转换为 [1,1,1]，
     *      最后选出 1 和 1，得到 0，最终数组转换为 [1]，这就是最后剩下那块石头的重量。
     *
     */
}
