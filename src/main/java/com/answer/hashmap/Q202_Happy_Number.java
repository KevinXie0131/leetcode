package com.answer.hashmap;

import java.util.HashSet;
import java.util.Set;

public class Q202_Happy_Number {
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
}
