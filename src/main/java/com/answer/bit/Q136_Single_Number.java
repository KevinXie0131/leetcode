package com.answer.bit;

public class Q136_Single_Number {

    public static void main(String[] args) {
        System.out.println(5 ^ 0);
        System.out.println(5 ^ 5);

        System.out.println(2 ^ 2 ^ 1);
    }

    public int singleNumber(int[] nums) {
        int single = 0;
        for(int n : nums){
        //    single ^= n;
            single = single ^ n;
        }

        return single;
    }
}
