package com.answer.greedy;

import java.util.*;

public class Q455_Assign_Cookies {
    /**
     * Greedy algorithm
     * 因为本题用到了贪心算法所以先来了解一下「贪心算法」的问题需要满足的条件：
     *
     * + 最优子结构：规模较大的问题的解由规模较小的子问题的解组成，规模较大的问题的解只由其中一个规模较小的子问题的解决定；
     * + 无后效性：后面阶段的求解不会修改前面阶段已经计算好的结果；
     * + 贪心选择性质：从局部最优解可以得到全局最优解。
     *
     * 综上可得：贪心算法就是做出当前状态下的最优选择认为就可以解决问题。
     */
    public int findContentChildren(int[] g, int[] s) {
        int res = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        int m = g.length;
        int n = s.length;

        for(int i = 0, j = 0; i < m && j < n; j++){
            if(s[j] >= g[i]){
                res++;
                i++;
            }
        }

        return res;
    }
}
