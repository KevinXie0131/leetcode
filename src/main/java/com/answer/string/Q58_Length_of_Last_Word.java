package com.answer.string;

public class Q58_Length_of_Last_Word {
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
     * Official answer
     */
    public int lengthOfLastWord_1(String s) {
        // trim the trailing spaces
        int p = s.length() - 1;
        while (p >= 0 && s.charAt(p) == ' ') {
            p--;
        }

        // compute the length of last word
        int length = 0;
        while (p >= 0 && s.charAt(p) != ' ') {
            p--;
            length++;
        }
        return length;
    }
}
