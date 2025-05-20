package com.answer.bit;

import org.omg.Messaging.SyncScopeHelper;

import java.util.*;

public class Q89_Gray_Code {
    /**
     * An n-bit gray code sequence is a sequence of 2^n integers where:
     *   Every integer is in the inclusive range [0, 2^n - 1],
     *   The first integer is 0,
     *   An integer appears no more than once in the sequence,
     *   The binary representation of every pair of adjacent integers differs by exactly one bit, and
     *   The binary representation of the first and last integers differs by exactly one bit.
     *   Given an integer n, return any valid n-bit gray code sequence
     * n 位格雷码序列 是一个由 2^n 个整数组成的序列，其中：
     *   每个整数都在范围 [0, 2^n - 1] 内（含 0 和 2^n - 1）
     *   第一个整数是 0
     *   一个整数在序列中出现 不超过一次
     *   每对 相邻 整数的二进制表示 恰好一位不同 ，且
     *   第一个 和 最后一个 整数的二进制表示 恰好一位不同
     *   给你一个整数 n ，返回任一有效的 n 位格雷码序列 。
     */
    public static void main(String[] args) {
        int n = 3;
        System.out.println(grayCode(n)); // [0,1,3,2] or [0,2,3,1]
    }
    /**
     * 方法一：归纳法
     */
    static public List<Integer> grayCode(int n) {
        List<Integer> ret = new ArrayList<Integer>();
        ret.add(0);
        for (int i = 1; i <= n; i++) {
            int m = ret.size();
            for (int j = m - 1; j >= 0; j--) {
                ret.add(ret.get(j) | (1 << (i - 1)));
            }
        }
        return ret;
    }
    /**
     * 给 G(n) 阶格雷码每个元素二进制形式前面添加 0
     * 设 G(n) 集合倒序（镜像）为 R(n)，给 R(n) 每个元素二进制形式前面添加 1
     * 拼接两个集合即可得到下一阶格雷码。
     * 时间复杂度：O(2^n)
     * 空间复杂度：O(1)
     */
    static public List<Integer> grayCode_1(int n) {
        List<Integer> res = new ArrayList<Integer>() {{ add(0); }};
        int head = 1;
        for (int i = 0; i < n; i++) {
            for (int j = res.size() - 1; j >= 0; j--)   // 注意这里要倒序
                res.add(head + res.get(j));
            head <<= 1;
        }
        return res;
    }
}
