package com.answer.dynamic_programming;

public class Q97_Interleaving_String {
    /**
     * 交错字符串
     * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
     * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
     *  s = s1 + s2 + ... + sn
     *  t = t1 + t2 + ... + tm
     *  |n - m| <= 1
     *  交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
     * 注意：a + b 意味着字符串 a 和 b 连接。
     */
    /**
     * 双指针法(提交不通过)
     * 直接两个指针，从左到右遍历 s3，如果和 s1 相同，则 s1 的指针+1；如果和 s2 相同，则 s2 的指针+1。
     * 这里存在一个思考的误区。比如对于 “aabcc”, “dbbca”, “aadbbcbcac” 我们得到的是 false，实际上应该是 true。
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length()) {
            return false;
        }
        char[] one = s1.toCharArray();
        char[] two = s2.toCharArray();
        int oneIndex = 0;
        int twoIndex = 0;
        char[] three  =s3.toCharArray();
        for(int i = 0; i < three.length; i++) {
            char c = three[i];
            if(oneIndex < one.length && c == one[oneIndex]) {
                oneIndex++;
            } else if(twoIndex < two.length && c == two[twoIndex]) {
                twoIndex++;
            } else {
                return false;
            }
        }
        return true;
    }
    /**
     * 动态规划
     * 交错从s1和s2拿元素，但每次可能拿多个。
     * 如果s1的前i个字符和s2的前j个字符，能够交替拼出s3的前i+j个字符的话， 那么，s3的下一个字符随便从s1还是s2拿都是有可能的。
     */
    public boolean isInterleave1(String s1, String s2, String s3) {
        int m  = s1.length();
        int n  = s2.length();
        if(m+n != s3.length()) {
            return false;
        }
        boolean[][] dp = new boolean[m+1][n+1]; // 表示 s1 的前 i 个字符和 s2 的前 j 个字符能否交错组成 s3的前 i+j 个字符
        dp[0][0] = true;

        for(int i = 0; i  <= m; i++) {
            for(int j = 0; j <= n; j++) {
                int p = i + j - 1;
                if(i > 0) {
                    dp[i][j] = dp[i][j] || (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p));
                }
                if(j > 0) {
                    dp[i][j] = dp[i][j] || (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }
        // 获取结果
        return dp[m][n];
    }
    /**
     * 另一种形式
     * 定义 f(i,j) 表示 s1 的前 i 个字符和 s2 的前 j 个字符可以交错组成 s3 的前 i+j 个字符。
     *   当 s3[i + j] = s1[i] 时，此时 f(i, j) = f(i - 1, j)；
     *   当 s3[i + j] = s2[j] 时，此时 f(i, j) = f(i, j - 1)。
     * 综上 f(i, j) = f(i - 1, j) || f(i, j - 1)。
     */
    public boolean isInterleave3(String s1, String s2, String s3) {
        int m  = s1.length();
        int n  = s2.length();
        if(m+n != s3.length()) return false;

        boolean[][] dp = new boolean[m+1][n+1]; // 表示 s1 的前 i 个字符和 s2 的前 j 个字符能否交错组成 s3的前 i+j 个字符
        dp[0][0] = true;

        for(int i = 0; i  <= m; i++) {
            for(int j = 0; j <= n; j++) {
                int p = i + j - 1;
                if(i > 0 && s1.charAt(i - 1) == s3.charAt(p)) {
                    dp[i][j] = dp[i - 1][j];
                }
                if(j > 0 && s2.charAt(j - 1) == s3.charAt(p)) {
                    dp[i][j] = dp[i][j] || dp[i][j - 1];
                }
            }
        }
        return dp[m][n];
    }
    /**
     * 优化-滚动数组
     * 通过状态转移方程来看，只用到了dp[i-1][j]和dp[i][j-1],即上一层的数据，再之前的数据就没有用了。可以将二维空间压缩成一维。
     */
    public boolean isInterleave2(String s1, String s2, String s3) {
        int m  = s1.length();
        int n  = s2.length();
        if(m+n != s3.length()) {
            return false;
        }
        boolean[] dp = new boolean[n+1];
        dp[0] = true;
        for(int i = 0; i  <= m; i++) {
            for(int j = 0; j <= n; j++) {
                int p = i + j - 1;
                if(i > 0) {
                    dp[j] = dp[j] && s1.charAt(i - 1) == s3.charAt(p);
                }
                if(j > 0) {
                    dp[j] = dp[j] || (dp[j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }
        // 获取结果
        return dp[n];
    }

}
