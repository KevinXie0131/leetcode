package com.answer.string;

public class Q58_Length_of_Last_Word {

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
