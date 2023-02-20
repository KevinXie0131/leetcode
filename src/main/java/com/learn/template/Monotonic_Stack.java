package com.learn.template;

import java.util.*;

public class Monotonic_Stack {
    /**
     * 单调递增栈
     */
    public int fn(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        int ans = 0;

        for (int num: arr) {
            // 对于单调递减的情况，只需将 > 翻转到 <
            while (!stack.empty() && stack.peek() > num) {
                // 根据题意补充代码
                stack.pop();
            }

            stack.push(num);
        }

        return ans;
    }
}
