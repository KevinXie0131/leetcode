package com.answer.hashmap;

public class Q167_Two_Sum_II_Input_Array_Is_Sorted {
    /**
     * Two points
     */
    public int[] twoSum(int[] numbers, int target) {
        int i = 0;
        int j = numbers.length - 1;
        while(i<j){
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
     */
    public int[] twoSum_1(int[] numbers, int target) {
        for(int i = 0;i< numbers.length; i++){
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

}
