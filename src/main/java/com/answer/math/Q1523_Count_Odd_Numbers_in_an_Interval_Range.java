package com.answer.math;

public class Q1523_Count_Odd_Numbers_in_an_Interval_Range {
    /**
     * Given two non-negative integers low and high. Return the count of odd numbers between low and high (inclusive).
     * 给你两个非负整数 low 和 high 。请你返回 low 和 high 之间（包括二者）奇数的数目。
     */
    /**
     * Time Limit Exceeded for 278382788 - 569302584
     * 0 <= low <= high <= 10^9
     */
    public int countOdds(int low, int high) {
        int count = 0;
        for(int i = low; i <= high; i++){
            if(i % 2 == 1){
                count++;
            }
        }

        return count;
    }
    /**
     * Approach 1: Maths
     */
    public int countOdds_1(int low, int high) {
        if(low % 2 == 0) low++;
        return ((high+1) / 2) - (low / 2);
    }
    /**
     * 如果high和low都是偶数，那么拿(high - low) / 2，就是结果。
     * 先从结尾的high来说
     *  high为奇数，表示当前这个奇数可以取到，即便我们将其+1结果并不变
     *  high为偶数，我们将其保持原状
     * 再来看开头low的场景：
     *  low为奇数，表示当前值我们可以取到，那么将low -= 1，并不影响结果
     *  low为偶数，我们将其保持原状
     * 如2中，即便low为偶数，我们将其-= 1，由于结果是一个整数，整除后结果不变，所以针对low，我们可以无脑-1。
     */
    public int countOdds_2(int low, int high) {
        if (high % 2 == 1 ) {
            high++;
        }
        return (high - low + 1) / 2;
    }
}
