package com.answer.array;

public class Q1295_Find_Numbers_with_Even_Number_of_Digits {
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
