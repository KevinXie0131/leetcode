package com.answer.greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.IntStream;

public class Q1005_Maximize_Sum_Of_Array_After_K_Negations {
    public static void main(String[] args) {
        int[] nums = {3,-1,0,2};
        int k = 3;
        System.out.println(largestSumAfterKNegations(nums, k));
    }
    /**
     * Greedy Algorithm （K次取反后最大化的数组和，可以多次选择同一个索引）
     * 贪心的思路，局部最优：让绝对值大的负数变为正数，当前数值达到最大，整体最优：整个数组和达到最大。
     * 局部最优可以推出全局最优。
     *
     * 题是一个有序正整数序列，如何转变K次正负，让 数组和 达到最大。
     * 那么又是一个贪心：局部最优：只找数值最小的正整数进行反转，当前数值和可以达到最大（例如正整数数组{5, 3, 1}，反转1 得到-1 比 反转5得到的-5 大多了），全局最优：整个 数组和 达到最大
     *
     * 解题步骤为：
     *  第一步：将数组按照绝对值大小从大到小排序，注意要按照绝对值的大小
     *  第二步：从前向后遍历，遇到负数将其变为正数，同时K--
     *  第三步：如果K还大于0，那么反复转变数值最小的元素，将K用完
     *  第四步：求和
     *
     * 时间复杂度: O(nlogn)
     */
    public static  int largestSumAfterKNegations(int[] nums, int k) {
  //      Integer[] array = Arrays.stream(nums).boxed().toArray(Integer[]::new);
  //      Arrays.sort(array, Comparator.comparingInt(Math::abs));
  //      Arrays.sort(array, (o1, o2) -> Math.abs(o2) - Math.abs(o1) );

        // 将数组按照绝对值大小从大到小排序，注意要按照绝对值的大小
        nums = IntStream.of(nums) // 第一步
                .boxed()
                .sorted((o1, o2) -> Math.abs(o2) - Math.abs(o1))
                .mapToInt(Integer::intValue).toArray();

        //从前向后遍历，遇到负数将其变为正数，同时K--
        for(int i = 0; i < nums.length && k > 0; i++){  // 第二步
            if(nums[i] < 0){
                nums[i] = -nums[i];
                k--;
            }
        }
        // 如果K还大于0，那么反复转变数值最小的元素，将K用完
        if(k % 2 == 1) {
            nums[nums.length - 1] = -nums[nums.length - 1];// 第三步
        }

        return Arrays.stream(nums).sum();// 第四步
    }

}
