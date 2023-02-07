package com.answer.array;

public class Q392_Is_Subsequence {
    public static void main(String[] args) {
        String s = "axc";
        String t = "ahbgdc";
        System.out.println(isSubsequence(s, t));
    }

    /**
     * Approach 2: Two-Pointers
     */
    public static boolean isSubsequence(String s, String t) {
        char[] source = s.toCharArray();
        char[] target = t.toCharArray();
        int i = 0 ;
        int j = 0 ;
        while(i< source.length && j < target.length){
            if( source[i] == target[j]) {
                i++;

                if(i == source.length) return true;
            }
            j++;
        }

        return i == source.length;
    }
}
