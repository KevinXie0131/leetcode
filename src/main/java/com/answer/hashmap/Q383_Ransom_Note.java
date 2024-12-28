package com.answer.hashmap;

public class Q383_Ransom_Note {

    public boolean canConstruct(String ransomNote, String magazine) {
        // shortcut
        if (ransomNote.length() > magazine.length()) {
            return false;
        }
        // 定义一个哈希映射数组
        int[] result = new int[26];
        char[] m = magazine.toCharArray();
        char[] n = ransomNote.toCharArray();
        // 遍历
        for(char s: m){
            result[s -'a']++;
        }
        for(char s: n){
            result[s -'a']--;
            if( result[s -'a'] < 0){ // 如果数组中存在负数，说明ransomNote字符串中存在magazine中没有的字符
                return false;
            }
        }

        return true;
    }
}
