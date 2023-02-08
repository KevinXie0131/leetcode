package com.answer.string;

public class Q387_First_Unique_Character_in_a_String {

    /**
     * Approach 1: Linear time solution
     */
    public int firstUniqChar(String s) {
        char[] ch = s.toCharArray();
        int[] counter = new int[26];
        for(char c : ch){
            counter[c - 'a']++;
        }
        for(int i = 0; i < ch.length; i++){
            if(counter[ch[i] - 'a'] == 1){
                return i;
            }
        }
        return - 1;
    }
}
