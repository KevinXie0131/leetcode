package com.template;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Template4_Monotonic_Stack {

    public static void main(String[] args) {
        int[] nums = {2,1,2,4,3};
        int[] res = nextGreaterElement(nums);

        System.out.println(Arrays.toString(res));

        int[] temperatures = {73,74,75,71,69,76};
        int[] res1 = dailyTemperatures(temperatures);
        System.out.println(Arrays.toString(res1));

        int[] temperatures2 = {73,74,75,71,69,72,76,73};
        int[] res2= dailyTemperatures2(temperatures2);
        System.out.println(Arrays.toString(res2));
    }

    static int[] nextGreaterElement(int[] nums) {
        int n = nums.length;
        // 存放答案的数组
        int[] res = new int[n];
        Stack<Integer> s = new Stack<>();
        // 倒着往栈里放
        for (int i = n - 1; i >= 0; i--) {
            // 判定个子高矮
            while (!s.isEmpty() && s.peek() <= nums[i]) {
                // 矮个起开，反正也被挡着了。。。
                s.pop();
            }
            // nums[i] 身后的更大元素
            res[i] = s.isEmpty() ? -1 : s.peek();
            s.push(nums[i]);
        }
        return res;
    }

    static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        // 这里放元素索引，而不是元素
        Stack<Integer> stack = new Stack<>();
        /* 单调栈模板 */
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[stack.peek()] <= temperatures[i]) {
                stack.pop();
            }
            // 得到索引间距
            res[i] = stack.isEmpty() ? 0 : (stack.peek() - i);
            // 将索引入栈，而不是元素
            stack.push(i);
            System.out.println(stack);
        }
        return res;
    }

    /**
     * 73,74,75,71,69,72,76,73
     * [1, 1, 4, 2, 1, 1, 0, 0]
     */
    static public int[] dailyTemperatures2(int[] temperatures) {
        int length = temperatures.length;
        int[] result = new int[length];
        Stack<Integer> stack = new Stack<Integer>();

        for (int i = 0; i < length; i++) {
            int temperature = temperatures[i];

            while (!stack.isEmpty() && temperature > temperatures[stack.peek()]) {
                int prevIndex = stack.pop();
                result[prevIndex] = i - prevIndex;
            }
            stack.push(i);
            System.out.println(stack);
        }
        return result;
    }
}
