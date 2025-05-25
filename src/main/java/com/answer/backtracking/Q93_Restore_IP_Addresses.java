package com.answer.backtracking;

import java.util.*;

public class Q93_Restore_IP_Addresses {
    /**
     * A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255
     * (inclusive) and cannot have leading zeros.
     * 复原 IP 地址: 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
     * 例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
     * 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入 '.' 来形成。你 不能 重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
     */
    public static void main(String[] args) {
       String s = "25525511135"; // 输出：["255.255.11.135","255.255.111.35"]
       System.out.println(restoreIpAddresses(s));
    }
    /**
     * 切割问题就可以使用回溯搜索法把所有可能性搜出来
     * 时间复杂度: O(3^4)，IP地址最多包含4个数字，每个数字最多有3种可能的分割方式，则搜索树的最大深度为4，每个节点最多有3个子节点。
     * 空间复杂度: O(n)
     */
   static List<String> result = new ArrayList<String>(); // 记录结果
    // startIndex: 搜索的起始位置，pointNum:添加逗点的数量
    static public List<String> restoreIpAddresses(String s) {
        if(s.length() > 12 || s.length() < 4){
            return result; // 算是剪枝了
        }
        backtracking(s,0,0);
        return result;
    }
    // startIndex: 搜索的起始位置， pointNum:添加逗点的数量
    static public void backtracking(String s, int startIndex, int pointNum){
        // 逗点数量为3时，分隔结束
        // 判断第四段⼦字符串是否合法，如果合法就放进result中
        if (pointNum == 3) {
            if (isValid(s, startIndex, s.length() - 1)) {
                result.add(s);
                return;
            }
        }
        if (s.length() - startIndex > 3 * (4 - pointNum)) { // 算是剪枝
            return;
        }
        for(int i = startIndex; i < s.length(); i++){
            if(isValid(s, startIndex, i)){ // 判断 [startIndex,i] 这个区间的⼦串是否合法
                s= s.substring(0, i + 1) + "." + s.substring(i + 1); // 在i的后⾯插⼊⼀个逗点
                pointNum++;
                backtracking(s,i + 2, pointNum); // 插⼊逗点之后下⼀个⼦串的起始位置为i+2
                pointNum--; // 回溯
                s = s.substring(0, i + 1) + s.substring(i + 2); // 回溯删掉逗点
            } else {
                break; // 不合法，直接结束本层循环
            }
        }
    }
    // 判断字符串s在左闭⼜闭区间[start, end]所组成的数字是否合法
     private static boolean isValid(String s, int start, int end) {
        if (start > end) {
            return false;
        }
        if (s.charAt(start) == '0' && start != end) { // 0开头的数字不合法
            return false;  // 大于 1 位的时候，不能以 0 开头
        }
        int num = 0;
        for (int i = start; i <= end; i++) {
            if (s.charAt(i) > '9' || s.charAt(i) < '0') { // 遇到⾮数字字符不合法
                return false;
            }
            num = num * 10 + (s.charAt(i) - '0');
            if (num > 255) { // 如果⼤于255了不合法
                return false;
            }
        }
        return true;
    }
    /**
     * From 睡不醒的鲤鱼 不太容易理解
     * 将 IP 地址拆分成四个数字，枚举每个数字能截取的字符串的位置，当四个数字都确定，并且枚举到了字符串的最后一位，
     * 说明是一个合法方案，将其加入结果。
     */
    static List<String> ans = new ArrayList<String>();
    static LinkedList<Integer> cur = new LinkedList<>();

    static public List<String> restoreIpAddresses_3(String s) {
        dfs(s, 0, 0);
        return ans;
    }
    static void dfs(String s, int idx, int start) {
        if (idx == 4 && start == s.length()) {
            String ip = String.valueOf(cur.get(0));
            for (int i = 1; i < cur.size(); i++) {
                ip += "." + String.valueOf(cur.get(i));
            }
            ans.add(ip);
            return;
        }
        for (int i = start, num = 0; i < s.length(); i++) {
            num = num * 10 + s.charAt(i) - '0';
            if (num > 255) break;
            cur.add(num);
            dfs(s, idx + 1, i + 1);
            cur.removeLast();
            if (num == 0) break; // "0000" -> ["0.0.0.0"]
        }
    }
    /**
     * 方法一：但使用stringBuilder，故优化时间、空间复杂度，因为向字符串插入字符时无需复制整个字符串，从而减少了操作的时间复杂度，也不用开新空间存subString，从而减少了空间复杂度。
     */
    List<String> result1 = new ArrayList<String>();
    public List<String> restoreIpAddresses1(String s) {
        if (s.length() < 4 || s.length() > 12) return result1;
        backtracking1(new StringBuilder(s), 0, 0);
        return result1;
    }
    void backtracking1(StringBuilder s, int startIndex, int pointNum) {
        if (pointNum == 3) {
            if (isValid(s.toString(), startIndex, s.length() - 1)) {
                result1.add(s.toString());
            }
            return;
        }
        for (int i = startIndex; i < s.length(); i++) {
            if (isValid(s.toString(), startIndex, i)) {
                s.insert(i + 1,  ".");
                pointNum++;
                backtracking1(s, i + 2, pointNum); // backtracking1(s, i + 2, pointNum + 1); // 隐藏回溯
                pointNum--;
                s.deleteCharAt(i + 1) ;
            } else {
                break;
            }
        }
    }
    /**
     *  方法二：比上面的方法时间复杂度低，更好地剪枝，优化时间复杂度
     */
    List<String> result2 = new ArrayList<String>();
    StringBuilder stringBuilder = new StringBuilder();

    public List<String> restoreIpAddresses2(String s) {
        restoreIpAddressesHandler(s, 0, 0);
        return result2;
    }
    // number表示stringbuilder中ip段的数量
    public void restoreIpAddressesHandler(String s, int start, int number) {
        // 如果start等于s的长度并且ip段的数量是4，则加入结果集，并返回
        if (start == s.length() && number == 4) {
            result2.add(stringBuilder.toString());
            return;
        }
        // 如果start等于s的长度但是ip段的数量不为4，或者ip段的数量为4但是start小于s的长度，则直接返回
        if (start == s.length() || number == 4) {
            return;
        }
        // 剪枝：ip段的长度最大是3，并且ip段处于[0,255]
        for (int i = start; i < s.length() && i - start < 3 && Integer.parseInt(s.substring(start, i + 1)) >= 0
                && Integer.parseInt(s.substring(start, i + 1)) <= 255; i++) {
            if (i + 1 - start > 1 && s.charAt(start) - '0' == 0) {
                break;
            }
            stringBuilder.append(s.substring(start, i + 1));
            // 当stringBuilder里的网段数量小于3时，才会加点；如果等于3，说明已经有3段了，最后一段不需要再加点
            if (number < 3) {
                stringBuilder.append(".");
            }
            number++;
            restoreIpAddressesHandler(s, i + 1, number);
            number--;
            // 删除当前stringBuilder最后一个网段，注意考虑点的数量的问题
            stringBuilder.delete(start + number, i + number + 2);
        }
    }
}
