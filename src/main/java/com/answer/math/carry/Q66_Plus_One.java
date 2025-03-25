package com.answer.math.carry;

import java.math.BigInteger;
import java.util.Collections;
import java.util.*;

public class Q66_Plus_One {
    public static void main(String[] args) {
        int[] digits = {9};
        int[] res = plusOne_2(digits);
        System.out.println(Arrays.toString(res));
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
        for(int i = 0; i<res.size(); i++){
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
          } else {
              digits[i] = 0;
              if(i == 0){
                  int[] newDigits = new int[len + 1];
                  newDigits = Arrays.copyOf(digits, len + 1);
                  newDigits[0] = 1;
                  System.out.println(Arrays.toString(newDigits));
                  return newDigits;
              }
          }
        }
        return digits;
    }
    /**
     *
     */
    public static int[] plusOne_1(int[] digits) {
        int len = digits.length;
        for(int i = len - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] %= 10;
            if(digits[i]!=0)
                return digits;
        }
        digits = new int[len + 1];
        digits[0] = 1;
        return digits;
    }
}
