package com.answer.monotonic;

import java.util.*;

public class Q739_Daily_Temperatures {

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
