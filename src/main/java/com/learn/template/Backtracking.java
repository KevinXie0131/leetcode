package com.learn.template;

import java.util.List;

public class Backtracking {
    private static final boolean BASE_CASE = true;
    private static final Object ITERATE_OVER_INPUT = true;

    /**
     * 回溯
     */
    public int backtrack(List<Integer> curr, int level) {
        if (BASE_CASE) {
            // 修改答案
            return 0;
        }

        int ans = 0;
        for (int i : curr) {
            // 修改当前状态
            ans += backtrack(curr, level + 1);
            // 撤消对当前状态的修改
        }
        return ans;
    }
    /**
     * public int backtrack(STATE curr, OTHER_ARGUMENTS...) {
     *     if (BASE_CASE) {
     *         // 修改答案
     *         return 0;
     *     }
     *
     *     int ans = 0;
     *     for (ITERATE_OVER_INPUT) {
     *         // 修改当前状态
     *         ans += backtrack(curr, OTHER_ARGUMENTS...)
     *         // 撤消对当前状态的修改
     *     }
     * }
     */
}
