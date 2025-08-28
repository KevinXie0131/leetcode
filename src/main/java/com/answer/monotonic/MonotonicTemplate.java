package com.answer.monotonic;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

public class MonotonicTemplate {
    /**
     * 单调栈 (十六字真言：及时去掉无用数据，保证栈中元素有序)
     * 如果求一个元素右边第一个更大元素，单调栈就是递增的(从栈顶到栈底的顺序，即栈顶的元素比栈底的元素小)，
     * 如果求一个元素右边第一个更小元素，单调栈就是递减的(从栈顶到栈底的顺序，即栈顶的元素比栈底的元素大)。
     */
    public int[] dailyTemperatures1(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Deque<Integer> stack = new LinkedList<>();

        for(int i = 0; i < temperatures.length; i++){
            while(!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]){
                result[stack.peek()] = i- stack.peek();
                stack.pop();
            }
            stack.push(i); //  注意，单调栈里 加入的元素是 下标。
        }
        return result;
    }
    /**
     * 同上
     */
    public int[] dailyTemperatures2(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i < n; i++){
            while(!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]){
                int day = stack.pop();
                result[day] = i - day;
            }
            stack.push(i);
        }
        return result;
    }
    /**
     * 数组模拟单调栈
     */
    public int[] dailyTemperatures3(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];
        int[] stack = new int[n]; // 数组模拟，效率更高
        int top = -1;
        for (int i = 0; i < n; i++) {
            while (top >= 0 && temperatures[stack[top]] < temperatures[i]) {
                int j = stack[top--];
                res[j] = i - j;
            }
            stack[++top] = i;
        }
        return res;
    }
}
