package com.answer.math;

public class Q8_String_to_Integer {
    /**
     * Implement the myAtoi(string s) function, which converts a string to a 32-bit signed integer.
     * 你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数。
     * 函数 myAtoi(string s) 的算法如下：
     *   空格：读入字符串并丢弃无用的前导空格（" "）
     *   符号：检查下一个字符（假设还未到字符末尾）为 '-' 还是 '+'。如果两者都不存在，则假定结果为正。
     *   转换：通过跳过前置零来读取该整数，直到遇到非数字字符或到达字符串的结尾。如果没有读取数字，则结果为0。
     *   舍入：如果整数数超过 32 位有符号整数范围 [−2^31,  2^31 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −2^31 的整数应该被舍入为 −2^31 ，大于 2^31 − 1 的整数应该被舍入为 2^31 − 1 。
     *   返回整数作为最终结果。
     */
    /**
     * 按照规则实现即可，为了实现方便，有以下几个注意点：
     *     使用 long 存储结果，方便进行越界判断；
     *     使用额外变量标记正负号，在最后返回时与结果相乘。
     */
    public int myAtoi(String s) {
        int len = s.length();
        char[] arr = s.toCharArray(); // str.charAt(i) 方法回去检查下标的合法性，一般先转换成字符数组

        int index = 0;
        while(index < len && arr[index] == ' '){ // 去除前导空格
            index++;
        }
        if(index == len) return 0;   // 2、如果已经遍历完成（针对极端用例 "      "）

        int sign = 1;    // 3、如果出现符号字符，仅第 1 个有效，并记录正负
        char first = arr[index];
        if(first == '+'){ // 判断正负号
            index++;
        }else if(first == '-'){
            sign = -1;
            index++;
        }
        // 4、将后续出现的数字字符进行转换
        // 不能使用 long 类型，这是题目说的
        int result = 0;
        while(index < len){ // 读取连续字符
            char c = arr[index];
            if(c > '9' || c < '0'){ // 4.1 先判断不合法的情况
                break;
            }
            // 溢出判断
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && (c - '0') > Integer.MAX_VALUE % 10)) {
                return Integer.MAX_VALUE;  // 题目中说：环境只能存储 32 位大小的有符号整数，因此，需要提前判：断乘以 10 以后是否越界
            }
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && (c - '0') > -(Integer.MIN_VALUE % 10))) {
                return Integer.MIN_VALUE;
            }
            // 4.2 合法的情况下，才考虑转换，每一步都把符号位乘进去
            result = result * 10 + sign * (c - '0'); // 要加上正负号
            index++;
        }
        return result;
    }
    /**
     * 数字越界处理：
     * 在每轮数字拼接前，判断 res 在此轮拼接后是否超过 2147483647 ，若超过则加上符号位直接返回。
     * 设数字拼接边界 boundary = 2147483647 / 10=214748364 ，则以下两种情况越界：
     *    res > boundary            情况一：执行拼接10 × res ≥ 2147483650越界
     *    res = boundary && x > 7   情况二：拼接后是2147483648或2147483649越界
     */
    public int myAtoi_1(String s) {
        char[] c = s.trim().toCharArray();
        if (c.length == 0) return 0;

        int res = 0;
        int boundary = Integer.MAX_VALUE / 10;
        int i = 1;
        int sign = 1;

        if (c[0] == '-') {
            sign = -1;
        } else if (c[0] != '+') {
            i = 0;
        }
        for (int j = i; j < c.length; j++) {
            if (c[j] < '0' || c[j] > '9') break;

            if (res > boundary || res == boundary && c[j] > '7') return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

            res = res * 10 + (c[j] - '0');
        }
        return sign * res;
    }
    /**
     * 非正则解法
     */
    public int myAtoi_2(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        String strTrim = str.trim();//去字符前后空格
        if("".equals(strTrim)) return 0;//表示字符串为空或字符串全为空格的情况

        char first = strTrim.charAt(0);//取去掉字符串后的首字母
        if(first == '-' || first == '+' || (first >= '0' && first <= '9')){
            stringBuffer.append(first);//首字母为+ -,数字
        }else if(first < '0' || first > '9'){
            //字符串中的第一个非空格字符不是一个有效整数字符
            return 0;
        }
        for(int i = 1; i < strTrim.length(); i++){
            if(strTrim.charAt(i) < '0' || strTrim.charAt(i) > '9'){
                break;
            }
            stringBuffer.append(strTrim.charAt(i));
        }
        if(stringBuffer.toString().equals("+") || stringBuffer.toString().equals("-")){
            //如果该有效字符只为+，-
            return 0;
        }
        int number;
        try{
            number = Integer.parseInt(stringBuffer.toString());
            return number;
        }catch(Exception e){
            return strTrim.charAt(0) == '-' ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
    }
}
