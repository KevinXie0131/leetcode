package com.answer.priorityqueue;

import java.util.PriorityQueue;

public class Q1167_Minimum_Cost_to_Connect_Sticks {
    /**
     * You have some sticks with positive integer lengths.
     * You can connect any two sticks of lengths X and Y into one stick by paying a cost of X + Y.  You perform this action until there is one stick remaining.
     * Return the minimum cost of connecting all the given sticks into one stick in this way.
     *
     * Example 1:
     *  Input: sticks = [2,4,3]
     *  Output: 14
     *          第一次操作，将 2 和 3 连接（费用=5），剩下木棍 [5,4]
     *          第二次操作，将 5 和 4 连接（费用=9），现在只有一根木棍
     *          总费用=5+9=14
     * Example 2:
     *  Input: sticks = [1,8,3,5]
     *  Output: 30
     *          先合并 1 和 3（费用=4），剩下 [4,8,5]
     *          再合并 4 和 5（费用=9），剩下 [9,8]
     *          最后合并 9 和 8（费用=17）
     *          总费用=4+9+17=30
     *
     * 连接木棍的最低费用
     * 有一些长度为正整数的木棍。每次操作，你可以选择任意两根木棍连接成一根新的木棍，费用等于它们两根的长度之和。最终，你将所有的木棍连接成一根。
     * 返回把所有木棍连接成一根所需的最低总费用。你可以以任何顺序完成此过程。
     */
    public static void main(String[] args) {
       int[] sticks = {2,4,3};
       System.out.println(connectSticks(sticks));
       int[] sticks1 = {1,8,3,5};
       System.out.println(connectSticks(sticks1));
    }
    /**
     * 优先队列 + 贪心
     * Use a min-heap (PriorityQueue) to always connect the two smallest sticks.
     * Add their sum to the cost and put the result back into the heap.
     * Repeat until only one stick remains.
     * The total cost is the answer.
     * 用小根堆（优先队列）反复取出两根最短的棒材连接，并把费用累加，每次将新棒材放回堆中，直到只剩一根棒材为止。
     * 总费用即为答案。
     */
    static public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();// 小根堆
        for(int stick : sticks){
            queue.offer(stick);
        }
        int count = 0;
        while (queue.size() >= 2 ){
            int x = queue.poll();
            int y = queue.poll();
            count += x + y;

            queue.offer(x + y);
        }
        return count;
    }
}
