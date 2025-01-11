package com.answer.two_pointers;

public class Q844_Backspace_String_Compare {
    public static void main(String[] args) {
        String s = "ab#c";
        String t = "ad#c";
        backspaceCompare(s, t);
    }
    /**
     * 采用快慢指针，重构两个字符串，再比较重构后的两个字符串是否相等即可。
     * 注：唯一需要注意的地方在于字符串第一位为符号#时慢指针不回退，不然会造成数组访问越界的问题。
     */
    static boolean backspaceCompare(String s, String t) {
        return process(s).equals(process(t));
    }

    static String process(String s){
        char[] ch = s.toCharArray();
        int slow = 0;
        for(int fast = 0; fast < ch.length; fast++){
            if(ch[fast] != '#'){
                ch[slow] = ch[fast];
                slow++;
            } else {
                if(slow> 0){
                    slow--;
                }
            }
        }
        return new String(ch).substring(0, slow);
    }
    /**
     * Use StringBulder
     */
    String process1(String s){
        StringBuilder sb = new StringBuilder();

        for(int fast = 0; fast < s.length(); fast++){
            if(s.charAt(fast) != '#'){
                sb.append(s.charAt(fast));
            } else {
                if(sb.length() > 0){
                    sb.deleteCharAt(sb.length()-1);
                }
            }
        }
        return sb.toString();
    }
}

