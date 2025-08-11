package com.answer.binarysearch;

import java.util.Arrays;

public class Q875_Koko_Eating_Bananas {
    /**
     * 爱吃香蕉的珂珂
     * 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。
     * There are n piles of bananas, the ith pile has piles[i] bananas.
     * 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
     * Koko can decide her bananas-per-hour eating speed of k. Each hour, she chooses some pile of bananas and
     * eats k bananas from that pile. If the pile has less than k bananas, she eats all of them instead and
     * will not eat any more bananas during this hour.
     * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
     *
     * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
     * Return the minimum integer k such that she can eat all the bananas within h hours.
     *
     * 示例 1：
     *  输入：piles = [3,6,7,11], h = 8
     *  输出：4
     * 示例 2：
     *  输入：piles = [30,11,23,4,20], h = 5
     *  输出：30
     * 示例 3：
     *  输入：piles = [30,11,23,4,20], h = 6
     *  输出：23
     */
    /**
     * 本题可以使用二分查找的原因：有单调性，找的还是一个整数。
     * 应该尝试更小的速度 是不是还可以保证在 h 小时内吃完内吃完所有的香蕉
     * 可以分为如下 3 种情况：
     *  情况 1：calculateSum(piles, mid) > h，耗时太多，说明吃得慢，接下来吃的速度要快一点，此时设置 left = mid + 1;
     *  情况 2：calculateSum(piles, mid) == h，耗时正好等于 h，说明：当前速度可能是答案。不过还有可能的情况是：吃得慢一点，也能保证耗时是 h，此时设置 right = mid；
     *  情况 3：calculateSum(piles, mid) < h，耗时太少，吃得太快，尝试更小的速度，此时设置 right = mid - 1。
     */
    public int minEatingSpeed(int[] piles, int h) {
        int maxVal = 1;
        for (int pile : piles) {
            maxVal = Math.max(maxVal, pile);
        }

        int left = 1;  // 速度最小的时候，耗时最长
        int right = maxVal;   // 速度最大的时候，耗时最短
        // 为什么是 while(left < right) ？
        // 这是因为：循环体内出有 right = mid 这样的代码，在 left = right 时就不能继续搜索，因为如果还执行 right = mid，
        // 搜索区间不能缩小，就会进入死循环。
        while (left < right) {
            int mid = (left + right) / 2;
            int value = calculateSum(piles, mid);
            if (value > h) {
                left = mid + 1;  // 耗时太多，说明速度太慢了，下一轮需要提速，搜索区间在 [mid + 1..right]
            } else {
                // 搜索区间是上面 if 的反面区间，即下一轮搜索区间在 [left..mid]
                // 当 hours < h 时执行了 right = mid - 1 ，这会导致错过可能的最小值。
                right = mid;
            }
        }
        return left;
    }
    // 珂珂按照 speed 速度吃完这堆香蕉需要的小时数
    private int calculateSum(int[] piles, int speed) {
        int hours = 0;
        for (int pile : piles) {
            int hour = pile / speed;
            if (pile % speed != 0) {
                hour++;
            }
            hours += hour;
            // 上取整还可以这样写 hours += (pile + speed - 1) / speed;
        }
        return hours;
    }
    /**
     * use Math.ceil()
     */
    public int minEatingSpeed1(int[] piles, int h) {
        int left = 1;
        int right = 1;
        for(int pile : piles){
            right = Math.max(right, pile);
        }

        while(left < right) {
            int mid = (left + right) >>> 1;
            int hour = 0;

            for(int pile : piles){
                hour += Math.ceil((double)pile / mid);
            }

            if(hour <= h){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        return left;
    }
    /**
     * 二分查找
     * 设吃香蕉的总时长为costTime，则问题转化为在[1,max]范围内寻找一个最小的k，使得costTime <= h
     */
    public int minEatingSpeed2(int[] piles, int h) {
        //找到最大值
        int max = Arrays.stream(piles).max().getAsInt();
        //边界
        int left = 1, right = max;
        //目标K
        int ans = max;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            //计算花费时长
            int costTime = 0;
            for (int pile : piles) {
                int curCostTime = pile / mid;
                //有剩余香蕉
                if (curCostTime * mid < pile) { // if (pile % mid != 0)
                    curCostTime++;
                }
                costTime += curCostTime;
            }
            //记录结果，缩小范围
            if (costTime <= h) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
