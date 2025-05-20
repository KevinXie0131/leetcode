package com.answer.bit;

public class Q136_Single_Number {
    /**
     * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
     * You must implement a solution with a linear runtime complexity and use only constant extra space.
     * 只出现一次的数字
     * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
     * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
     */
    public static void main(String[] args) {
        System.out.println(5 ^ 0);
        System.out.println(5 ^ 5);
        System.out.println(2 ^ 2 ^ 1);
    }
    /**
     * 位运算 异或
     * 一个数和 0 做 XOR 运算等于本身：a⊕0 = a
     * 一个数和其本身做 XOR 运算等于 0：a⊕a = 0
     * XOR 运算满足交换律和结合律：a⊕b⊕a = (a⊕a)⊕b = 0⊕b = b
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
