package com.answer.greedy;

import java.util.Arrays;

public class Q135_Candy { // Hard 困难
    /**
     * 分发糖果
     * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
     * 你需要按照以下要求，给这些孩子分发糖果：
     *  每个孩子至少分配到 1 个糖果。
     *  相邻两个孩子评分更高的孩子会获得更多的糖果。
     * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
     * 示例 1：
     *  输入：ratings = [1,0,2]
     *  输出：5
     *  解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
     * 示例 2：
     *  输入：ratings = [1,2,2]
     *  输出：4
     *  解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
     *          第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
     */
    public static void main(String[] args) {
       int[] ratings = {1,0,2};
       System.out.println(candy1(ratings));
    }
    /**
     * Greedy
     * Approach 3: Using one array
     * 先确定右边评分大于左边的情况（也就是从前向后遍历）
     * 此时局部最优：只要右边评分比左边大，右边的孩子就多一个糖果，全局最优：相邻的孩子中，评分高的右孩子获得比左边孩子更多的糖果
     * 局部最优可以推出全局最优。
     *
     * 再确定左孩子大于右孩子的情况（从后向前遍历）
     * 局部最优：取candyVec[i + 1] + 1 和 candyVec[i] 最大的糖果数量，保证第i个小孩的糖果数量既大于左边的也大于右边的。
     * 全局最优：相邻的孩子中，评分高的孩子获得更多的糖果
     *
     * 采用了两次贪心的策略：
     *  一次是从左到右遍历，只比较右边孩子评分比左边大的情况。
     *  一次是从右到左遍历，只比较左边孩子评分比右边大的情况。
     * 这样从局部最优推出了全局最优，即：相邻的孩子中，评分高的孩子获得更多的糖果
     */
    public int candy(int[] ratings) {
        int[] candy  = new int[ratings.length];
        Arrays.fill(candy, 1);

        for(int i = 1; i < ratings.length; i++){ // 从前向后
            if(ratings[i] > ratings[i - 1]){
                candy[i] = candy[i - 1] + 1; // 1、起点下标1 从左往右，只要 右边 比 左边 大，右边的糖果=左边 + 1
            }
        }
        for(int i = ratings.length - 2; i >= 0; i--) { // 从后向前
            if(ratings[i] > ratings[i + 1]) {
                // 取candyVec[i + 1] + 1 和 candyVec[i] 最大的糖果数量，
                // candyVec[i]只有取最大的才能既保持对左边candyVec[i - 1]的糖果多，也比右边candyVec[i + 1]的糖果多
                candy[i] = Math.max(candy[i], candy[i + 1] + 1); // 2、起点下标 ratings.length - 2 从右往左， 只要左边 比 右边 大，此时 左边的糖果应该 取本身的糖果数（符合比它左边大） 和 右边糖果数 + 1 二者的最大值，这样才符合 它比它左边的大，也比它右边大
            }
        }
        int sum = 0;
        for(int n : candy){  // 统计结果
            sum += n;
        }
        return sum;
    }
    /**
     * 另一种形式
     * 根据每个孩子左（右）侧比当前孩子得分低的相邻单调递增（减）区间内的孩子数量，确定能分配的最少糖果数量，最后累加即可。
     */
   static public int candy1(int[] ratings) {
        int[] left  = new int[ratings.length];
        int[] right  = new int[ratings.length];

        for(int i = 1; i < ratings.length; i++){ // 从前向后
            if(ratings[i] > ratings[i - 1]){
                left[i] = left[i - 1] + 1;
            }
        }
        for(int i = ratings.length - 2; i >= 0; i--) { // 从后向前
            if(ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
        }
        int sum = 0;
        for(int i = 0; i < ratings.length; i++){  // 统计结果
            sum += Math.max(left[i], right[i]) + 1; // 每个人至少分一个糖果
        }
        return sum;
    }
    /**
     * 贪心
     * 时间复杂度 O(N) ： 遍历两遍数组即可得到结果；
     * 空间复杂度 O(N) ： 需要借用 left，right 的线性额外空间。
     */
     public int candy3(int[] ratings) {
        int[] left = new int[ratings.length];
        int[] right = new int[ratings.length];
        Arrays.fill(left, 1);
        Arrays.fill(right, 1);

        for(int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }

        int count = left[ratings.length - 1];
        for(int i = ratings.length - 2; i >= 0; i--) {
            if(ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            }
            count += Math.max(left[i], right[i]);
        }
        return count;
     }
}
