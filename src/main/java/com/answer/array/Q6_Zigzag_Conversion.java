package com.answer.array;

import java.util.*;

public class Q6_Zigzag_Conversion {
    public static void main(String[] args) {
        String s =  "ABCDEFG";
        int numRows = 3;
        String res = convert(s, numRows);
        System.out.println(res);
    }
    /**
     * 使用一个数组存储每一行的字符串，在遍历过程中将每个字符拼接到数组对应行的字符串中
     */
    public static String convert(String s, int numRows) {
        if(numRows < 2) return s;

        List<StringBuffer> list = new ArrayList<>();
        for(int i = 0; i < numRows; i++){
            list.add(new StringBuffer());
        }
        int i = 0;
        int flag = -1;
        for(char c : s.toCharArray()){
            list.get(i).append(c);
            if(i == 0 || i == numRows - 1){ // 首行和最后行 需要改变方向
                flag = -flag;
            }
            i += flag; // i为当前行数
        }
        StringBuffer sb= new StringBuffer();
        for(StringBuffer sb1 : list){
            sb.append(sb1);
        }

        return sb.toString();
    }
}
