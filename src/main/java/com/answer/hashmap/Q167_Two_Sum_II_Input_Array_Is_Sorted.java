package com.answer.hashmap;

import java.util.*;

public class Q167_Two_Sum_II_Input_Array_Is_Sorted {
    /**
     * Similar problem:
     * 170. Two Sum III - Data structure design
     * https://leetcode.com/problems/two-sum-iii-data-structure-design/solution/
     */
    /**
     * Two points
     *
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
     * Binary Search
     * Time Complexity O(nlogn)
     * Space Complexity O(1)
     */
    public int[] twoSum_1(int[] numbers, int target) {
        for(int i = 0; i< numbers.length; i++){
            int left = i + 1;
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
