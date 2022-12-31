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
        System.out.println(str);

        StringBuilder str1 = new StringBuilder("abcdefg");
        str1.setCharAt(2, 'C');
        System.out.println(str1);

        str1.insert(3, 'X');
        System.out.println(str1);

        str1.deleteCharAt(3);
        System.out.println(str1);

        str1.replace(4, 6, "E");
        System.out.println(str1);

        str1.append('G');
        str1.append("HI");
        System.out.println(str1);

        str1.reverse();
        System.out.println(str1);

        System.out.println(str1.length());
        System.out.println(str1.toString().toCharArray());

        System.out.println(str1.charAt(4));
        System.out.println(str1.toString().charAt(2));

        str1.deleteCharAt(8);
        System.out.println(str1);
    }
}
