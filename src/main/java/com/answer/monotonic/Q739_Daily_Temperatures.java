package com.answer.monotonic;

import java.util.*;

public class Q739_Daily_Temperatures {

    /**
     * 单调栈
     * 在栈的基本操作的基础上，通过对栈中元素进行若干次入栈和出栈后，栈中的元素「自栈顶向栈底」，元素值单调增加 / 单调减少，
     * 此时的栈称为「单调栈」，前者称为「单调增加栈」，而后者称为「单调减少栈」。
     *
     * 单调栈被广泛应用在求解当前元素左 / 右侧「第一个」「小于（小于等于）/ 大于（大于等于）」的元素所在位置 / 元素值
     *
     * 需要特别注意的是：由于当前元素的出现，会破坏单调栈的单调性，因此此方法仅适用于求解「第一个」破坏单调性的元素，而不适用于求解这样的元素的总数等问题
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] result = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i< n; i++){

            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()] ){
                int preIndex = stack.pop();
                result[preIndex] = i - preIndex;
            }

            stack.push(i);
        }

        return result;

    }
}
