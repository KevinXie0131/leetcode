package com.answer.math;

public class Q405_Convert_a_Number_to_Hexadecimal {

    public static void main(String[] args) {
        System.out.println(toHex_1(26));
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
            value = (long)(value + Math.pow(2, 32));
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
            value = value / 16;
        }
        sb.reverse();
        return sb.toString();
    }
    /**
     * Bit
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
            num = num >>> 4;
        }

        sb.reverse();
        return sb.toString();
    }
}
