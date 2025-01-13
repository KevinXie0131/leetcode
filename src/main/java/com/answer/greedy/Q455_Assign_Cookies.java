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
    /**
     * 这里的局部最优就是大饼干喂给胃口大的，充分利用饼干尺寸喂饱一个，全局最优就是喂饱尽可能多的小孩。
     * 可以尝试使用贪心策略，先将饼干数组和小孩数组排序。
     * 然后从后向前遍历小孩数组，用大饼干优先满足胃口大的，并统计满足小孩数量。
     * 时间复杂度：O(nlogn) 空间复杂度：O(1)
     */
    public int findContentChildren1(int[] g, int[] s) { // 先遍历的胃口，再遍历的饼干. 优先考虑胃口，先喂饱大胃口
        Arrays.sort(g);
        Arrays.sort(s);
        int result = 0;
        int index =  s.length - 1; // 饼干数组的下标
        for(int i = g.length - 1; i >= 0 ; i--){ // 遍历胃口
            if(index >= 0 && s[index] >= g[i]){  // 遍历饼干
                index--;
                result++;
            }
        }
        return result;
    }
    /**
     * 也可以换一个思路，小饼干先喂饱小胃口. 因为遍历顺序变了，我们是从小到大遍历。
     */
    public int findContentChildren2(int[] g, int[] s) { // 优先考虑饼干，小饼干先喂饱小胃口
        Arrays.sort(g);
        Arrays.sort(s);
        int index = 0;
        int count = 0;
        for(int i = 0; i < s.length; i++) { // 饼干
            if(index < g.length && g[index] <= s[i]){ // 胃口
                index++;
                count++;
            }
        }

        return count;
    }
}
