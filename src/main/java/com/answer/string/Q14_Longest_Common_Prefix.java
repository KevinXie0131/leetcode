package com.answer.string;

public class Q14_Longest_Common_Prefix {
    /**
     * Approach 1: Horizontal scanning
     * 横向扫描
     */
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }
        // 依次遍历字符串数组，更新最长公共前缀
        int length = strs.length;
        String prefix = strs[0];
        for(int i = 1; i < length; i++){
            prefix = calPrefix(prefix, strs[i]);
            if(prefix.length() == 0){
                return prefix;
            }
        }
        return prefix;
    }

    public String calPrefix(String str1, String str2){
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        for(int i = 0; i < length; i++){
            if(str1.charAt(i) != str2.charAt(i)){
                break;
            }
            index++;
        }
        return str1.substring(0, index);
    }
    /**
     * Approach 2: Vertical scanning
     * 纵向扫描
     * 纵向扫描，以字符串数组中的第一个字符串为基准，从前往后遍历所有字符串的每一列，比较相同列上的字符是否相同，
     * 如果相同则继续对下一列进行比较，如果不相同则当前列不再属于公共前缀，当前列之前的部分为最长公共前缀。
     */
    public String longestCommonPrefix_1(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }
        // 纵向扫描：遍历第一个字符串的字符，并与其余字符串相应位置的字符比较
        for(int i = 0; i < strs[0].length(); i++){
            char c = strs[0].charAt(i);
            for(int j = 0; j < strs.length; j++){
                if(i == strs[j].length() || strs[j].charAt(i) != c){
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
    /**
     * Approach 3: Divide and conquer
     * 二分查找
     */
    public String longestCommonPrefix_2(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }
        // 分治
        return calPrefix(strs, 0, strs.length - 1);
    }

    public String calPrefix(String[] strs, int start, int end){
        if(start == end){
            return strs[start];
        }
        int mid = start + (end - start) / 2;
        String left = calPrefix(strs, start, mid);
        String right = calPrefix(strs, mid+1, end);
        return doCal(left, right);
    }

    public String doCal(String str1, String str2){
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        for(int i = 0; i < length; i++){
            if(str1.charAt(i) != str2.charAt(i)){
                break;
            }
            index++;
        }
        return str1.substring(0, index);
    }
    /**
     * Approach 4: Binary search
     * 二分查找
     */
    public String longestCommonPrefix_3(String[] strs) {
        if(strs == null || strs.length == 0){
            return "";
        }
        // 二分查找
        // 最短字符串的字符数 minLength
        int minLength = Integer.MAX_VALUE;
        for(int i = 0; i < strs.length; i++){
            minLength = Math.min(minLength, strs[i].length());
        }
        // 在 0 - minLength 区间内进行二分查找
        int start = 0, end = minLength;
        while(start < end){
            int mid = (end - start + 1) / 2 + start;
            if(isPrefix(strs, mid)){
                start = mid;
            }else{
                end = mid - 1;
            }
        }
        return strs[0].substring(0, start);
    }

    public boolean isPrefix(String[] strs, int length){
        String str0 = strs[0].substring(0, length);
        for(int i = 0; i < strs.length; i++){
            String str = strs[i];
            for(int j = 0; j < length; j++){
                if(str0.charAt(j) != str.charAt(j)){
                    return false;
                }
            }
        }
        return true;
    }
}
