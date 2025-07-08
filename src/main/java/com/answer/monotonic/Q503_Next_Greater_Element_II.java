package com.answer.monotonic;

import java.util.*;

public class Q503_Next_Greater_Element_II {
    /**
     * Given a circular integer array nums (i.e., the next element of nums[nums.length - 1] is nums[0]), return the next greater number for every element in nums.
     * The next greater number of a number x is the first greater number to its traversing-order next in the array,
     * which means you could search circularly to find its next greater number. If it doesn't exist, return -1 for this number.
     * 下一个更大元素 II
     * 给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），返回 nums 中每个元素的 下一个更大元素 。
     * 数字 x 的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1 。
     */
    public static void main(String[] args) {
        /**
         * 输入: nums = [1,2,1]
         * 输出: [2,-1,2]
         * 解释: 第一个 1 的下一个更大的数是 2；
         * 数字 2 找不到下一个更大的数；
         * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
         */
        int[] nums = {1,2,1};
        int[] r = nextGreaterElements0(nums);
        System.out.println(Arrays.toString(r));
    }
    /**
     * 单调栈 / 把下标存入stack <- not distinct 0-indexed integer arrays & only one array, so there is no need for map
     * refer to Q496_Next_Greater_Element_I
     */
    public static int[] nextGreaterElements0(int[] nums) {
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
        Deque<Integer> stack = new ArrayDeque<>();

        int[] newNums = new int[nums.length * 2];
        int index = 0;
        for(int i = 0; i < newNums.length; i++){
            newNums[i] = nums[index++];
            index = index % nums.length;
        }

        for(int i = 0; i < newNums.length; i++){
            while(!stack.isEmpty() && newNums[stack.peek()] < newNums[i] ){
                int top = stack.pop();
                result[top % nums.length] = newNums[i];
            }
            stack.push(i);
        }
        return result;
    }
    /**
     * 直接把两个数组拼接在一起，然后使用单调栈求下一个最大值
     * 扩充nums数组相当于多了一个O(n)的操作
     */
    public static int[] nextGreaterElements1(int[] nums) {
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
        Deque<Integer> stack = new LinkedList<>();  // 开始单调栈
        int[] numsNew = Arrays.copyOf(nums, nums.length + nums.length);
        System.arraycopy(nums, 0, numsNew, nums.length, nums.length); // 将两个nums数组拼接在一起

        for(int i = 0; i < numsNew.length; i++){
            while(!stack.isEmpty() && numsNew[stack.peek()] < numsNew[i] ){
                result[stack.peek() % nums.length] = numsNew[i]; // 最后再把结果集即result数组resize到原数组大小
                stack.pop();
            }
            stack.push(i);
        }
        return result;
    }
    /**
     * 可以不扩充nums，而是在遍历的过程中模拟走了两遍nums
     */
    public int[] nextGreaterElements2(int[] nums) {
        int size = nums.length;
        int[] result = new int[size]; //存放结果
        Arrays.fill(result, -1);//默认全部初始化为-1
        Deque<Integer> stack = new LinkedList<>();//栈中存放的是nums中的元素下标

        for(int i = 0; i < size * 2; i++){
            while(!stack.isEmpty() && nums[stack.peek()] < nums[i % size] ){// 模拟遍历两遍nums，注意一下都是用i % nums.size()来操作
                result[stack.peek() % nums.length] = nums[i % size];   //更新result
                stack.pop(); //弹出栈顶
            }
            stack.push(i % size);
        }

        return result;
    }
    /**
     * 同上
     */
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i=0; i < 2 * nums.length; i++){
            while(!stack.isEmpty() && nums[i % n] > nums[stack.peek()]){
                int preIndex = stack.pop();
                result[preIndex] = nums[i % n];
            }

            stack.push(i % n);
        }

        return result;

    }
}
