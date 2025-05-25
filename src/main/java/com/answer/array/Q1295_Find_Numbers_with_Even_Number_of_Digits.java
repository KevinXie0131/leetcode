package com.answer.array;

public class Q1295_Find_Numbers_with_Even_Number_of_Digits {
    /**
     * 统计位数为偶数的数字: 给你一个整数数组 nums，请你返回其中包含 偶数 个数位的数字的个数。
     * 示例 1：
     *  输入：nums = [12,345,2,6,7896]
     *  输出：2
     *  解释：
     *      12 是 2 位数字（位数为偶数）
     *      345 是 3 位数字（位数为奇数）
     *      2 是 1 位数字（位数为奇数）
     *      6 是 1 位数字 位数为奇数）
     *      7896 是 4 位数字（位数为偶数）
     *      因此只有 12 和 7896 是位数为偶数的数字
     */
    /**
     * 返回其中位数为 偶数 的数字的个数
     */
    public int findNumbers(int[] nums) {
        int count = 0;
        for(int num : nums){
            String s = String.valueOf(num);
            int len = s.length();
            if(len % 2 == 0){
                count++;
            }
/*            if ((int) (Math.log10(num) + 1) % 2 == 0) {
                count++;
            }*/
        }
        return count;
    }
    /**
     * 我们可以不断地去掉 x 的个位数和十位数，也就是把 x 除以 100（下取整），直到 x<100 为止
     * 如果最终 x<10，那么 x 的原始十进制长度就是奇数。反之，如果 x≥10，那么 x 的原始十进制长度就是偶数。
     */
    public int findNumbers_1(int[] nums) {
        int ans = 0;
        for (int x : nums) {
            while (x >= 100) {
                x /= 100;
            }
            if (x >= 10) {
                ans++;
            }
        }
        return ans;
    }
}
