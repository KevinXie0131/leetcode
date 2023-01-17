package com.answer.monotonic;

import java.util.*;

public class Q901_Online_Stock_Span {

    public static void main(String[] args) {
        StockSpanner stockSpanner = new StockSpanner();

        System.out.println(stockSpanner.next(100)); // return 1
        System.out.println(stockSpanner.next(80));  // return 1
        System.out.println(stockSpanner.next(60));  // return 1
        System.out.println(stockSpanner.next(70));  // return 2
        System.out.println(stockSpanner.next(60));  // return 1
        System.out.println(stockSpanner.next(75));  // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
        System.out.println(stockSpanner.next(85));  // return 6
        System.out.println(stockSpanner.next(110)); // return 8
    }
}

class StockSpanner{
    static Deque<int []> stack;
    static int index;

    public StockSpanner() {
        stack = new ArrayDeque<>();
        stack.push(new int[]{-1, Integer.MAX_VALUE});
        index = -1;
    }

    static public int next(int price) {
        index++;

        while(price >= stack.peek()[1]){
            stack.pop();
        }

        int result = index - stack.peek()[0];
        stack.push(new int[]{index, price});

        return result;
    }
}