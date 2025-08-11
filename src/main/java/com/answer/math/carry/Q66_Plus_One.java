package com.answer.math.carry;

import java.math.BigInteger;
import java.util.Collections;
import java.util.*;

public class Q66_Plus_One {
    /**
     * given a large integer represented as an integer array digits, where each digits[i] is the ith digit
     * of the integer. The digits are ordered from most significant to least significant in left-to-right order.
     * The large integer does not contain any leading 0's.
     * Increment the large integer by one and return the resulting array of digits.
     * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     */
    public static void main(String[] args) {
        int[] digits = {9,1};
        int[] res = plusOne_3(digits);
        System.out.println(Arrays.toString(res));
        System.out.println(Integer.MAX_VALUE); // 2147483647
    }
    /**
     *  cannot pass [7,2,8,5,0,9,1,2,9,5,3,6,6,7,3,2,8,4,3,7,9,5,7,7,4,7,4,9,4,7,0,1,1,1,7,4,0,0,6]
     */
    static public int[] plusOne_0(int[] digits) {
        long value = 0;
        for(int i = 0; i <= digits.length - 1; i++){
            value = value * 10 + digits[i];
        }

        value++;

        String str = String.valueOf(value);
        int[] res = new int[str.length()];
        for(int i = 0; i < res.length; i++){
            res[i] = str.charAt(i) - '0';
        }
        return res;
    }
    /**
     * 模拟加法
     */
    static public int[] plusOne_1(int[] digits) {
        int carry = 1;
        StringBuffer str = new StringBuffer();

        for(int i = digits.length - 1; i >= 0; i--){
            int num = digits[i] + carry;
            if(num >= 10) {
                str.append(num % 10);
                carry = 1;
            } else {
                str.append(num);
                carry = 0;
            }
        }
        if(carry == 1) str.append(1);

        int[] res = new int[str.length()];
        for(int i = 0; i < res.length; i++){
            res[i] = str.charAt(res.length - 1 - i) - '0';
        }
        return res;
    }
    /**
     * 模拟加法
     */
    public static int[] plusOne(int[] digits) {
        List<Integer> res = new ArrayList<>();
        int carry = 0;
        int n = digits.length;
        int index = n - 1;
        while(index >= 0){
            int sum = 0;
            if(index == n - 1){
                sum = digits[index] + 1 + carry;
            } else{
                sum = digits[index] + carry;
            }

            carry = sum / 10;
            res.add(sum % 10);

            index--;
        }
        if(carry > 0){
            res.add(carry);
        }
        Collections.reverse(res);
        int[] result = new int[res.size()];
        for(int i = 0; i < res.size(); i++){
            result[i] = res.get(i);
        }
        return result;
    }
    /**
     * 从后向前遍历数字，根据每一位和 9 的大小关系，确定是加一还是置零。
     * < 9 则直接加1
     * = 9 则加1 变成0
     * 如果是999 + 1，则在最前面加1
     */
    public static int[] plusOne_2(int[] digits) {
        int len = digits.length;
        for(int i = len - 1; i >= 0; i--) {
          if(digits[i] < 9){
              digits[i]++;
              break;
          } else { // == 9
              digits[i] = 0;
              if(i == 0){
                  int[] newDigits = new int[len + 1];
             //     int[] newDigits;
             //     newDigits = Arrays.copyOf(digits, len + 1);
                  newDigits[0] = 1;
                  System.out.println(Arrays.toString(newDigits));
                  return newDigits;
              }
          }
        }
        return digits;
    }
    /**
     * 有可能的情况就只有两种：
     *  除 9 之外的数字加一；
     *  数字 9。
     */
    public static int[] plusOne_3(int[] digits) {
        int len = digits.length;
        for(int i = len - 1; i >= 0; i--) {
            digits[i]++; // 循环直到判断没有再进位就退出循环返回结果
            digits[i] %= 10;
            if(digits[i] != 0) {
                return digits; // 加法运算如不出现进位就运算结束
            }
        }
        digits = new int[len + 1];
        digits[0] = 1; // 当出现 99、999 之类的数字时，循环到最后也需要进位，出现这种情况时需要手动将它进一位。
        return digits;
    }
    /**
     * 不用纠结某一位是不是9，而应该去判断加1之后是不是0：
     */
    public int[] plusOne_4(int[] digits) {
        int len = digits.length;
        for (int i = len - 1; i >= 0; i--) {
            digits[i] = (digits[i] + 1) % 10;
            if (digits[i] != 0) {
                return digits;
            }
        }
        // 如果 digits 的所有元素都是 9，例如 [9,9,9,9,9]，那么答案为 [1,0,0,0,0,0]。我们只需要构造一个长度比 digits
        // 多 1 的新数组，将首元素置为 1，其余元素置为 0 即可。
        digits = new int[len + 1];
        digits[0] = 1;
        return digits;
    }
}
