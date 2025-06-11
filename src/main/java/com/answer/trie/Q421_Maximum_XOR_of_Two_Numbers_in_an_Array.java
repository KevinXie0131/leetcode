package com.answer.trie;

import java.util.*;

public class Q421_Maximum_XOR_of_Two_Numbers_in_an_Array {
    /**
     * Given an integer array nums, return the maximum result of nums[i] XOR nums[j], where 0 <= i <= j < n.
     * 数组中两个数的最大异或值
     * 一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n 。
     * 示例 1：
     *  输入：nums = [3,10,5,25,2,8]
     *  输出：28
     *  解释：最大运算结果是 5 XOR 25 = 28.
     * 示例 2：
     *  输入：nums = [14,70,53,83,49,91,36,80,92,51,66,70]
     *  输出：127
     */
    public static void main(String[] args) {
        int[] nums = {7, 2};
        int res = findMaximumXOR1(nums);
        System.out.println(res);
    }
    /**
     * 暴力 Time Limit Exceeded
     */
    public int findMaximumXOR0(int[] nums) {
        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                ans = Math.max(ans, nums[i] ^ nums[j]);
            }
        }
        return ans;
    }
    /**
     * 方法一：哈希表
     * 从高位到低位依次确定 x 二进制表示的每一位
     */
    static final int HIGH_BIT = 30; //  31 个二进制位  最高位的二进制位编号为 30

    public int findMaximumXOR(int[] nums) {
        int x = 0;
        for (int k = HIGH_BIT; k >= 0; --k) {
            Set<Integer> seen = new HashSet<Integer>();
            // 将所有的 pre^k(a_j) 放入哈希表中
            for (int num : nums) {
                seen.add(num >> k); // 如果只想保留从最高位开始到第 k 个二进制位为止的部分 只需将其右移 k 位
            }
            // 目前 x 包含从最高位开始到第 k+1 个二进制位为止的部分
            // 我们将 x 的第 k 个二进制位置为 1，即为 x = x*2+1
            int xNext = x * 2 + 1;
            boolean found = false;
            // 枚举 i
            for (int num : nums) {
                if (seen.contains(xNext ^ (num >> k))) {
                    found = true;
                    break;
                }
            }
            if (found) {
                x = xNext;
            } else {
                x = xNext - 1;// 如果没有找到满足等式的 a_i 和 a_j，那么 x 的第 k 个二进制位只能为 0 即为 x = x*2
            }
        }
        return x;
    }
    /**
     * 用 ⊕ 表示异或，如果 a⊕b=newAns，那么两边同时异或 b，由于 b⊕b=0，所以得到 a=newAns⊕b（相当于把两数之和代码中的减法改成异或）。
     * 这样就可以一边枚举 b，一边在哈希表中查找 newAns⊕b 了
     */
    static public int findMaximumXOR1(int[] nums) {
        int max = 0;
        for (int x : nums) {
            max = Math.max(max, x);
        }
        int highBit = 31 - Integer.numberOfLeadingZeros(max);

        int ans = 0, mask = 0;
        Set<Integer> seen = new HashSet<>();
        for (int i = highBit; i >= 0; i--) { // 从最高位开始枚举
            seen.clear();
            mask |= 1 << i;
            int newAns = ans | (1 << i); // 这个比特位可以是 1 吗？
            for (int x : nums) {
                x &= mask; // 低于 i 的比特位置为 0
                if (seen.contains(newAns ^ x)) {
                    ans = newAns; // 这个比特位可以是 1
                    break;
                }
                seen.add(x);
            }
        }
        return ans;
    }
    /**
     * 求最大值，高位的值能是 1 就应当尽量是 1。
     * 任意两个数「异或」得到第三个数
     */
    static public int findMaximumXOR3(int[] nums) {
        int res = 0;
        int mask = 0;
        // 每一位的确定都要使用新的「哈希表」，为此在循环外初始化，每一轮循环结束清空
        Set<Integer> hashSet = new HashSet<>();
        for (int i = 31; i >= 0; i--) {
            // mask 每一轮的样子是这样的：
            // 1000 0000 0000 0000 0000 0000 0000 0000
            // 1100 0000 0000 0000 0000 0000 0000 0000
            // 1110 0000 0000 0000 0000 0000 0000 0000
            // 1111 0000 0000 0000 0000 0000 0000 0000
            mask = mask | (1 << i);
            for (int num : nums) {
                // 用 & 依次提取最高位
                hashSet.add(num & mask);
            }

            // 这里先假定该位是 1，所以用变量 temp
            int temp = res | (1 << i);
            for (Integer prefix : hashSet) {
                if (hashSet.contains(prefix ^ temp)) {
                    // 如果 temp 与某个前缀的异或结果也在「哈希表」中，说明 temp 是结果的前缀
                    res = temp;
                    break;
                }
            }
            hashSet.clear();
        }
        return res;
    }
    /**
     * 如果不好理解的话，先可以思考这么一个问题：
     * 给定1个数组nums，再给定1个数值x。请判断nums数组中是否存在2个数，且这2个数的异或结果值为x。
     * 暴力法：
     *
     * for(int i = 1; i < nums.length; ++i)
     *     for(int j = 0; j < i; ++j)
     *         if(nums[i] ^ nums[j] == x)
     *             return true;
     * 如何不使用暴力法解决该问题呢？假设数组中存在a和b，满足a ^ b == x，则a ^ x == b与b ^ x == a也必然满足。
     * 反过来讲，你可以遍历数组nums，将当前遍历的数记为a，计算a ^ x，若数组中存在1个数(记为b)，满足b == a ^ x，则说明数组中存在2个数a和b，满足a ^ b == x。
     * 理解了这一点的话，就可利用HashSet，将nums数组中所有数值都存入HashSet，再如下列代码一样操作：
     *
     * for(int num : hashSet)
     *     if(hashSet.contains(num ^ x))
     *         return true;
     */
}
