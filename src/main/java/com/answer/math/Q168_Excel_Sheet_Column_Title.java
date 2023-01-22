package com.answer.math;

public class Q168_Excel_Sheet_Column_Title {

    public String convertToTitle(int columnNumber) {
        StringBuffer sb = new StringBuffer();
        while(columnNumber > 0){
            columnNumber--;
            sb.append((char)(columnNumber%26 + 'A'));
            columnNumber /= 26;
        }

        return sb.reverse().toString();

    }}
