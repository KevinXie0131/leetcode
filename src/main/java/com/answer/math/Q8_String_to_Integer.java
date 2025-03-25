package com.answer.math;

public class Q8_String_to_Integer {
    /**
     * 按照规则实现即可，为了实现方便，有以下几个注意点：
     *     使用 long 存储结果，方便进行越界判断；
     *     使用额外变量标记正负号，在最后返回时与结果相乘。
     */
    public int myAtoi(String s) {
        int len = s.length();
        char[] arr = s.toCharArray();

        int index = 0;
        while(index < len && arr[index] == ' '){ // 去除前导空格
            index++;
        }
        if(index == len) return 0;

        int sign = 1;
        char first = arr[index];
        if(first == '+'){ // 判断正负号
            index++;
        }else if(first == '-'){
            sign = -1;
            index++;
        }

        int result = 0;
        while(index < len){ // 读取连续字符
            char c = arr[index];
            if(c > '9' || c < '0'){
                break;
            }
            // 溢出判断
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && (c - '0') > Integer.MAX_VALUE % 10)) {
                return Integer.MAX_VALUE;
            }
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && (c - '0') > -(Integer.MIN_VALUE % 10))) {
                return Integer.MIN_VALUE;
            }

            result = result * 10 + sign * (c - '0'); // 要加上正负号
            index++;
        }
        return result;
    }
}
