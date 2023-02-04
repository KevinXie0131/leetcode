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
     * Greedy Algorithm
     */
    public static  int largestSumAfterKNegations(int[] nums, int k) {

        Integer[] array = Arrays.stream( nums ).boxed().toArray( Integer[]::new );
        Arrays.sort(array, Comparator.comparingInt(Math::abs));
        Arrays.sort(array, (o1, o2) -> Math.abs(o2) - Math.abs(o1) );

        nums = IntStream.of(nums)
                .boxed()
                .sorted((o1, o2) -> Math.abs(o2) - Math.abs(o1))
                .mapToInt(Integer::intValue).toArray();

        for(int i = 0; i < nums.length && k > 0; i++){
            if(nums[i] < 0){
                nums[i] = -nums[i];
                k--;
            }
        }
        if(k%2 == 1) {
            nums[nums.length - 1] = -nums[nums.length - 1];
        }

        return Arrays.stream(nums).sum();
    }

}
