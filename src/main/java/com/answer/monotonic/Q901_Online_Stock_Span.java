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

        StockSpanner2 stockSpanner2 = new StockSpanner2();

        System.out.println(stockSpanner2.next(100)); // return 1
        System.out.println(stockSpanner2.next(80));  // return 1
        System.out.println(stockSpanner2.next(60));  // return 1
        System.out.println(stockSpanner2.next(70));  // return 2
        System.out.println(stockSpanner2.next(60));  // return 1
        System.out.println(stockSpanner2.next(75));  // return 4, because the last 4 prices (including today's price of 75) were less than or equal to today's price.
        System.out.println(stockSpanner2.next(85));  // return 6
        System.out.println(stockSpanner2.next(110)); // return 8
    }
}
/**
 * 单调栈
 * 这题让我们求出每个 price 的上一个更大元素距离当前有多远。比如示例中 70 的上一个更大元素是 80，距离为 2。
 */
class StockSpanner{
    static Deque<int []> stack;
    static int day; // 由于数据是流式输入的，除了记录 price，还需要记录它是第几天输入的（相当于 price 在数组中的下标）

    public StockSpanner() {
        stack = new ArrayDeque<>();
        stack.push(new int[]{-1, Integer.MAX_VALUE}); // 可以在初始化时往栈底添加一个 (−1,∞)，这样栈一定不会为空，无需单独处理 price 大于等于之前所有输入的情况
        day = 0; // 第一个 next 调用算作第 0 天
    }

    static public int next(int price) {
        while(price >= stack.peek()[1]){ // 如果栈顶元素小于等于当前元素，说明栈顶元素也不会是后面任何股票的<左侧第一个比我大的>，此时直接出栈即可。
            stack.pop();  // 栈顶数据后面不会再用到了，因为 price 更大
        }

        int result = day - stack.peek()[0];// 计算当前天数和栈顶这个元素天数的差值
        stack.push(new int[]{day, price});// 最后将当前天的天数和股票价格入栈
        day++;

        return result;
    }
}

/**
 * 单调栈十六字真言：及时去掉无用数据，保证栈中元素有序。
 *
 * 当日股票价格的 跨度 被定义为股票价格小于或等于今天价格的最大连续日数（从今天开始往回数，包括今天）
 * 问题变成找实际上左侧第一个比我大的位置在哪里？
 * 1、所以可以维护一个单调栈。如果栈顶元素小于等于当前元素，说明栈顶元素也不会是后面任何股票的<左侧第一个比我大的>，此时直接出栈即可。所以可以保证栈内元素自底到顶是单调增
 * 2、然后可以计算当前天数和栈顶这个元素天数的差值。这个差值就是要求的跨度
 * 3、最后将当前天的天数和股票价格入栈
 */
class StockSpanner_1{
        Deque<int[]> stack = new ArrayDeque<>();// 记录截止到目前为止，上一个比自己价格大的。int[]存储的是{天数, 价格}
        int day = 0; // 记录天数

        public StockSpanner_1() {
        }

        public int next(int price) {
            while (!stack.isEmpty() && stack.peek()[1] <= price) {
                stack.pop();// 如果栈顶元素小于等于当前元素，说明栈顶元素也不会是后面任何股票的<左侧第一个比我大的>，此时直接出栈即可。
            }
            int prev;
            if(stack.isEmpty()){
                prev = -1;
            }else{
                prev = stack.peek()[0];
            }
            int ans = day - prev; // 计算当前天数和栈顶这个元素天数的差值

            stack.push(new int[]{day++, price});// 最后将当前天的天数和股票价格入栈

            return ans;
        }
}
/**
 * 单调栈
 */
class StockSpanner2 {
    private Deque<int[]> stack = new ArrayDeque<>();

    public StockSpanner2() {
    }

    public int next(int price) {
        int days = 1; // 表示当前价格的跨度
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            days += stack.pop()[1]; // 如果栈顶元素的价格小于等于 price，则将当日价格的跨度 days 加上栈顶元素的跨度，然后将栈顶元素出栈，直到栈顶元素的价格大于 price，或者栈为空为止。
        }
        stack.push(new int[] {price, days});
        return days;
    }
}
