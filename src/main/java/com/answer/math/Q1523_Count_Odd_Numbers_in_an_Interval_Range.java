package com.answer.math;

public class Q1523_Count_Odd_Numbers_in_an_Interval_Range {

    /**
     * Time Limit Exceeded for 278382788 - 569302584
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
}
