package com.answer.math;

import java.util.*;

public class Q1356_Sort_Integers_by_The_Number_of_1_Bits {
    /**
     * You are given an integer array arr. Sort the integers in the array in ascending order by the number of 1's
     * in their binary representation and in case of two or more integers have the same number of 1's you have to sort them in ascending order.
     * 根据数字二进制下 1 的数目排序
     * 给你一个整数数组 arr 。请你将数组中的元素按照其二进制表示中数字 1 的数目升序排序。
     * 如果存在多个数字二进制中 1 的数目相同，则必须将它们按照数值大小升序排列。
     */
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
            if((n & 1) == 1)  {
                count++;  // 当前位是1，count++
            }
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
    /**
     * 方法一：暴力
     */
    public int[] sortByBits_2(int[] arr) {
        int[] bit = new int[10001];
        List<Integer> list = new ArrayList<Integer>();
        for (int x : arr) {
            list.add(x);
            bit[x] = bitCount2(x);
        }
        Collections.sort(list, new Comparator<Integer>() {
            public int compare(Integer x, Integer y) {
                if (bit[x] != bit[y]) {
                    return bit[x] - bit[y];
                } else {
                    return x - y;
                }
            }
        });
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = list.get(i);
        }
        return arr;
    }
    /**
     * 方法二：递推预处理
     * 线性预处理 bit 数组然后去排序即可。
     */
    public int[] sortByBits_3(int[] arr) {
        List<Integer> list = new ArrayList<Integer>();
        for (int x : arr) {
            list.add(x);
        }
        int[] bit = new int[10001];
        for (int i = 1; i <= 10000; ++i) {
            bit[i] = bit[i >> 1] + (i & 1); // 二进制表示下数字 1 的个数
        }
        Collections.sort(list, new Comparator<Integer>() {
            public int compare(Integer x, Integer y) {
                if (bit[x] != bit[y]) {
                    return bit[x] - bit[y];
                } else {
                    return x - y;
                }
            }
        });
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = list.get(i);
        }
        return arr;
    }
    /**
     * 循环并使用 Integer.bitCount 计算数字中1的个数，乘以20000（题目中不会大于 10^4）然后加上原数字，
     * 放入数组 map 中，并对 map 进行排序，最后 % 20000 获取原来的数组，填充到原数组返回即可。
     */
    public int[] sortByBits_4(int[] arr) {
        int[] map = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            map[i] = Integer.bitCount(arr[i]) * 20000 + arr[i];
        }
        Arrays.sort(map);
        for (int i = 0; i < map.length; i++) {
            map[i] = map[i] % 20000;
        }
        return map;
    }
}
