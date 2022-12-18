package com.leetcode;

public class Test17_Character {

    public static void main(String[] args) {

        System.out.println(Character.isDigit('a'));
        System.out.println(Character.isDigit('1'));

        System.out.println(Character.isLetter('a'));
        System.out.println(Character.isLetter('1'));

        System.out.println("4=  " + Integer.toBinaryString(4));
        System.out.println("6=  " + Integer.toBinaryString(6));
        System.out.println("4&6=  " + Integer.toBinaryString(4&6));
        System.out.println("4|6=  " + Integer.toBinaryString(4|6));

        System.out.println("~4=  " + Integer.toBinaryString(~4));

        System.out.println("-1=  " + Integer.toBinaryString(-1));

        System.out.println("17 % 4=  " + 17 % 4);
        System.out.println("17 & 3=  " + (17 & 3));
    }
}
