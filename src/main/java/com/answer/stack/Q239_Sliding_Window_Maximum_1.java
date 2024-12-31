package com.answer.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Q239_Sliding_Window_Maximum_1 {

    public static void main(String[] args) {
/*        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;*/
        int[] nums = {1,-1};
        int k = 1;

/*        int[] nums = {-7,-8,7,5,7,1,6,0};
        int k = 4;*/
        int[] result = maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(result));
    }
    // 利用双端队列手动实现单调队列
    public static int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> queue = new ArrayDeque<>();
        int len = nums.length - k + 1;
        int[] result = new int[len];
        int n = nums.length;
        int index = 0;

        for(int i = 0; i < k; i++){
            while(!queue.isEmpty() && nums[i] > queue.getLast()){
                queue.removeLast();
            }
            queue.offer(nums[i]);
        }
        result[index++] = queue.peek();

        for(int i = k; i < nums.length; i++){
            if(!queue.isEmpty() && nums[i - k] == queue.peek()){
                queue.poll();
            }
            while(!queue.isEmpty() && nums[i]  > queue.getLast()){
                queue.removeLast();
            }
            queue.offer(nums[i]);

            result[index++] = queue.peek();
        }
        return result;
    }
    // 解法二
    // 利用双端队列手动实现单调队列
    /**
     * 用一个单调队列来存储对应的下标，每当窗口滑动的时候，直接取队列的头部指针对应的值放入结果集即可
     * 单调队列类似 （tail -->） 3 --> 2 --> 1 --> 0 (--> head) (右边为头结点，元素存的是下标)
     */
    public int[] maxSlidingWindow_1(int[] nums, int k) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int n = nums.length;
        int[] res = new int[n - k + 1];
        int idx = 0;
        for(int i = 0; i < n; i++) {
            // 根据题意，i为nums下标，是要在[i - k + 1, i] 中选到最大值，只需要保证两点
            // 1.队列头结点需要在[i - k + 1, i]范围内，不符合则要弹出
            while(!deque.isEmpty() && deque.peek() < i - k + 1){
                deque.poll();
            }
            // 2.既然是单调，就要保证每次放进去的数字要比末尾的都大，否则也弹出
            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            deque.offer(i);

            // 因为单调，当i增长到符合第一个k范围的时候，每滑动一步都将队列头节点放入结果就行了
            if(i >= k - 1){
                res[idx++] = nums[deque.peek()];
            }
        }
        return res;
    }
}