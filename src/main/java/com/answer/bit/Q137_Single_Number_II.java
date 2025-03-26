package com.answer.bit;

public class Q137_Single_Number_II {
    /**
     * 二进制
     * 统计所有数字每一位中 1 的数量，如果可以整除 3，说明结果这一位为 0，否则为 1。
     */
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i = 0; i < 32; i++) { //考虑每一位
            int count = 0;
            for (int j = 0; j < nums.length; j++) {//考虑每一个数
                if (((nums[j] >>> i) & 1) == 1) { //当前位是否是 1
                    count++; // 得到每个二进制位总和
                }
            }

            if (count % 3 != 0) { //1 的个数是否是 3 的倍数
                ans = ans | (1 << i);
            }
        }
        return ans;
    }
    /**
     *
     */
    public int singleNumber_1(int[] nums) {
        int seenOnce = 0, seenTwice = 0;

        for (int num : nums) {
            // first appearence:
            // add num to seen_once
            // don't add to seen_twice because of presence in seen_once

            // second appearance:
            // remove num from seen_once
            // add num to seen_twice

            // third appearance:
            // don't add to seen_once because of presence in seen_twice
            // remove num from seen_twice
            seenOnce = ~seenTwice & (seenOnce ^ num);
            seenTwice = ~seenOnce & (seenTwice ^ num);
        }

        return seenOnce;
    }
}
