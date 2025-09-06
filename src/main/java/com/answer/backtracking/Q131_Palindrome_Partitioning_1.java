package com.answer.backtracking;

import java.util.*;

public class Q131_Palindrome_Partitioning_1 {
    public static void main(String[] args) {
       // System.out.println(partition("abcd"));
        System.out.println(partition("aab"));
     /* 0 -> a
        1 -> a
        2 -> b
        1 -> ab
        0 -> aa
        2 -> b
        0 -> aab
        [[a, a, b], [aa, b]] */
    }
    /**
     * 切割问题可以抽象为组合问题
     * 如何模拟那些切割线
     * 切割问题中递归如何终止
     * 在递归循环中如何截取子串
     * 如何判断回文
     */
    static List<List<String>> result = new ArrayList<List<String>>();;
    static Deque<String> path = new ArrayDeque<>();

    static public List<List<String>> partition(String s) {
        backtracking(s, 0, new StringBuilder());
        return result;
    }

    static public void backtracking(String str, int startIndex, StringBuilder sb) {
        //因为是起始位置一个一个加的，所以结束时start一定等于s.length, 因为进入backtracking时一定末尾也是回文, 所以path是满足条件的
        if(startIndex == str.length()){ // if(startIndex >= str.length()){ // works too
            result.add(new ArrayList(path));  //注意创建一个新的copy
            return;
        }
        //像前两题一样从前往后搜索, 如果发现回文, 进入backtracking, 起始位置后移一位, 循环结束照例移除path的末位
        for (int i = startIndex; i < str.length(); i++){
            sb.append(str.charAt(i));
            System.out.println(startIndex + " -> " + sb);
            if (isPalindrome(sb.toString())){
                path.add(sb.toString());
                backtracking(str, i + 1, new StringBuilder());
                path.removeLast();
            }
        }
    }
    /**
     * 回溯法搜所有可行解的模板一般是这样的：
     * res = []
     * path = []
     *
     * def backtrack(未探索区域, res, path):
     *     if 未探索区域满足结束条件:
     *         res.add(path) # 深度拷贝
     *         return
     *     for 选择 in 未探索区域当前可能的选择:
     *         if 当前选择符合要求:
     *             path.add(当前选择)
     *             backtrack(新的未探索区域, res, path)
     *             path.pop()
     */
    /**
     * another form
     * 定义递归函数 ：
     *    定义一个递归函数 backtracking(string s, int startIndex)，其中 startIndex 表示当前子串的起始位置。
     *    每次进入递归时，将起始位置移动到当前位置的下一个位置（即 startIndex + 1），从而逐步探索从该位置出发的所有可能子串。
     * 递归终止条件 ：
     *    当 startIndex 达到字符串的长度 n 时，说明已经处理完所有可能的子串组合，递归终止。
     * 遍历子串的结束位置 ：
     *    在递归过程中，使用一个 for 循环来遍历从当前起始位置 startIndex 开始的所有可能的子串结束位置。
     *    对于每个子串，判断其是否满足回文条件。如果满足，则将其加入结果集；如果不满足，则跳过。
     */
    public List<List<String>> partition_1(String s) {
        backtracking_1(s, 0);
        return result;
    }

    public void backtracking_1(String str, int startIndex) {
        if(startIndex == str.length()){ // 分割完毕 // if(startIndex >= str.length()){ // works too
            result.add(new ArrayList(path)); // 复制 path
            return;
        }
        // 切出的子串满足回文，将它加入部分解 temp 数组，并继续往下切（递归）
        // 切出的子串不是回文，跳过该选择，不落入递归，继续下一轮迭代
        for (int i = startIndex; i < str.length(); i++){  // 枚举子串的结束位置
            if (isPalindrome(str.substring(startIndex, i + 1))){
                path.add(str.substring(startIndex, i + 1)); // 分割
                backtracking_1(str, i + 1);  // 考虑剩余的 s[i+1] ~ s[n-1] 怎么分割
                path.removeLast();  // 恢复现场
            }
        }
    }

    static public boolean isPalindrome(String str) {
        char[] ch = str.toCharArray();
        for(int i= 0, j = ch.length -1; i <= j; i++, j--){
            if(ch[i] != ch[j]){
                return false;
            }
        }
        return true;
    }
}
