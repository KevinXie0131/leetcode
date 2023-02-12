package com.answer.string;

public class Q186_Reverse_Words_in_a_String_II {

    /**
     * Approach 1: Reverse the Whole String and Then Reverse Each Word
     */
    public void reverseWords(char[] s) {
        reverseString(s, 0 , s.length - 1);
        reverseEachWord(s);
    }

    private static void reverseString(char[] s, int start, int end){
        while(start < end){
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }

    private static void reverseEachWord(char[] s) {
        int left = 0;
        int right = 1;
        int n = s.length;
        while(left < n){
            while(right < n && s[right] != ' '){
                right++;
            }
            reverseString(s, left, right - 1);
            left = right + 1;
            right++;
        }
    }
}
