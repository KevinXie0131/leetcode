package com.learn.template;

public class Sliding_Window {
    private static final boolean WINDOW_CONDITION_BROKEN = true; // add to make it compliant
    /**
     * 滑动窗口
     */
    public int fn(int[] arr) {
        int left = 0, ans = 0, curr = 0;

        for (int right = 0; right < arr.length; right++) {
            // 根据题意补充代码来将 arr[right] 添加到 curr

            while (WINDOW_CONDITION_BROKEN) {
                // 从 curr 中删除 arr[left]
                left++;
            }

            // 更新 ans
        }

        return ans;
    }
}
