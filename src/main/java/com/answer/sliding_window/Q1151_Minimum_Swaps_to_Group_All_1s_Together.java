package com.answer.sliding_window;

import java.util.ArrayDeque;
import java.util.*;

public class Q1151_Minimum_Swaps_to_Group_All_1s_Together {
    /**
     * Given a binary array data, return the minimum number of swaps required to group all 1’s present in the array together in any place in the array.
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
     *  最少交换次数来组合所有的1
     *  给出一个二进制数组 data，你需要通过交换位置，将数组中 任何位置 上的 1 组合到一起，并返回所有可能中所需 最少的交换次数。
     */
    public static void main(String[] args) {
        int[] data1 = {1,0,1,0,1};
    //    System.out.println(minSwaps5(data1));
        int[] data2 = {0,0,0,1,0};
   //     System.out.println(minSwaps5(data2));
        int[] data = {1,0,1,0,1,0,0,1,1,0,1};
        System.out.println(minSwaps5(data));
    }
    /**
     * 滑动窗口
     * 先统计总共有多少个1，我们假设有m个。然后以m为滑动窗口的长度，通过滑动窗口的方式判断窗口中有多少个1（假设为n个），
     * 那么此时我们需要移动的次数就是m-n，我们只需要找最小值即可。
     */
    public static int minSwaps(int[] data) {
        int size = Arrays.stream(data).filter(i -> i == 1).sum(); // 滑动窗口的 size 是固定的

        int min = Integer.MAX_VALUE, count = 0;
        for(int index = 0; index < size; index++){
            if(data[index] == 1){
                count++;
            }
            min = Math.min(min, size - count);
        }

        for(int index = size; index < data.length; index++){
            if(data[index] == 1){
                count++;
            }
            if(data[index - size] == 1){
                count--;
            }
            min = Math.min(min,  size - count);
        }
        return min;
    }
    /**
     * another form
     */
    public static int minSwaps_a(int[] data) {
        int size = Arrays.stream(data).sum(); // 滑动窗口的 size 是固定的

        int min = size, count = 0;
        for(int index = 0; index < data.length; index++){
            if(data[index] == 1){
                count++;
            }
            if(index >= size && data[index - size] == 1){
                count--;
            }
            min = Math.min(min,  size - count);
        }
        return min;
    }
    /**
     * another form
     * 首先我们用一个变量 ones 记录 input 数组中一共有多少个 1。然后我们用滑动窗口的模板开始扫描 input 数组，
     * 当 end 指针遇到 1 的时候，count++；当 end 和 start 两个指针的距离 == ones 的时候，
     * 此时我们看一下 end 和 start 之间有几个 1，此时 ones 和 count 的差值就是需要 swap 的次数。遍历整个数组，
     * 找到全局最小的 swap 次数即可。
     */
    public static int minSwaps4(int[] data) {
        int totalOne = Arrays.stream(data).sum();
        if(totalOne == 1 || totalOne == 0) return 0;

        int count = 0, left = 0, right = 0, min = Integer.MAX_VALUE;

        while(right < data.length){
            if(data[right] == 1){
                count++;
            }

            if(right - left == totalOne){
                min = Math.min(min, totalOne - count);
                if(data[left] == 1){
                    count--;
                }
                left--;
            }
            right++;
        }
        return min;
    }
    /**
     * Approach 1: Sliding Window with Two Pointers
     *
     * 本题意思就是让找固定窗口中最少有几个0。窗口长度就是1个总个数。
     * 为啥呢？倒着想，交换成功后，窗口中全是1，可知不管怎么交换，最终的窗口长度是固定的，长度就是所有1的个数。交换时，窗口中有几个0，
     * 就得来几次交换，把它们换出去，可知求最少交换次数，就是求最少0。
     *
     */
    public static int minSwaps2(int[] data) {
        int len = data.length;
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
     * 1.Count total number of 1s as totalCount.
     * 2.Use a sliding window of size totalCount to find the window with the most 1s (max).
     * 3.The minimum number of swaps needed is totalCount - max
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
    /**
     * 可以通过前缀和的方式统计区间内1的个数
     */
    public static int minSwaps5(int[] data) {
        int n = data.length;
        int[] preSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            preSum[i + 1] = preSum[i] + data[i];
        }

        int min = n;
        int ones = preSum[n];
        // data       [1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1]
        // presum: [0, 1, 1, 2, 2, 3, 3, 3, 4, 5, 5, 6]
        for(int i = ones; i < n + 1; i++){
            min = Math.min(min, ones - (preSum[i] - preSum[i - ones]));
        }
        return min;
    }
}
