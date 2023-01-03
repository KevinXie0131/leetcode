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

        System.out.println(Math.abs(1 - 2));
        System.out.println(Math.abs(3 - 2));

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

        System.out.println("223 % 10 -> " + 223 % 10);

        int a1 = 10;
        int b1 = -a1;
        System.out.println("b1 -> " + b1);
      //  int  c1 = 2147483648;  // java: integer number too large: 2147483648

        int high = 2100000000;
        int low = 2000000000;
        System.out.println("mid using >>> 1 = " + ((low + high) >>> 1));
        System.out.println("mid using / 2   = " + ((low + high) / 2));
        System.out.println("mid using / 2   = " + (low + (high - low) / 2));

        int val = 0b01100;
        int shifted = val << 2;
        System.out.println(Integer.toBinaryString(shifted));
        System.out.println(Integer.numberOfLeadingZeros(33));
        System.out.println(Integer.toBinaryString(33));

        System.out.println((13 & 1) == 0); // isEven
        System.out.println((12 & 1) == 0);

        int n1 = 16;
        int n2 = 17;
        System.out.println((n1 & (n1 - 1)) == 0); //isPowerOfTwo;
        System.out.println((n2 & (n2 - 1)) == 0);
    }
}
