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

    /**
     *
     */
    public List<Integer> addToArrayForm_1(int[] num, int k) {
        int n = num.length;
        List<Integer> result = new ArrayList<>();

        int carry = 0;
        int index = n - 1;
        while(index >= 0 || k != 0){
            int x = index < 0 ? 0 : num[index];
            int y = k == 0 ? 0 : k % 10;

            int sum = x + y + carry;

            result.add(sum % 10);
            carry = sum / 10;

            index--;
            k = k / 10;
        }
        if(carry > 0){
            result.add(carry);
        }
        Collections.reverse(result);
        return result;
    }
    /**
     * Can be used as model
     */
    public List<Integer> addToArrayForm_2(int[] num, int k) {
        List<Integer> res = new ArrayList<>(); // 返回结果
        int p1 = num.length - 1; // 标记遍历到 num 的位置
        int carry = 0; // 进位
        while (p1 >= 0 || k != 0 || carry != 0) { // num 没遍历完，或 k 没遍历完，或进位不为 0
            int adder1 = p1 >= 0 ? num[p1] : 0; // 当前 num 的取值
            int adder2 = k % 10; // 当前 k 的位置，如果 k 已经是 0 那么 % 10 以后仍然是 0
            int sum = adder1 + adder2 + carry; // 当前位置相加的结果
            carry = sum >= 10 ? 1 : 0; // 是否有进位
            sum = sum >= 10 ? sum - 10 : sum; // 去除进位后留下的数字
            res.add(sum); // 把去除进位后留下的数字拼接到结果中
            p1 --; // 遍历到 num 的位置向左移动
            k /= 10; // 取 k 的下一个位置的数字
        }
        Collections.reverse(res); // 把结果反转
        return res;
    }
}
