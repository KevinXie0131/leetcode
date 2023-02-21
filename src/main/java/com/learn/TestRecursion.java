package com.learn;

import java.util.HashMap;

public class TestRecursion {
    /**
     * Recursion is an approach to solving problems using a function that calls itself as a subroutine.
     * 1. base case (bottom cases) - a terminating scenario that does not use recursion to produce an answer.
     * 2. recurrence relation - reduces all other cases towards the base case.
     */
    /**
     * Print a string in reverse order.
     */
    private static void printReverse(char [] str) {
        helper(0, str);
    }

    private static void helper(int index, char [] str) {
        if (str == null || index >= str.length) {
            return;
        }
        helper(index + 1, str);
        System.out.print(str[index]);
    }
    /**
     * Reverse String
     *
     * Another Divide-and-Conquer Solution
     * So one of the ideas about how to divide the problem would be reducing the input string at each step into two components:
     * 1). the leading and trailing characters.
     * 2). the remaining substring without the leading and trailing characters.
     *     We then can solve the two components independently from each other.
     */
    public void reverseString(char[] s) {
        helper(0, s.length - 1, s);
    }

    private void helper(int start, int end, char [] s) {
        if (start >= end) {
            return;
        }
        // swap between the first and the last elements.
        char tmp = s[start];
        s[start] = s[end];
        s[end] = tmp;

        helper(start + 1, end - 1, s);
    }
    /**
     * Duplicate Calculation in Recursion - Memoization
     *
     * Memoization is an optimization technique used primarily to speed up computer programs by storing the results of
     * expensive function calls and returning the cached result when the same inputs occur again.
     */
    HashMap<Integer, Integer> cache = new HashMap<Integer, Integer>();

    private int fib(int N) {
        if (cache.containsKey(N)) {
            return cache.get(N);
        }
        int result;
        if (N < 2) {
            result = N;
        } else {
            result = fib(N-1) + fib(N-2);
        }
        // keep the result in cache.
        cache.put(N, result);
        return result;
    }
    /**
     * Tail Recursion
     *
     * Tail recursion is a recursion where the recursive call is the final instruction in the recursion function.
     * And there should be only one recursive call in the function.
     *
     * The benefit of having tail recursion is that it could avoid the accumulation of stack overheads during the recursive calls,
     * since the system could reuse a fixed amount space in the stack for each recursive call.
     */
    private static int helper_non_tail_recursion(int start, int [] ls) {
        if (start >= ls.length) {
            return 0;
        }
        // not a tail recursion because it does some computation after the recursive call returned.
        return ls[start] + helper_non_tail_recursion(start+1, ls);
    }
    public static int sum_non_tail_recursion(int [] ls) {
        if (ls == null || ls.length == 0) {
            return 0;
        }
        return helper_non_tail_recursion(0, ls);
    }
    //---------------------------------------------
    private static int helper_tail_recursion(int start, int [] ls, int acc) {
        if (start >= ls.length) {
            return acc;
        }
        // this is a tail recursion because the final instruction is the recursive call.
        return helper_tail_recursion(start+1, ls, acc+ls[start]);
    }
    public static int sum_tail_recursion(int [] ls) {
        if (ls == null || ls.length == 0) {
            return 0;
        }
        return helper_tail_recursion(0, ls, 0);
    }
}
