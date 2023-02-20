package com.learn.template;

public class Two_Pointers {
    private static final boolean CONDITION = true; // add to make it compliant

    /**
     * 双指针: 只有一个输入, 从两端开始遍历
     */
    public int fn(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        int ans = 0;

        while (left < right) {
            // 一些根据 letf 和 right 相关的代码补充
            if (CONDITION) {
                left++;
            } else {
                right--;
            }
        }

        return ans;
    }
}
