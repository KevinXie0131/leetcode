package com.answer.stack;

import java.util.*;

public class Q933_Number_of_Recent_Calls {
    /**
     * 最近的请求次数
     * 写一个 RecentCounter 类来计算特定时间范围内最近的请求(counts the number of recent requests within a certain time frame.)。
     * 请你实现 RecentCounter 类：
     *  RecentCounter() 初始化计数器，请求数为 0 。
     *  int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，并返回过去 3000 毫秒内发生的所有请求数（包括新请求）。确切地说，返回在 [t-3000, t] 内(inclusive range)发生的请求数。
     *
     * 保证 每次对 ping 的调用都使用比之前更大的 t 值。
     * It is guaranteed that every call to ping uses a strictly larger value of t than the previous call.
     *
     * 示例 1：
     * 输入：["RecentCounter", "ping", "ping", "ping", "ping"]
     *       [[], [1], [100], [3001], [3002]]
     * 输出：[null, 1, 2, 3, 3]
     * 解释：
     *  RecentCounter recentCounter = new RecentCounter();
     *  recentCounter.ping(1);     // requests = [1]，范围是 [-2999,1]，返回 1
     *  recentCounter.ping(100);   // requests = [1, 100]，范围是 [-2900,100]，返回 2
     *  recentCounter.ping(3001);  // requests = [1, 100, 3001]，范围是 [1,3001]，返回 3
     *  recentCounter.ping(3002);  // requests = [1, 100, 3001, 3002]，范围是 [2,3002]，返回 3
     */
    public static void main(String[] args) {
        // [1], [100], [3001], [3002]]
        Q933_Number_of_Recent_Calls call = new Q933_Number_of_Recent_Calls();
        System.out.println(call.ping(1));
        System.out.println(call.ping(100));
        System.out.println(call.ping(3001));
        System.out.println(call.ping(3002));
    }
    /**
     * 使用Queue
     * 一个队列维护发生请求的时间，当在时间 t 收到请求时，将时间 t 入队。
     * 由于每次收到的请求的时间都比之前的大，因此从队首到队尾的时间值是单调递增的。当在时间 t 收到请求时，
     * 为了求出 [t−3000,t] 内发生的请求数，我们可以不断从队首弹出早于 t−3000 的时间。循环结束后队列的长度就是 [t−3000,t] 内发生的请求数。
     */
    Deque<Integer> queue;

    public Q933_Number_of_Recent_Calls() {
        queue = new ArrayDeque<>();
    }

    public int ping(int t) {
        queue.offer(t);//遇到请求就存入队列中，存入t
        while(queue.size() > 0 && t - queue.peek() > 3000){  // 从队列中淘汰t-3000以前的请求
            queue.poll();
        }
        /* 队首元素的值在[t - 3000, t]内结束循环 */
    /*    while (queue.peek() < t - 3000) {   //peek判断有几个，并计数
            queue.poll();
        }*/
        return queue.size();
    }
    /**
     * Use array as queue
     * 双指针+数组存储
     */
    int left, right; //代表最后待插入的位置、t-3000范围的起始位置
    int []times;

    public void RecentCounter_1() {
        left = 0;
        right = 0;
        times = new int[10005]; // At most 10^4 calls will be made to ping.
    }

    public int ping_1(int t) {
        times[right++] = t;
        while (times[left] < t - 3000) {
            left++;
        }
        return right - left;   /* 返回数组的大小 = 发生的请求数 */
    }
}
