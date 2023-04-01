package com.answer.dynamic_programming;

public class Q91_Decode_Ways {
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
        int[] f = new int[n + 1];
        f[0] = 1;

        for (int i = 1; i <= n; ++i) {
            if (s.charAt(i - 1) != '0') {
                f[i] += f[i - 1];
            }
            if (i > 1 && s.charAt(i - 2) != '0'
                    && ((s.charAt(i - 2) - '0') * 10 + (s.charAt(i - 1) - '0') <= 26)) {
                f[i] += f[i - 2];
            }
        }
        return f[n];
    }
}
