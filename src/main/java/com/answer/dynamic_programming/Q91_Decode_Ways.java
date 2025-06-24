package com.answer.dynamic_programming;

public class Q91_Decode_Ways {
    /**
     * 解码方法
     * 一条包含字母 A-Z 的消息通过以下映射进行了 编码 ：
     *  "1" -> 'A'
     *  "2" -> 'B'
     *  ...
     *  "25" -> 'Y'
     *  "26" -> 'Z'
     * 然而，在 解码 已编码的消息时，你意识到有许多不同的方式来解码，因为有些编码被包含在其它编码当中（"2" 和 "5" 与 "25"）。
     * 例如，"11106" 可以映射为：
     *  "AAJF" ，将消息分组为 (1, 1, 10, 6)
     *  "KJF" ，将消息分组为 (11, 10, 6)
     *  消息不能分组为  (1, 11, 06) ，因为 "06" 不是一个合法编码（只有 "6" 是合法的）。
     * 注意，可能存在无法解码的字符串。
     * 给你一个只含数字的 非空 字符串 s ，请计算并返回 解码 方法的 总数 。如果没有合法的方式解码整个字符串，返回 0。
     */
    /**
     * Dynamic Programming
     *
     * 这其实是一道字符串类的动态规划题，不难发现对于字符串 s 的某个位置 i 而言，我们只关心「位置 i 自己能否形成独立 item 」
     * 和「位置 i 能够与上一位置（i-1）能否形成 item」，而不关心 i-1 之前的位置。
     *
     * 定义f[i] 为考虑前 i 个字符的解码方案数
     * 只能由位置 i 的单独作为一个 item，设为 a，转移的前提是 a 的数值范围为 [1,9]，转移逻辑为 f[i]=f[i−1]
     * 只能由位置 i 的与前一位置（i-1）共同作为一个 item，设为 b，转移的前提是 b 的数值范围为 [10,26]，转移逻辑为 f[i]=f[i−2]。
     * 位置 i 既能作为独立 item 也能与上一位置形成 item，转移逻辑为 f[i]=f[i−1]+f[i−2]
     */
    public int numDecodings(String s) {
        int n = s.length();
        s = " " + s;
        char[] cs = s.toCharArray();
        int[] f = new int[n + 1];
        f[0] = 1; // 空字符串可以有 1 种解码方法，解码出一个空字符串

        for (int i = 1; i <= n; i++) {
            // a : 代表「当前位置」单独形成 item
            // b : 代表「当前位置」与「前一位置」共同形成 item
            int a = cs[i] - '0';
            int b = (cs[i - 1] - '0') * 10 + (cs[i] - '0');

            // 如果 a 属于有效值，那么 f[i] 可以由 f[i - 1] 转移过来
            if (1 <= a && a <= 9) f[i] = f[i - 1];
            // 如果 b 属于有效值，那么 f[i] 可以由 f[i - 2] 或者 f[i - 1] & f[i - 2] 转移过来
            if (10 <= b && b <= 26) f[i] += f[i - 2];
        }
        return f[n];
    }
    /**
     * Another Form of Dynamic Programming
     */
    public int numDecodings_1(String s) {
        int n = s.length();
        // DP array to store the subproblem results
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; ++i) {
            // Check if successful single digit decode is possible.
            if (s.charAt(i - 1) != '0') {
                dp[i] += dp[i - 1];
            }
            // Check if successful two digit decode is possible.
            if (i > 1 && s.charAt(i - 2) != '0'
                    && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }
    /**
     * 动态规划 使用 O(n) 的空间
     */
    public int numDecodings2(String s) {
        char[] chars = s.toCharArray();
        if(chars[0] == '0') { // s[i] 为0，而且没有上一个元素。
            return 0;
        }
        int len = s.length();
        int[] dp = new int[len + 1]; // dp[i] 为 s[0…i] 的编码数
        // dp[-1] = dp[0] = 1
        // 为什么初始化为 1？？
        // 因为此时 chars[0] != '0'，所以 dp[0] = 1;
        // dp[-1] 这里也是1，纯粹是为了 dp[i-1] 时的推导。
        dp[0] = 1;
        dp[1] = 1;
        // 开始遍历
        for(int i = 1; i < len; i++) {
            if (chars[i] == '0') { //1. 如果当前元素为0
                if (chars[i - 1] == '1' || chars[i - 1] == '2') {  //s[i - 1]等于1或2的情况
                    dp[i + 1] = dp[i - 1]; //由于s[1]指第二个下标，对应为dp[2],所以dp的下标要比s大1，故为dp[i+1]
                } else {
                    return 0;
                }
            } else {
                if (chars[i - 1] == '1' || (chars[i - 1] == '2' && chars[i] <= '6')) {//开始的数值为1，后面的数值 0-9 都满足条件。s[i-1]s[i]两位数要小于26的情况
                    dp[i + 1] = dp[i] + dp[i - 1];
                } else {
                    dp[i + 1] = dp[i];  // 即当前状态值等于前一个状态
                }
            }
        }
        return dp[len];
    }
}
