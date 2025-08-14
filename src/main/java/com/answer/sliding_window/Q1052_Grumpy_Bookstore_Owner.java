package com.answer.sliding_window;

public class Q1052_Grumpy_Bookstore_Owner {
    /**
     * 爱生气的书店老板
     * 有一个书店老板，他的书店开了 n 分钟。每分钟都有一些顾客进入这家商店。给定一个长度为 n 的整数数组 customers ，
     * 其中 customers[i] 是在第 i 分钟开始时进入商店的顾客数量，所有这些顾客在第 i 分钟结束后离开。
     * 在某些分钟内，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。
     * 当书店老板生气时，那一分钟的顾客就会不满意，若老板不生气则顾客是满意的。
     * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 minutes 分钟不生气，但却只能使用一次。
     * 请你返回 这一天营业下来，最多有多少客户能够感到满意 。
     * 示例 1：
     *  输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], minutes = 3
     *  输出：16
     *  解释：书店老板在最后 3 分钟保持冷静。感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
     * 示例 2：
     *  输入：customers = [1], grumpy = [0], minutes = 1
     *  输出：1
     */
    public static void main(String[] args) {
        int[] customers = {1,0,1,2,1,1,7,5};
        int[] grumpy = {0,1,0,1,0,1,0,1};
        int minutes = 3;
        System.out.println(maxSatisfied(customers, grumpy, minutes));
    }
    /**
     * Sliding window
     * 寻找一个时间长度为 X 的窗口，能留住更多的原本因为老板生气而被赶走顾客。
     * 能得到的最终的顾客数 = 所有不生气时间内的顾客总数 + 在窗口 X 内 挽留住的原本因为生气而被赶走顾客数。
     */
    public static int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int len = customers.length;
        int total = 0, increase = 0, max = 0;

        for(int i = 0; i < len; i++){
            if(grumpy[i] == 0){
                total += customers[i]; // 所有不生气时间内的顾客总数
            }
        }
        // 生气的 X 分钟内，会让多少顾客不满意
        for(int i = 0; i < minutes; i++){
            if(grumpy[i] == 1){
                increase += customers[i];
            }
        }
        max = increase; // this step is needed

       // 然后利用滑动窗口，每次向右移动一步
        for(int i = minutes; i < len; i++){
            if(grumpy[i] == 1){ // 如果新进入窗口的元素是生气的，累加不满意的顾客到滑动窗口中
                increase += customers[i];
            }
            if(grumpy[i - minutes] == 1){ // 如果离开窗口的元素是生气的，则从滑动窗口中减去该不满意的顾客数
                increase -= customers[i - minutes];
            }
            max = Math.max(max, increase); // 求所有窗口内不满意顾客的最大值
        }
        return total + max;
    }
    /**
     * 直接把顾客人数和老板的生气情况想乘，可以简化代码。
     */
    public static int maxSatisfied1(int[] customers, int[] grumpy, int minutes) {
        int total = 0;
        int len = customers.length;
        int increase = 0, max = 0;

        for(int i = 0; i < len; i++){
            if(grumpy[i] == 0){
                total += customers[i]; // 所有不生气时间内的顾客总数
            }
        }
        // 在窗口 X 内因为生气而被赶走的顾客数
        for(int i = 0; i < len; i++){
            if(i < minutes){
                increase += customers[i] * grumpy[i]; // 生气的 X 分钟内，会让多少顾客不满意
            }else{
                increase += customers[i] * grumpy[i]; // 如果新进入窗口的元素是生气的，累加不满意的顾客到滑动窗口中
                increase -= customers[i - minutes] * grumpy[i - minutes]; // 如果离开窗口的元素是生气的，则从滑动窗口中减去该不满意的顾客数
            }
            max = Math.max(max, increase); // 求所有窗口内不满意顾客的最大值
        }
        return total + max;
    }
    /**
     * 滑动窗口
     * 由于「技巧」只会将情绪将「生气」变为「不生气」，不生气仍然是不生气。
     *  我们可以先将原本就满意的客户加入答案，同时将对应的 customers[i] 变为 0。
     *  之后的问题转化为：在 customers 中找到连续一段长度为 minutes 的子数组，使得其总和最大。
     *  这部分就是我们应用技巧所得到的客户。
     */
    public int maxSatisfied3(int[] customers, int[] grumpy, int minutes) {
        int n = customers.length, ans = 0;

        for (int i = 0; i < n; i++) {
            if (grumpy[i] == 0) { // 先将原本就满意的客户加入答案，同时将对应的 customers[i] 变为 0。
                ans += customers[i];
                customers[i] = 0;
            }
        }
        int cur = 0, max = 0;
        for (int i = 0; i < n; i++) {
            cur += customers[i];

            if (i >= minutes){ // 找到连续一段长度为 minutes 的子数组，使得其总和最大。
                cur -= customers[i - minutes];
            }
            max = Math.max(max, cur);
        }
        return ans + max;
    }
}
