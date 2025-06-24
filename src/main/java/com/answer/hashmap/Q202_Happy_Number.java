package com.answer.hashmap;

import java.util.HashSet;
import java.util.Set;

public class Q202_Happy_Number {
    /**
     * 快乐数
     * 编写一个算法来判断一个数 n 是不是快乐数。
     * 「快乐数」 定义为：
     *  对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
     *  然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
     *  如果这个过程 结果为 1，那么这个数就是快乐数。
     *  Starting with any positive integer, replace the number by the sum of the squares of its digits.
     *  Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
     *  Those numbers for which this process ends in 1 are happy.
     * 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
     *
     * 示例 1：
     * 输入：n = 19
     * 输出：true
     * 解释：
     * 1^2 + 9^2 = 82
     * 8^2 + 2^2 = 68
     * 6^2 + 8^2 = 100
     * 1^2 + 0^2 + 0^2 = 1
     */
    /**
     * loops endlessly in a cycle无限循环，那么也就是说求和的过程中，sum会重复出现，这对解题很重要
     * 当我们遇到了要快速判断一个元素是否出现集合里的时候，就要考虑哈希法了。
     */
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while(n != 1 && !set.contains(n)){  // 如果这个sum曾经出现过，说明已经陷入了无限循环了，立刻return false
            set.add(n);
            n = getNextNumber(n);
        }

        return n == 1;
    }
    // 取数值各个位上的单数之和
    public int getNextNumber(int n){
        int result = 0;
        while(n > 0){
            int temp = n % 10;
            result += temp * temp;
            n = n / 10;

        }
        return result;
    }
    /**
     * 使用 “快慢指针” 思想，找出循环：“快指针” 每次走两步，“慢指针” 每次走一步，当二者相等时，即为一个循环周期。
     * 此时，判断是不是因为 1 引起的循环，是的话就是快乐数，否则不是快乐数。
     * 注意：此题不建议用集合记录每次的计算结果来判断是否进入循环，因为这个集合可能大到无法存储；
     * 另外，也不建议使用递归，同理，如果递归层次较深，会直接导致调用栈崩溃。不要因为这个题目给出的整数是 int 型而投机取巧。
     */
    int bitSquareSum(int n) {
        int sum = 0;
        while(n > 0)
        {
            int bit = n % 10;
            sum += bit * bit;
            n = n / 10;
        }
        return sum;
    }
    // 快慢指针判断有没有环
    boolean isHappy_1(int n) {
        int slow = n, fast = n;
        do{
            slow = bitSquareSum(slow);
            fast = bitSquareSum(fast);
            fast = bitSquareSum(fast);
        }while(slow != fast); // 如果 n 不是一个快乐的数字，那么最终快跑者和慢跑者将在同一个数字上相遇。

        return slow == 1; // 如果是1，则是快乐数
    }
}
