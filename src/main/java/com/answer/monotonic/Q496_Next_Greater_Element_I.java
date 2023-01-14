package com.answer.monotonic;

import java.util.*;

public class Q496_Next_Greater_Element_I {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        Stack<Integer> stack = new Stack<>();
        Map<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[i] > nums2[stack.peek()]) {
                int prevIndex = stack.pop();
                map.put(nums2[prevIndex], nums2[i]);
            }
            stack.push(i);
        }

        for (int i = 0 ; i < nums1.length; i++) {
            result[i] = map.getOrDefault(nums1[i], -1);
        }
        return result;
    }
}
