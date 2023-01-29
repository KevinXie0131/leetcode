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
     * PriorityQueue
     */
    public static int nthUglyNumber_2(int n) {
        int[] factors = new int[]{2, 3, 5};

        Set<Long> set = new HashSet<>();
        PriorityQueue<Long> queue = new PriorityQueue<>();
        set.add(1L);
        queue.add(1L);
        for(int i = 1; i <= n; i++){
            long val = queue.poll();
            if(i == n) {
                return (int)val;
            }
            for(int factor :factors ){
                long ugly = factor * val;
                if(!set.contains(ugly)){
                    set.add(ugly);
                    queue.add(ugly);
                }
            }
        }
        return -1;
    }
}
