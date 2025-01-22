package com.answer.monotonic;

import java.util.*;

public class Q503_Next_Greater_Element_II {
    public static void main(String[] args) {
        int[] nums = {1,2,1};
        int[] r = nextGreaterElements1(nums);
        System.out.println(Arrays.toString(r));
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
