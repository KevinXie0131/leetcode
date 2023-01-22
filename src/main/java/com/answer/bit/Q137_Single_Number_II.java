package com.answer.bit;

public class Q137_Single_Number_II {
    public int singleNumber(int[] nums) {
        int ans = 0;
        //考虑每一位
        for (int i = 0; i < 32; i++) {
            int count = 0;
            //考虑每一个数
            for (int j = 0; j < nums.length; j++) {
                //当前位是否是 1
                if ((nums[j] >>> i & 1) == 1) {
                    count++;
                }
            }
            //1 的个数是否是 3 的倍数
            if (count % 3 != 0) {
                ans = ans | 1 << i;
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
