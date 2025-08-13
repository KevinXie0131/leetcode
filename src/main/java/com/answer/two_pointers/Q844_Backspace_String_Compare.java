package com.answer.two_pointers;

public class Q844_Backspace_String_Compare {
    /**
     * Given two strings s and t, return true if they are equal when both are typed into empty text editors. '#' means a backspace character.
     * Note that after backspacing an empty text, the text will continue empty.
     * 比较含退格的字符串
     * 给定 s 和 t 两个字符串，当它们分别被输入到空白的文本编辑器后，如果两者相等，返回 true 。# 代表退格字符。
     * 注意：如果对空文本输入退格字符，文本继续为空。
     * s and t only contain lowercase letters and '#' characters.
     * Follow up: Can you solve it in O(n) time and O(1) space?
     * s 和 t 只含有小写字母以及字符 '#'
     * 进阶：你可以用 O(n) 的时间复杂度和 O(1) 的空间复杂度解决该问题吗？
     */
    public static void main(String[] args) {
        /**
         * 示例 1：
         *  输入：s = "ab#c", t = "ad#c"
         *  输出：true
         *  解释：s 和 t 都会变成 "ac"。
         */
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
                if(slow > 0){
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
             //   builder.insert(slow, s.charAt(fast)); // works too
                slow++;
            } else {
                if(slow > 0){
                    slow--;
                }
            }
        }
        return builder.substring(0, slow);  //截取有效字符串
    }
    /**
     * Use StringBulder （使用栈的思路）重构字符串
     * 直接使用字符串string，来作为栈，末尾添加和弹出
     * 时间复杂度：O(N+M)
     * 空间复杂度：O(N+M)
     */
    String process1(String s){
        StringBuilder sb = new StringBuilder(); // 模拟栈

        for(int index = 0; index < s.length(); index++){
            if(s.charAt(index) != '#'){
                sb.append(s.charAt(index));  // 模拟入栈
            } else {
                if(sb.length() > 0){ // 栈非空才能弹栈
                    sb.deleteCharAt(sb.length() - 1);  // 模拟弹栈
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
                    sb1.deleteCharAt(sb1.length() - 1);  // 模拟弹栈
                }
            }
        }
        for(int i = 0; i < t.length(); i++){
            if(t.charAt(i) != '#'){
                sb2.append(t.charAt(i));  // 模拟入栈
            } else {
                if(sb2.length() > 0){ // 栈非空才能弹栈
                    sb2.deleteCharAt(sb2.length() - 1);  // 模拟弹栈
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
    /**
     * 双指针
     * 一个字符是否会被删掉，只取决于该字符后面的退格符，而与该字符前面的退格符无关。因此当我们逆序地遍历字符串，就可以立即确定当前字符是否会被删掉。
     * 时间复杂度：O(N+M)
     * 空间复杂度：O(1)
     */
    public boolean backspaceCompare5(String s, String t) {
        int i = s.length() - 1, j = t.length() - 1;
        int skip1 = 0, skip2 = 0;
        // 虽然有两个while循环嵌套，但实际上只遍历了一遍字符串s和t!!!
        while(i >= 0 || j >= 0){
            while(i >= 0){ // 先找到 s 中第一个需要比较的字符（即去除 # 影响后的第一个待比较字符）
                if(s.charAt(i) == '#'){
                    skip1++;
                    i--;
                }else if(skip1 > 0){
                    skip1--;
                    i--;
                }else {
                    break;
                }
            }
            // 再找到 t 中第一个需要比较的字符（即去除 # 影响后的第一个待比较字符）
            while(j >= 0){
                if(t.charAt(j) == '#'){
                    skip2++;
                    j--;
                }else if(skip2 > 0){
                    skip2--;
                    j--;
                }else {
                    break;
                }
            }
            // 然后开始比较,注意有下面这个 if 条件的原因是：如果 index = 0 位置上为 '#'，则 i, j 会为 -1
            // 而 index = -1 的情况应当处理。
            if(i >= 0 && j >= 0){
                if(s.charAt(i) != t.charAt(j)){ // 如果待比较字符不同，return false
                    return false;
                }
            }
            // (i >= 0 && j >= 0) 为 false 情况为
            // 1. i < 0 && j >= 0
            // 2. j < 0 && i >= 0
            // 3. i < 0 && j < 0
            // 其中，第 3 种情况为符合题意情况，因为这种情况下 s 和 t 都是 index = 0 的位置为 '#' 而这种情况下
            // 退格空字符即为空字符，也符合题意，应当返回 True。
            // 但是，情况 1 和 2 不符合题意，因为 s 和 t 其中一个是在 index >= 0 处找到了待比较字符，另一个没有找到
            // 这种情况显然不符合题意，应当返回 False，下式便处理这种情况。
            else if(i >= 0 || j >= 0){
                return false;
            }
          /*  else if (i >= 0 && j < 0) return false; // works too
            else if (i < 0 && j >= 0) return false;*/
            i--;
            j--;
        }
        return true;
    }

}

