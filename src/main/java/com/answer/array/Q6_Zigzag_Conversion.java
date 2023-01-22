package com.answer.array;

import java.util.*;

public class Q6_Zigzag_Conversion {
    public static void main(String[] args) {
        String s =  "AB";
        int numRows = 1;
        String res = convert(s, numRows);
        System.out.println(res);
    }

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
            if(i == 0 || i == numRows-1){
                flag = -flag;
            }
            i += flag;
        }
        StringBuffer sb= new StringBuffer();
        for(StringBuffer sb1 : list){
            sb.append(sb1);
        }

        return sb.toString();
    }
}
