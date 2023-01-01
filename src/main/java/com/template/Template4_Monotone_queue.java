package com.template;

import java.util.Deque;
import java.util.LinkedList;

public class Template4_Monotone_queue {
    public static void main(String[] args) {

        int[] nums = {1,3,-1,-3,-2,5,3,6,7};
        int k = 3;
        int n = nums.length;

        Deque<Integer> deque = new LinkedList<Integer>();

        for (int i = 0; i < n; ++i) {
            while (!deque.isEmpty() && nums[i] >= deque.peekLast()) {
                deque.pollLast();
             //   System.out.println(deque);
            }
            deque.offerLast(nums[i]);
            System.out.println(deque);
        }

        // 1,3,-1,-3,-2,5,3,6,7
/*        deque.offerFirst(nums[0]);
        for (int i = 1; i < n; ++i) {
            while(!deque.isEmpty() && deque.peekLast().compareTo(nums[i]) >= 0) {
                deque.pollLast();
            }
            deque.offerLast(nums[i]);
            System.out.println(deque);
        }*/


/*        for (int i = k; i < n; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
        }*/

    }
}
