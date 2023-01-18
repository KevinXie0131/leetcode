package com.answer.string;

public class Q344_Reverse_String {

    /**
     * Approach 1: Recursion, In-Place, O(N) Space
     */
    public void reverseString(char[] s) {
        int right = s.length - 1;
        int left = 0;

        recursion(s, left, right);
    }

    public void recursion(char[] s, int left, int right){
        if(left >= right)return;

        char temp = s[left];
        s[left] = s[right];
        s[right] = temp;

        recursion(s, left+1, right-1);
    }

    /**
     * Approach 2: Two Pointers, Iteration, O(1) Space
     */
    public void reverseString_1(char[] s) {
        int right = s.length - 1;
        int left = 0;
        while(left < right){
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left++;
            right--;
        }
    }
}
