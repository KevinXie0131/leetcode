package com.answer.math;

import java.util.Arrays;
import java.util.Comparator;

public class Q1356_Sort_Integers_by_The_Number_of_1_Bits {
    public static void main(String[] args) {
        // The symbol & denotes the bitwise AND operator.
        // It evaluates the binary value of given numbers.
        // The binary result of these numbers will be returned to us in base 10.
        System.out.println(10 & 12);
        // returns 8
        int[] arr = {0,1,2,3,4,5,6,7,8};
        System.out.println(Arrays.toString(sortByBits(arr)));
    }
    /**
     * 考察如何计算一个数的二进制中1的数量
     */
   static public int[] sortByBits(int[] arr) {
       return Arrays.stream(arr).boxed()
               .sorted(new Comparator<Integer>(){
                   @Override
                   public int compare(Integer o1, Integer o2) {
                       int cnt1 = bitCount1(o1);
                       int cnt2 = bitCount1(o2);
                       // 如果bit中1数量相同，比较数值大小
                       // 否则比较bit中1数量大小
                       return (cnt1 == cnt2) ? Integer.compare(o1, o2) : Integer.compare(cnt1, cnt2);
                   }
               })
               .mapToInt(Integer::intValue)
               .toArray();
    }
    /**
     * 方法一：朴实无华挨个计算1的数量，最多就是循环n的二进制位数，32位。
     */
    static int bitCount1(int n) {
        int count = 0; // 计数器
        while (n > 0) {
            if((n & 1) == 1)  count++;  // 当前位是1，count++
            n >>= 1 ; // n向右移位
        }
        return count;
    }
    /**
     * 方法二: 种方法，只循环n的二进制中1的个数次，比方法一高效的多
     */
    int bitCount2(int n) { // 计算n的二进制中1的数量
        int count = 0;
        while (n > 0) {
            n &= (n - 1); // 清除最低位的1
            count++;
        }
        return count;
    }

}
