package com.answer.priorityqueue;

import java.util.*;

public class Q264_Ugly_Number_II {

    public static void main(String[] args) {
        System.out.println(nthUglyNumber_2(10));
    }
    /**
     * Time Limit Exceeded
     */
    public static int nthUglyNumber(int n) {
        int num = 1;
        while(n > 0){
        //    System.out.println("n: " + n);
         //   System.out.println("num: " + num);
            if(isUgly_1(num)){
                n--;
            }
            if(n == 0) break;
            num++;
        }
        return num;
    }
    public static boolean isUgly_1(int n) {
        if (n <= 0) return false;

        while(n % 2 == 0) n = n / 2;
        while(n % 3 == 0) n = n / 3;
        while(n % 5 == 0) n = n / 5;

        return n == 1;
    }
    /**
     * Approach 1: Heap - PriorityQueue 最小堆
     * 每个丑数都可以由其他较小的丑数通过乘以 2 或 3 或 5 得到。
     * 总时间复杂度是 O(nlogn) 空间复杂度：O(n)
     */
    public static int nthUglyNumber_2(int n) {
        int[] factors = new int[]{2, 3, 5};
        // 为了避免重复元素，可以使用哈希集合去重，避免相同元素多次加入堆。
        Set<Long> set = new HashSet<>(); // 用于去重
        PriorityQueue<Long> queue = new PriorityQueue<>();
        set.add(1L);
        queue.add(1L);
        long val = -1L;
        for(int i = 1; i <= n; i++){
             val = queue.poll();
         /*   if(i == n) {
                System.out.println(i);
                return (int)val;
            }*/
            for(int factor :factors ){
                long ugly = factor * val;
                if(!set.contains(ugly)){ // 去除重复的数值 比如 2*3=6 和3*2=6
                    set.add(ugly);
                    queue.add(ugly);
                }
            }
            System.out.println(queue);
        }
        return (int)val;
    }
    /**
     * TreeMap - don't worry about duplicated elements
     */
    static public int nthUglyNumber_3(int n) {
        TreeSet<Long> set = new TreeSet<Long>(); //TreeSet会排序
        int count = 0;
        long result = 1;
        set.add(result);
        while (count < n) {
            result = set.pollFirst();
            count++;
            set.add(result * 2); // TreeSet会去重
            set.add(result * 3);
            set.add(result * 5);
            System.out.println(set);
        }
        return (int) result;
    }
    /**
     * 动态规划(三指针) 时间复杂度 O(n) 空间复杂度 O(n)
     * 我们设 3 个指针 p_2,p_3,p_5
     * 代表的是第几个数的 2 倍、第几个数 3 倍、第几个数 5 倍
     * 动态方程 dp[i]=min(dp[p_2]*2,dp[p_3]*3,dp[p_5]*5)
     * 然后根据 dp[i] 是由 index2, index3, index5 中的哪个相乘得到的，对应的把此 index + 1，表示还没乘过该 index 的最小丑数变大了。
     */
    public int nthUglyNumber3(int n) {
        int a = 0, b = 0, c = 0;
        int[] res = new int[n];
        res[0] = 1;
        for(int i = 1; i < n; i++) {
            int n2 = res[a] * 2, n3 = res[b] * 3, n5 = res[c] * 5;
            res[i] = Math.min(Math.min(n2, n3), n5);
            if (res[i] == n2) a++;
            if (res[i] == n3) b++;
            if (res[i] == n5) c++;
        }
        return res[n - 1];
    }
    /**
     * 同上 另一种形式
     */
    public int nthUglyNumber4(int n) {
        int[] dp = new int[n + 1]; // 用作存储已有丑数（从下标 1 开始存储，第一个丑数为 1）
        dp[1] = 1;
        // 由于三个有序序列都是由「已有丑数」*「质因数」而来
        // i2、i3 和 i5 分别代表三个有序序列当前使用到哪一位「已有丑数」下标（起始都指向 1）
        int p2 = 1, p3 = 1, p5 = 1;
        for (int i = 2; i <= n; i++) {
            // 由 dp[iX] * X 可得当前有序序列指向哪一位
            int num2 = dp[p2] * 2;
            int num3 = dp[p3] * 3;
            int num5 = dp[p5] * 5;
            // 将三个有序序列中的最小一位存入「已有丑数」序列，并将其下标后移
            dp[i] = Math.min(Math.min(num2, num3), num5);
            // 由于可能不同有序序列之间产生相同丑数，因此只要一样的丑数就跳过（不能使用 else if ）
            if (dp[i] == num2) {
                p2++;
            }
            if (dp[i] == num3) {
                p3++;
            }
            if (dp[i] == num5) {
                p5++;
            }
        }
        return dp[n];
    }
}
