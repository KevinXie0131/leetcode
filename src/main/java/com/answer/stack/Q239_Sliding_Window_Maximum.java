package com.answer.stack;

import java.util.*;

public class Q239_Sliding_Window_Maximum {
    /**
     * 滑动窗口最大值
     * 一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
     * You are given an array of integers nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.
     * 返回 滑动窗口中的最大值 。
     * Return the max sliding window.
     * 示例 1：
     *  输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
     *  输出：[3,3,5,5,6,7]
     *  解释：
     * 滑动窗口的位置                  最大值
     * Window position                Max
     * ---------------               -----
     * [1  3  -1] -3  5  3  6  7       3
     *  1 [3  -1  -3] 5  3  6  7       3
     *  1  3 [-1  -3  5] 3  6  7       5
     *  1  3  -1 [-3  5  3] 6  7       5
     *  1  3  -1  -3 [5  3  6] 7       6
     *  1  3  -1  -3  5 [3  6  7]      7
     * 示例 2：
     *  输入：nums = [1], k = 1
     *  输出：[1]
     */
    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
/*        int[] nums = {1,-1};
        int k = 1;*/
        int[] result = maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(result));
    }
    /**
     * 这是使用单调队列的经典题目。
     * 这个维护元素单调递减的队列就叫做单调队列，即单调递减或单调递增的队列。
     * 其实队列没有必要维护窗口里的所有元素，只需要维护有可能成为窗口里最大值的元素就可以了，同时保证队列里的元素数值是由大到小的。
     * 不要以为实现的单调队列就是 对窗口里面的数进行排序，如果排序的话，那和优先级队列又有什么区别了呢。
     *
     * 设计单调队列的时候，pop，和push操作要保持如下规则：
     * pop(value)：如果窗口移除的元素value等于单调队列的出口元素，那么队列弹出元素，否则不用任何操作
     * push(value)：如果push的元素value大于入口元素的数值，那么就将队列入口的元素弹出，直到push元素的数值小于等于队列入口元素的数值为止
     * 保持如上规则，每次窗口移动的时候，只要问que.front()就可以返回当前窗口的最大值。
     * 时间复杂度: O(n) 空间复杂度: O(k)
     *
     * 可能会想用一个大顶堆（优先级队列）来存放这个窗口里的k个数字，这样就可以知道最大的最大值是多少了，
     * 但是问题是这个窗口是移动的，而大顶堆每次只能弹出最大值，我们无法移除其他数值，这样就造成大顶堆维护的不是滑动窗口里面的数值了。所以不能用大顶堆。
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length - k + 1;
        int[] result = new int[len];
        int num = 0;

        MyMonoTonicQueue myQueue = new MyMonoTonicQueue();
        for(int i = 0; i < k; i++){
            myQueue.offer(nums[i]); // 先将前k的元素放进队列
        }
        result[num++] = myQueue.peek(); // result 记录前k的元素的最大值
        for(int i = k; i < nums.length; i++){
            myQueue.poll(nums[i - k]); // 滑动窗口移除最前面元素
            myQueue.offer(nums[i]); // 滑动窗口前加入最后面的元素
            result[num++] = myQueue.peek(); // 记录对应的最大值
        }
        return result;
    }

    /**
     *
     */
    public static int[] maxSlidingWindow1(int[] nums, int k) {
        MyMonoTonicQueue myQueue = new MyMonoTonicQueue(); //自定义队列
        int[] result = new int[nums.length - k + 1]; //存放结果元素的数组
        for(int i = 0; i < k; i++){
            myQueue.offer(nums[i]);   //先将前k的元素放入队列
        }
        result[0] = myQueue.peek();

        for(int i = k; i < nums.length; i++){
            myQueue.poll(nums[i-k]); //滑动窗口移除最前面的元素，移除是判断该元素是否放入队列
            myQueue.offer(nums[i]); //滑动窗口加入最后面的元素
            result [i-k + 1] = myQueue.peek();  //记录对应的最大值
        }
        return result;
    }
}
//单调队列（从大到小）
class MyMonoTonicQueue {
    // 使用deque来实现单调队列
    Deque<Integer> queue = new ArrayDeque<>();
    // 每次弹出的时候，比较当前要弹出的数值是否等于队列出口元素的数值，如果相等则弹出。
    // 同时pop之前判断队列当前是否为空。
    void poll(int val){
        if(!queue.isEmpty() && val == queue.peek()){
            queue.poll();
        }
    }
    // 如果push的数值大于入口元素的数值，那么就将队列后端的数值弹出，直到push的数值小于等于队列入口元素的数值为止。
    // 这样就保持了队列里的数值是单调从大到小的了。
    //
    //添加元素时，如果要添加的元素大于入口处的元素，就将入口元素弹出
    //保证队列元素单调递减
    // 比如此时队列元素3,1，2将要入队，比1大，所以1弹出，此时队列：3,2
    void offer(int val){
        while(!queue.isEmpty() && val > queue.getLast()){
            queue.removeLast();
        }
        queue.offer(val);
    }
    // 查询当前队列里的最大值 直接返回队列前端也就是front就可以了。
    //
    // 队列队顶元素始终为最大值
    int peek(){
        return queue.peek();
    }
}