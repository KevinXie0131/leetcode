package com.answer.array;

import java.util.Arrays;
import java.util.HashMap;

public class Q1365_How_Many_Numbers_Are_Smaller_Than_the_Current_Number {
    public static void main(String[] args) {
      int []nums = {8,1,2,2,3};
      int[] result = smallerNumbersThanCurrent(nums);
      System.out.println(Arrays.toString(result));
    }
    /**
     * 可以排序之后加哈希，时间复杂度为O(n\log n)
     */
    static public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] result = new int[nums.length];
        int[] rank = new int[101];
        int[] copyNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copyNums); // 从小到大排序之后, 其实每一个数值的下标就代表这前面有几个比它小的了。元素下标就是小于当前数字的数字

        for(int i = copyNums.length - 1; i >= 0; i--){ // 从后向前，记录 vec[i] 对应的下标
            rank[copyNums[i]] = i; // 在构造数组hash的时候，从后向前遍历，这样hash里存放的就是相同元素最左面的数值和下标了。
        }
        // 此时hash里保存的每一个元素数值 对应的 小于这个数值的个数
        for(int i = 0; i < result.length; i++){
            result[i] = rank[nums[i]];
        }
        return result;
    }
    /**
     * HashMap
     */
    public int[] smallerNumbersThanCurrent2(int[] nums) {
        int[] result = new int[nums.length];
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] copyNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(copyNums);

        for(int i = copyNums.length - 1; i >= 0; i--){
            map.put(copyNums[i], i);
        }
/*
        for (int i = 0; i < copyNums.length; i++) {
            if (!map.containsKey(copyNums[i])) { // 遇到了相同的数字，那么不需要更新该 number 的情况
                map.put(copyNums[i], i);
            }
        }
*/
        for(int i = 0; i < result.length; i++){
            result[i] = map.get(nums[i]);
        }
        return result;
    }
}
