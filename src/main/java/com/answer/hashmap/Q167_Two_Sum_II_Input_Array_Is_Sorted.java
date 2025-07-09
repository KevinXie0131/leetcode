package com.answer.hashmap;

import java.util.*;

public class Q167_Two_Sum_II_Input_Array_Is_Sorted {
    /**
     * 两数之和 II - 输入有序数组
     * 给你一个下标从 1 (1-indexed)开始的整数数组 numbers ，该数组已按 非递减顺序排列 (sorted in non-decreasing order) ，请你从数组中找出满足相加之和等于目标数 target 的两个数。如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
     * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
     * 你可以假设每个输入 只对应唯一的答案exactly one solution ，而且你 不可以 重复使用相同的元素。
     * 你所设计的解决方案必须只使用常量级的额外空间。
     *
     * 示例 1：
     *  输入：numbers = [2,7,11,15], target = 9
     *  输出：[1,2]
     *  解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
     * 示例 2：
     *  输入：numbers = [2,3,4], target = 6
     *  输出：[1,3]
     *  解释：2 与 4 之和等于目标数 6 。因此 index1 = 1, index2 = 3 。返回 [1, 3] 。
     * 示例 3：
     *  输入：numbers = [-1,0], target = -1
     *  输出：[1,2]
     *  解释：-1 与 0 之和等于目标数 -1 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
     */
    /**
     * Similar problem:
     * 170. Two Sum III - Data structure design
     * https://leetcode.com/problems/two-sum-iii-data-structure-design/solution/
     */
    /**
     * Two points 双指针 (由于输入数组有序（非严格递增）)
     * already sorted in non-decreasing order
     * Let these two numbers be numbers[index1] and numbers[index2] where 1 <= index1 < index2 <= numbers.length.
     * Your solution must use only constant extra space.
     *
     * Time Complexity O(n)
     * Space Complexity O(1)
     */
    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        while(i < j){
            int sum = numbers[i] + numbers[j];

            if(sum == target){
                return new int[]{i + 1, j + 1};
            } else if(sum > target){
                j--;
            }else{
                i++;
            }
        }
        return null;
    }
    /**
     * Binary Search 二分查找
     * Time Complexity O(nlogn)
     * Space Complexity O(1)
     */
    public int[] twoSum_1(int[] numbers, int target) {
        for(int i = 0; i < numbers.length; i++){
            int left = i + 1; // 为了避免重复寻找，在寻找第二个数时，只在第一个数的右侧寻找。
            int right = numbers.length - 1;
            while(left <= right){
                int mid = (left + right) >>> 1;
                if(numbers[mid] == target - numbers[i]){
                    return new int[]{i + 1, mid + 1};
                }else if(numbers[mid] < target - numbers[i]){
                    left = mid + 1;
                }else{
                    right = mid - 1;
                }
            }
        }
        return null;
    }
    /**
     * Q1 TWo Sum的Hashmap方法也可以
     * Time Complexity O(n)
     * Space Complexity O(n)
     */
    public int[] twoSum3(int[] numbers, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];

        for(int i = 0; i < numbers.length; i++){
            int temp = target - numbers[i]; // 遍历当前元素，并在map中寻找是否有匹配的key
            if(map.containsKey(numbers[i])){
                result[0] = map.get(numbers[i]) + 1; // 1-indexed array of integers 所以加1
                result[1] = i + 1;
            }

            map.put(temp, i);    // 如果没找到匹配对，就把访问过的元素和下标加入到map中
        }
        return result;
    }
}
