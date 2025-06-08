package com.answer.monotonic;

import java.util.*;

public class Q901_Online_Stock_Span {
    /**
     * 股票价格跨度
     * 设计一个算法收集某些股票的每日报价，并返回该股票当日价格的 跨度 。
     * The span of the stock's price in one day is the maximum number of consecutive days (starting from that day and going backward) for which the stock price was less than or equal to the price of that day.
     *  For example, if the prices of the stock in the last four days is [7,2,1,2] and the price of the stock today is 2, then the span of today is 4 because starting from today, the price of the stock was less than or equal 2 for 4 consecutive days.
     *  Also, if the prices of the stock in the last four days is [7,34,1,2] and the price of the stock today is 8, then the span of today is 3 because starting from today, the price of the stock was less than or equal 8 for 3 consecutive days.
     * 当日股票价格的 跨度 被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）。
     *   例如，如果未来 7 天股票的价格是 [100,80,60,70,60,75,85]，那么股票跨度将是 [1,1,1,2,1,4,6] 。
     * 实现 StockSpanner 类：
     *   StockSpanner() 初始化类对象。
     *   int next(int price) 给出今天的股价 price ，返回该股票当日价格的 跨度 。
     * 示例：
     *  输入：
     *      ["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
     *      [[], [100], [80], [60], [70], [60], [75], [85]]
     *  输出：
     *      [null, 1, 1, 1, 2, 1, 4, 6]
     *  解释：
     *      StockSpanner stockSpanner = new StockSpanner();
     *      stockSpanner.next(100); // 返回 1
     *      stockSpanner.next(80);  // 返回 1
     *      stockSpanner.next(60);  // 返回 1
     *      stockSpanner.next(70);  // 返回 2
     *      stockSpanner.next(60);  // 返回 1
     *      stockSpanner.next(75);  // 返回 4 ，因为截至今天的最后 4 个股价 (包括今天的股价 75) 都小于或等于今天的股价。
     *      stockSpanner.next(85);  // 返回 6
     */
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

        StockSpanner_1 stockSpanner_1 = new StockSpanner_1();

        System.out.println(stockSpanner_1.next(100)); // return 1
        System.out.println(stockSpanner_1.next(80));  // return 1
        System.out.println(stockSpanner_1.next(60));  // return 1
        System.out.println(stockSpanner_1.next(70));  // return 2
        System.out.println(stockSpanner_1.next(60));  // return 1
        System.out.println(stockSpanner_1.next(75));  // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
        System.out.println(stockSpanner_1.next(85));  // return 6
        System.out.println(stockSpanner_1.next(110)); // return 8
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

class StockSpanner_1{
        Deque<int[]> stack = new ArrayDeque<>();
        int cur = 0;

        public StockSpanner_1() {
        }

        public int next(int price) {
            while (!stack.isEmpty() && stack.peek()[1] <= price) {
                stack.pop();
            }
            int prev;
            int ans = 0;
            if(stack.isEmpty()){
                prev = -1;
            }else{
                prev = stack.peek()[0];
            }
            ans = cur - prev;

            stack.push(new int[]{cur++, price});

            return ans;
        }
}
