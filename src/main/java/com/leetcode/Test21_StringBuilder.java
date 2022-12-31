package com.leetcode;

public class Test21_StringBuilder {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        sb.append("1");
        sb.append("2");

        System.out.println(sb);

        System.out.println(sb.reverse());

        StringBuilder str = new StringBuilder("saa");
        str.setLength(10);
        System.out.print(str);
        System.out.print(str);
    }
}
