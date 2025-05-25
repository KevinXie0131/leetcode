package com.answer.math.carry;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Q989_Add_to_Array_Form_of_Integer {
    /**
     * The array-form of an integer num is an array representing its digits in left to right order.
     * For example, for num = 1321, the array form is [1,3,2,1].
     * Given num, the array-form of an integer, and an integer k, return the array-form of the integer num + k.
     * 整数的 数组形式  num 是按照从左到右的顺序表示其数字的数组。
     * 例如，对于 num = 1321 ，数组形式是 [1,3,2,1] 。
     * 给定 num ，整数的 数组形式 ，和整数 k ，返回 整数 num + k 的 数组形式 。
     */
    public static void main(String[] args) {
        int[] num = {1,2,0,0};
        int k = 34;
        List<Integer> res = addToArrayForm2(num, k);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(res);
    }
    /*
     * cannot handle num = [0], k = 23
     */
    static public List<Integer> addToArrayForm1(int[] num, int k) {
        int carry = 0;
        StringBuffer str = new StringBuffer();

        for(int i = num.length - 1; i >= 0; i--){
            int addVal = (k % (int)Math.pow(10, num.length - i));
            int val = num[i] + carry + addVal/(int)Math.pow(10, num.length - i - 1);
            if(val >= 10) {str.append(val % 10); carry = 1;}
            else {str.append(val); carry = 0;}
        }
        if(carry == 1) str.append(1);

        List<Integer> res = new ArrayList<>();
        for(int i = 0; i < str.length(); i++){
            res.add(str.charAt(str.length() - 1 - i) - '0');
        }
        return res;
    }
    /**
     * Refer to Q415_Add_Strings. can work
     */
    static public List<Integer> addToArrayForm2(int[] num, int k) {
       String num1 = String.valueOf(k);
       String num2 = Arrays.stream(num)
                           .mapToObj(String::valueOf)
                           .collect(Collectors.joining());
       // Refer to Q415_Add_Strings
       List<Integer> res = new ArrayList<>();
       int i = num1.length() - 1, j = num2.length() - 1, carry = 0;
       char[] num1Char = num1.toCharArray();
       char[] num2Char = num2.toCharArray();
       while (i >= 0 || j >= 0 || carry != 0) {
           int x = i < 0 ? 0 : num1Char[i--] - '0';
           int y = j < 0 ? 0 : num2Char[j--] - '0';
           int sum = x + y + carry;
           res.add(sum % 10);//添加到字符串尾部
           carry = sum / 10;
       }
       Collections.reverse(res);
       return res;
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
     * 逐位相加
     * 逐位将数字加在一起, 从低位到高位依次计算, 若加法的结果大于等于 10，把进位的 1 加入到下一位的计算中
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
     * 逐位相加 + 链表
     */
    public List<Integer> addToArrayForm_3(int[] num, int k) {
        int size = num.length;
        LinkedList<Integer> result = new LinkedList<>();
        int carry = 0;
        for(int i = size - 1; i >= 0; i--){
            int remainder = k % 10;
            k = k / 10;
            int total = num[i] + carry + remainder;
            result.addFirst(total % 10);
            carry = total / 10;
        }

        k = k + carry;
        while(k > 0){
            result.addFirst(k % 10);
            k = k / 10;
        }

        return result;
    }
    /**
     * Can be used as model
     * <公式>
     * 当前位 = (A 的当前位 + B 的当前位 + 进位carry) % 10
     * 注意，AB两数都加完后，最后判断一下进位 carry, 进位不为 0 的话加在前面。
     *
     * <加法模板>
     * while ( A 没完 || B 没完)
     *     A 的当前位
     *     B 的当前位
     *
     *     和 = A 的当前位 + B 的当前位 + 进位carry
     *
     *     当前位 = 和 % 10;
     *     进位 = 和 / 10;
     * 判断还有进位吗
     */
    public List<Integer> addToArrayForm_2(int[] num, int k) {
        List<Integer> res = new ArrayList<>(); // 返回结果
        int index = num.length - 1; // 标记遍历到 num 的位置
        int carry = 0; // 进位
        while (index >= 0 || k != 0 || carry != 0) { // num 没遍历完，或 k 没遍历完，或进位不为 0
            int adder1 = index >= 0 ? num[index] : 0; // 当前 num 的取值
            int adder2 = k % 10; // 当前 k 的位置，如果 k 已经是 0 那么 % 10 以后仍然是 0
            int sum = adder1 + adder2 + carry; // 当前位置相加的结果
            carry = sum / 10;   // 是否有进位
            res.add(sum % 10);  // 把去除进位后留下的数字拼接到结果中
            index --; // 遍历到 num 的位置向左移动
            k /= 10; // 取 k 的下一个位置的数字
        }
        Collections.reverse(res); // 把结果反转
        return res;
    }
}
