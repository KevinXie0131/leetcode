package com.answer.math;

public class Q405_Convert_a_Number_to_Hexadecimal {
    /**
     * Given a 32-bit integer num, return a string representing its hexadecimal representation. For negative integers, two’s complement method is used.
     * All the letters in the answer string should be lowercase characters, and there should not be any leading zeros in the answer except for the zero itself.
     * Note: You are not allowed to use any built-in library method to directly solve this problem.
     * 给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 补码运算 方法。
     * 答案字符串中的所有字母都应该是小写字符，并且除了 0 本身之外，答案中不应该有任何前置零。
     * 注意: 不允许使用任何由库提供的将数字直接转换或格式化为十六进制的方法来解决这个问题。
     */
    public static void main(String[] args) {
        System.out.println(toHex_1(-26));
    }
    /**
     * Simulation
     */
    public static String toHex(int num) {
        if(num == -1) {
            return "ffffffff";
        }
        if(num == 0) {
            return "0";
        }
        long value = num;
        if(value <= 0) {
            value = (long)(value + Math.pow(2, 32)); // 需要处理「补码」问题：对于负数的 num，我们需要先在 num 基础上加上 2^32 的偏移量，再进行进制转换。
        }
        StringBuffer sb = new StringBuffer();

        String str="abcdef";
        while(value != 0){
            long mod = value % 16;
            if(mod < 10){
                sb.append((char)('0' + mod));
            }else{
                sb.append(str.charAt((int)(mod - 10)));
            }
           /* char c = (char)(mod + '0');
            if (mod >= 10) c = (char)(mod - 10 + 'a');
            sb.append(c);*/
            value = value / 16;
        }
        sb.reverse();
        return sb.toString();
    }
    /**
     * Bit 位运算 + 分组换算
     * 将长度为 32 的二进制转换为 16 进制数，本质是对长度为 32 的二进制数进行分组，每 4 个一组（二进制 (1111) 表示 15，则使用长度为 4 的二进制可以表示 0-15）。
     * 长度为 32 的二进制本身就是使用补码规则来表示的，因此我们无须额外处理「补码」问题。
     */
    public static String toHex_1(int num) {
        if(num == -1) {
            return "ffffffff";
        }
        if(num == 0) {
            return "0";
        }
        int f= 0xf;
        StringBuffer sb = new StringBuffer();

        char[] ch = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        while(num != 0){
            sb.append(ch[num & f]);
/*            int u = num & 15;
            char c = (char)(u + '0');
            if (u >= 10) c = (char)(u - 10 + 'a');
            sb.append(c);*/
            num = num >>> 4; // 逻辑右移4位
        }

        sb.reverse();
        return sb.toString();
    }
}
