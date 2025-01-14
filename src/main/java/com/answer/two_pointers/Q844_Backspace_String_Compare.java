package com.answer.two_pointers;

public class Q844_Backspace_String_Compare {
    public static void main(String[] args) {
        String s = "ab#c";
        String t = "ad#c";
        backspaceCompare_4(s, t);
    }
    /**
     * 采用快慢指针，重构两个字符串，再比较重构后的两个字符串是否相等即可。
     * 注：唯一需要注意的地方在于字符串第一位为符号#时慢指针不回退，不然会造成数组访问越界的问题。
     * 空间复杂度:O(1)
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
     *  另一种形式 使用快慢双指针去除字符串中的#
     */
    String process_0(String s){
        int slow = 0;
        StringBuilder builder = new StringBuilder(s); //StringBuilder用于修改字符串
        for(int fast = 0; fast < s.length(); fast++){
            if(s.charAt(fast) != '#'){
                builder.setCharAt(slow, s.charAt(fast));
                slow++;
            } else {
                if(slow> 0){
                    slow--;
                }
            }
        }
        return builder.toString().substring(0, slow);  //截取有效字符串
    }
    /**
     * Use StringBulder （使用栈的思路）
     * 直接使用字符串string，来作为栈，末尾添加和弹出
     */
    String process1(String s){
        StringBuilder sb = new StringBuilder(); // 模拟栈

        for(int fast = 0; fast < s.length(); fast++){
            if(s.charAt(fast) != '#'){
                sb.append(s.charAt(fast));  // 模拟入栈
            } else {
                if(sb.length() > 0){ // 栈非空才能弹栈
                    sb.deleteCharAt(sb.length()-1);  // 模拟弹栈
                }
            }
        }
        return sb.toString();
    }
    /**
     * 另一种形式 普通方法（使用栈的思路）
     */
    public boolean backspaceCompare3(String s, String t) {
        StringBuilder sb1 = new StringBuilder(); // 模拟栈
        StringBuilder sb2 = new StringBuilder();// 模拟栈
        // 分别处理两个 String
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != '#'){
                sb1.append(s.charAt(i));  // 模拟入栈
            } else {
                if(sb1.length() > 0){ // 栈非空才能弹栈
                    sb1.deleteCharAt(sb1.length()-1);  // 模拟弹栈
                }
            }
        }
        for(int i = 0; i < t.length(); i++){
            if(t.charAt(i) != '#'){
                sb2.append(t.charAt(i));  // 模拟入栈
            } else {
                if(sb2.length() > 0){ // 栈非空才能弹栈
                    sb2.deleteCharAt(sb2.length()-1);  // 模拟弹栈
                }
            }
        }
        return sb1.toString().equals(sb2.toString());
    }
    /**
     * 从后往前双指针 (优化方法: 有使用 O(1) 的空间复杂度)
     * 同时从后向前遍历S和T（i初始为S末尾，j初始为T末尾），记录#的数量，模拟消除的操作，如果#用完了，就开始比较S[i]和S[j]。
     * 如果S[i]和S[j]不相同返回false，如果有一个指针（i或者j）先走到的字符串头部位置，也返回false。
     */
    static boolean backspaceCompare_4(String s, String t) {
        int sSkipNum = 0; //记录s的#的个数
        int tSkipNum = 0; //记录t的#的个数
        int sIndex = s.length() - 1;
        int tIndex = t.length() - 1;
        while(true) {
            // 从后向前，消除S的#
            while(sIndex >= 0) { //每次记录连续的#并跳过被删除的字符
                if(s.charAt(sIndex) == '#') {
                    sSkipNum++;
                } else {
                    if(sSkipNum > 0) {
                        sSkipNum--;
                    } else {
                        break;
                    }
                }
                sIndex--;
            }
            // 从后向前，消除T的#
            while(tIndex >= 0) { //每次记录连续的#并跳过被删除的字符
                if(t.charAt(tIndex) == '#') {
                    tSkipNum++;
                } else {
                    if(tSkipNum > 0) {
                        tSkipNum--;
                    } else {
                        break;
                    }
                }
                tIndex--;
            }
            // 后半部分#消除完了，接下来比较S[i] != T[j]
            if(sIndex < 0 || tIndex < 0) { //s 或者 t遍历完了
                break;
            }
            if(s.charAt(sIndex) != t.charAt(tIndex)) { //当前下标的字符不相等
                return false;
            }
            sIndex--;
            tIndex--;
        }
        // 说明S和T同时遍历完毕
        if(sIndex == -1 && tIndex == -1) { //同时遍历完 则相等
            return true;
        }
        return false;
    }
}

