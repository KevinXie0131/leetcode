package com.answer.array;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Q989_Add_to_Array_Form_of_Integer {

    public static void main(String[] args) {
        int[] num = {9,9,9,9,9,9,9,9,9,9};
        int k = 1;
        List<Integer> res = addToArrayForm(num, k);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(res);
    }

    /**
     * Cannot handle [1,2,6,3,0,7,1,7,1,9,7,5,6,6,4,4,0,0,6,3] + 516
     */
    public static  List<Integer> addToArrayForm(int[] num, int k) {
        int n = num.length;
        List<Long> res = new ArrayList<>();
        List<Integer> result = new ArrayList<>();

        long sum = 0;
        for(int i = 0; i < n; i++){
            sum = sum * 10;
            sum += num[i];
        }

        sum += k;
        while(sum > 0){
            res.add(sum % 10);
            sum = sum / 10;
        }

        Collections.reverse(res);

        for(Long val : res){
            result.add(val.intValue()); // convert long to int
        }

        return result;
    }

}
