package com.answer.math.carry;

import java.math.BigInteger;
import java.util.Collections;
import java.util.*;

public class Q66_Plus_One {

    public static void main(String[] args) {
        int[] digits = {9,9,9};
        int[] res = plusOne_1(digits);
        System.out.println(Arrays.toString(res));
    }

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
