package com.answer.sliding_window;

import java.util.ArrayDeque;
import java.util.*;

public class Q1151_Minimum_Swaps_to_Group_All_1s_Together {
    /**
     * Given a binary array data, return the minimum number of swaps required to group all 1’s present in the array together
     * in any place in the array.
     * Example 1:
     *  Input: [1,0,1,0,1]
     *  Output: 1
     *  Explanation: There are 3 ways to group all 1's together:
     *              [1,1,1,0,0] using 1 swap.
     *              [0,1,1,1,0] using 2 swaps.
     *              [0,0,1,1,1] using 1 swap. The minimum is 1.
     * Example 2:
     *  Input: [0,0,0,1,0]
     *  Output: 0
     *  Explanation: Since there is only one 1 in the array, no swaps needed.
     * Example 3:
     *  Input: [1,0,1,0,1,0,0,1,1,0,1]
     *  Output: 3
     *  Explanation: One possible solution that uses 3 swaps is [0,0,0,0,0,1,1,1,1,1,1].
     *  最少交换次数来组合所有的1: 出一个二进制数组 data，你需要通过交换位置，将数组中 任何位置 上的 1 组合到一起，并返回所有可能中所需 最少的交换次数。
     */
    public static void main(String[] args) {
        int[] data = {1,0,1,0,1,0,0,1,1,0,1};
        System.out.println(minSwaps(data));
    }
    /**
     * Approach 1: Sliding Window with Two Pointers
     *
     * 本题意思就是让找固定窗口中最少有几个0。窗口长度就是1个总个数。
     * 为啥呢？倒着想，交换成功后，窗口中全是1，可知不管怎么交换，最终的窗口长度是固定的，长度就是所有1的个数。交换时，窗口中有几个0，
     * 就得来几次交换，把它们换出去，可知求最少交换次数，就是求最少0。
     *
     */
    public static int minSwaps(int[] data) {
        int len = data.length;
        int min = Integer.MIN_VALUE;
        int totalCount = Arrays.stream(data).sum();

        int sum = 0;
        int max = 0;
        for(int i = 0; i < len; i++){
            if(i < totalCount){
                sum += data[i];
            } else{
                sum += data[i];
                sum -= data[i - totalCount];
            }
            max = Math.max(max, sum);
        }

        return totalCount - max;
    }
    /**
     * Approach 2: Sliding Window with Deque (Double-ended Queue)
     */
    public int minSwaps_1(int[] data) {
        int len = data.length;
        int totalCount = Arrays.stream(data).sum();

        int sum = 0;
        int max = 0;
        Deque<Integer> deque = new ArrayDeque<>();

        for(int i = 0; i < len; i++){
            deque.offer(data[i]);
            sum += data[i];

            if(deque.size() > totalCount){
                sum -= deque.poll();
            }

            max = Math.max(max, sum);
        }

        return totalCount - max;
    }
}
