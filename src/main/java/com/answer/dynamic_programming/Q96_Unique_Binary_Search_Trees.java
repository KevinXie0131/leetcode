package com.answer.dynamic_programming;

public class Q96_Unique_Binary_Search_Trees {
    /**
     * Approach 1: Dynamic Programming
     * 互不相同的 二叉搜索树 有多少种
     * For example: dp[3] = dp[0]*dp[2] + dp[1]*dp[1] + dp[2]*dp[0]
     *
     * dp[3]，就是 元素1为头结点搜索树的数量 + 元素2为头结点搜索树的数量 + 元素3为头结点搜索树的数量
     * 元素1为头结点搜索树的数量 = 右子树有2个元素的搜索树数量 * 左子树有0个元素的搜索树数量
     * 元素2为头结点搜索树的数量 = 右子树有1个元素的搜索树数量 * 左子树有1个元素的搜索树数量
     * 元素3为头结点搜索树的数量 = 右子树有0个元素的搜索树数量 * 左子树有2个元素的搜索树数量
     * 有n个元素的搜索树数量就是dp[n]。
     * 所以dp[3] = dp[2] * dp[0] + dp[1] * dp[1] + dp[0] * dp[2]
     *
     * dp[i] += dp[以j为头结点左子树节点数量] * dp[以j为头结点右子树节点数量]. j相当于是头结点的元素，从1遍历到i为止。
     * 所以递推公式：dp[i] += dp[j - 1] * dp[i - j]; ，j-1 为j为头结点左子树节点数量，i-j 为以j为头结点右子树节点数量
     */
    public int numTrees(int n) {
        int[] dp = new int[n+1]; // dp[i] ： 1到i为节点组成的二叉搜索树的个数为dp[i]。

        dp[0] = 1; // 0个节点只有1种
        for(int i = 1; i < n + 1; i++){
            for(int j = 1; j <= i; j++){
                //对于第i个节点，需要考虑1作为根节点直到i作为根节点的情况，所以需要累加
                //一共i个节点，对于根节点j时,左子树的节点个数为j-1，右子树的节点个数为i-j
                dp[i] += dp[i - j] * dp[j - 1]; // 总共i个节点，左面有j-1个节点，右面有i-j个节点
            }
        }

        return dp[n];
    }
}
