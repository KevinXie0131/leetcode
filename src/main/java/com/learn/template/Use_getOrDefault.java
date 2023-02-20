package com.learn.template;

import java.util.*;

public class Use_getOrDefault {
    /**
     * 找到符合确切条件的子数组数
     */
    public int fn(int[] arr, int k) {
        Map<Integer, Integer> counts = new HashMap<>();
        counts.put(0, 1);
        int ans = 0, curr = 0;

        for (int num: arr) {
            // 根据题意补充代码来改变 curr
            ans += counts.getOrDefault(curr - k, 0);
            counts.put(curr, counts.getOrDefault(curr, 0) + 1);
        }

        return ans;
    }

}
