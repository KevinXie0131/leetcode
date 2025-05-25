package com.answer.string;

public class Q58_Length_of_Last_Word {
    /**
     * Given a string s consisting of words and spaces, return the length of the last word in the string.
     * A word is a maximal substring consisting of non-space characters only.
     * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。返回字符串中 最后一个 单词的长度。
     * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
     */
    /**
     * 双指针
     * 从后向前遍历字符串，找到第一个非空字符的位置，之后继续向前遍历，直到遇到空格位置，
     * 记录两个位置的间隔，即为最后一个单词的长度。
     */
    public int lengthOfLastWord_2(String s) {
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ' ') continue;
            int j = i - 1;
            while (j >= 0 && s.charAt(j) != ' ') j--;
            return i - j;
        }
        return 0;
    }
    /**
     * Approach 1: String Index Manipulation
     */
    public int lengthOfLastWord(String s) {
        char[] ch = s.toCharArray();
        int len = ch.length;
        int right = len - 1;

        while(right >= 0){
            if(ch[right] == ' '){
                right--;
            }else{
                break; // 遇到第一个非空格字符
            }
        }

        int left = right - 1;
        while(left >= 0){
            if(ch[left] == ' '){ // 遇到空格
                break;
            }else{
                left--;
            }
        }

        return right - left;
    }
    /**
     * Official answer 反向遍历
     * 从最后一个字母开始继续反向遍历字符串，直到遇到空格或者到达字符串的起始位置。遍历到的每个字母都是最后一个单词中的字母，
     * 因此遍历到的字母数量即为最后一个单词的长度。
     */
    public int lengthOfLastWord_1(String s) {
        // trim the trailing spaces
        int index  = s.length() - 1;
        while (index  >= 0 && s.charAt(index ) == ' ') {
            index --;
        }
        // compute the length of last word
        int length = 0;
        while (index  >= 0 && s.charAt(index ) != ' ') {
            index --;
            length++;
        }
        return length;
    }
}
