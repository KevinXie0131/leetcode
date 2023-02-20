package com.learn.template;

public class Two_Pointers_1 {
    private static final boolean CONDITION = true; // add to make it compliant
    /**
     * 双指针: 有两个输入, 两个都需要遍历完
     */
    public int fn(int[] arr1, int[] arr2) {
        int i = 0, j = 0, ans = 0;

        while (i < arr1.length && j < arr2.length) {
            // 根据题意补充代码
            if (CONDITION) {
                i++;
            } else {
                j++;
            }
        }

        while (i < arr1.length) {
            // 根据题意补充代码
            i++;
        }

        while (j < arr2.length) {
            // 根据题意补充代码
            j++;
        }

        return ans;
    }
}
