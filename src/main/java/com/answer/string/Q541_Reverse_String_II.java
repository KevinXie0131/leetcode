package com.answer.string;

import java.util.Arrays;

public class Q541_Reverse_String_II {

    public static void main(String[] args) {
        String s = "abcdefg";
        int k = 2;

        System.out.println(reverseStr(s, k));
    }

    public static String reverseStr(String s, int k) {
        char[] array = s.toCharArray();
        for(int i = 0; i< array.length; i += 2*k){
            int r = i + k - 1;
            reverse(array, i,  Math.min(array.length- 1, r));
        }
        return new String(array);
    }

    public static void reverse(char arr[], int left, int right){
        while(left < right){
            char temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }

    }

    /**
     *
     */
    public String reverseStr_1(String s, int k) {
        StringBuffer result = new StringBuffer();
        int start = 0;
        int len = s.length();

        while(start < len){
            StringBuffer temp = new StringBuffer();
            int first = (start + k > len)?len:start+k;
            int second = (start + 2*k > len)?len:start+2*k;
            temp.append(s.substring(start, first));
            result.append(temp.reverse());

            result.append(s.substring(first, second));

            start += 2*k;
        }

        return result.toString();
    }
}
