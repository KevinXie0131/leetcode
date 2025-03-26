package com.answer.bit;

public class Q136_Single_Number {

    public static void main(String[] args) {
        System.out.println(5 ^ 0);
        System.out.println(5 ^ 5);

        System.out.println(2 ^ 2 ^ 1);
    }
    /**
     * 位运算 异或
     */
    public int singleNumber(int[] nums) {
        int single = 0;
        for(int n : nums){
        //    single ^= n;
            single = single ^ n; // 将数组中所有数字做异或，两两消除，最终剩下的就是只出现一次的元素。
        }
        return single;
    }
}
