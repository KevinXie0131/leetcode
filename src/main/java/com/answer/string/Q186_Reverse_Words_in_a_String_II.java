package com.answer.string;

public class Q186_Reverse_Words_in_a_String_II {
    /**
     * reverse the order of words in a given character array, in-place. You must do this in-place in O(1) extra space.
     * 给定一个字符串，逐个翻转字符串中的每个单词。
     * 单词的定义是不包含空格的一系列字符
     * 输入字符串中不会包含前置或尾随的空格
     * 单词与单词之间永远是以单个空格隔开的
     * 进阶：使用 O(1) 额外空间复杂度的原地解法。
     */
    public static void main(String[] args) {
        char[] input =  {'t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e'};
        //  Output: ['b','l','u','e',' ','i','s',' ','s','k','y',' ','t','h','e']
        reverseWords1(input);
        System.out.println(input);
    }
    /**
     * Time: O(N), where N is the length of the array.
     * Space: O(1), in-place modification.
     */
    static public void reverseWords1(char[] s) {
        reverseString(s, 0 , s.length - 1); // Step 1: Reverse the entire array

        int start = 0;
        for(int end = 0; end <= s.length; end++){ // Step 2: Reverse each word in the reversed array
            if(end == s.length || s[end] == ' '){
                reverseString(s, start , end - 1);
                start = end + 1;
            }
        }
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
    /**
     * Approach 1: Reverse the Whole String and Then Reverse Each Word
     */
    static public void reverseWords(char[] s) {
        reverseString(s, 0 , s.length - 1);  // Step 1: Reverse the entire array
        reverseEachWord(s);                             // Step 2: Reverse each word in the reversed array
    }

    private static void reverseEachWord(char[] s) {
        int left = 0;
        int right = 0;
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
