package com.answer.math;

public class Q779_K_th_Symbol_in_Grammar {
    /**
     * We build a table of n rows (1-indexed). We start by writing 0 in the 1st row. Now in every subsequent row,
     * we look at the previous row and replace each occurrence of 0 with 01, and each occurrence of 1 with 10.
     * 第K个语法符号
     * 我们构建了一个包含 n 行( 索引从 1  开始 )的表。首先在第一行我们写上一个 0。接下来的每一行，将前一行中的0替换为01，1替换为10。
     * 例如，对于 n = 3 ，第 1 行是 0 ，第 2 行是 01 ，第3行是 0110 。
     * 给定行数 n 和序数 k，返回第 n 行中第 k 个字符。（ k 从索引 1 开始）
     */
    public static void main(String[] args) {
        System.out.println(kthGrammar(4, 6));
    }
    /**
     * 找规律 + 递归
     * 0
     * 01
     * 0110
     * 01101001
     * 可以注意到规律：每一行的后半部分正好为前半部分的“翻转”——前半部分是 0 后半部分变为 1，前半部分是 1，后半部分变为 0。
     * 且每一行的前半部分和上一行相同。
     *
     * 对于查询某一个行第 k 个数字，如果 k 在后半部分，那么原问题就可以转化为求解该行前半部分的对应位置的“翻转”数字，
     * 又因为该行前半部分与上一行相同，所以又转化为上一行对应对应的“翻转”数字。那么按照这样一直递归下去，并在第一行时返回数字 0 即可。

     */
    static public int kthGrammar(int n, int k) {
        if (k == 1) {
            return 0; // 向上遍历到了第1行，则返回结果
        }
        if (k > (1 << n - 2)) {
            return 1 ^ kthGrammar(n - 1, k - (1 << n - 2)); // 如果在“蓝色区间”，则与上一行值相反
        } else {
            return kthGrammar(n - 1, k); // 如果在“黄色区间”，则与上一行值相同
        }
    }
}
