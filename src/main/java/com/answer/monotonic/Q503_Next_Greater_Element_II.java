package com.answer.monotonic;

import java.util.*;

public class Q503_Next_Greater_Element_II {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i=0; i < 2*nums.length; i++){
            while(!stack.isEmpty() && nums[i%n] > nums[stack.peek()]){
                int preIndex = stack.pop();
                result[preIndex] = nums[i%n];
            }

            stack.push(i%n);
        }

        return result;

    }
}
