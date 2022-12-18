package com.leetcode;

public class Test15_Math {

    public static void main(String[] args) {

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);

        System.out.println(Math.pow(2, 5));

        System.out.println(Math.max(2, 3));
        System.out.println(Math.min(2, 3));

        System.out.println(Math.ceil(4.3));
        System.out.println(Math.floor(5.4));

        System.out.println(Math.round(7.6));
        System.out.println(Math.round(7.3));

        int a = 8;
        int b = 12;
        System.out.println("a & b -> " + (a & b));
        System.out.println("a | b -> " + (a | b));
        System.out.println("~a -> " + (~a));
        System.out.println("a >> 1 -> " + (a >> 1));
        System.out.println("a % 3 -> " + (a % 3));

        Integer x = 11;
        Integer y = 12;
        System.out.println("x == y -> " + (x == y));
        System.out.println("x == y -> " + (x.equals(y)));

        System.out.println("'a'^'b' -> " + ('a' ^ 'b'));
        System.out.println("'a'^'b'^'a' -> " + ('a' ^ 'b' ^ 'a'));
        System.out.println("'a'^'b'^'a'^'b' -> " + ('a' ^ 'b' ^ 'a' ^ 'b'));
    }
}