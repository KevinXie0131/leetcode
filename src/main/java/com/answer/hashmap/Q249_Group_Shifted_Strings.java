package com.answer.hashmap;

import java.util.*;

public class Q249_Group_Shifted_Strings {
    /**
     * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd".
     * We can keep "shifting" which forms the sequence: "abc" -> "bcd" -> ... -> "xyz"
     * Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
     *
     * Example:
     * Input: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
     * Output:
     * [
     *   ["abc","bcd","xyz"],
     *   ["az","ba"],
     *   ["acef"],
     *   ["a","z"]
     * ]
     * 移位字符串分组
     * 对字符串进行 “移位” 的操作：
     *  右移：将字符串中每个字母都变为其在字母表中 后续 的字母，其中用 'a' 替换 'z'。比如，"abc" 能够右移为 "bcd"，"xyz" 能够右移为 "yza"。
     *  左移：将字符串中每个字母都变为其在字母表中 之前 的字母，其中用 'z' 替换 'a'。比如，"bcd" 能够左移为 "abc"，"yza" 能够左移为 "xyz"。
     * 我们可以不断地向两个方向移动字符串，形成 无限的移位序列。
     *  例如，移动 "abc" 来形成序列：... <-> "abc" <-> "bcd" <-> ... <-> "xyz" <-> "yza" <-> ... <-> "zab" <-> "abc" <-> ...
     * 给定一个字符串数组 strings，将属于相同移位序列的所有 strings[i] 进行分组。你可以以 任意顺序 返回答案。
     */
    public static void main(String[] args) {
        Q249_Group_Shifted_Strings sol = new Q249_Group_Shifted_Strings();
        String[] input = {"abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"};
        List<List<String>> result = sol.groupStrings2(input);
        for (List<String> group : result) {
            System.out.println(group);
        }
    }
    /**
     * getKey 方法为每个字符串生成一个移位特征（每两个字符的差值），这样具有相同移位关系的字符串会归为一组。
     * 使用哈希表分组，遍历输入字符串并生成 key。
     */
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strings) {
            String key = getKey(s);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(map.values());
    }

    // 生成字符串的移位特征 key
    private String getKey(String s) {
        StringBuilder key = new StringBuilder();
        for (int i = 1; i < s.length(); i++) {
            int diff = (s.charAt(i) - s.charAt(i - 1) + 26) % 26; // 保证是正数
            key.append(diff).append(",");
        }
        // 长度为1的字符串 key 为空
        return key.toString();
    }
    /**
     * 哈希表
     */
    public List<List<String>> groupStrings1(String[] strings) {
        Map<String, List<String>> g = new HashMap<>(); // 哈希表 g 来存储每个字符串移位后且首位为 'a' 的字符串
        for (String s : strings) {
            char[] t = s.toCharArray();
            int diff = t[0] - 'a';
            for (int i = 0; i < t.length; ++i) {
                t[i] = (char) (t[i] - diff);
                if (t[i] < 'a') {
                    t[i] += 26; // 计算其移位后的字符串t
                }
            }
            g.computeIfAbsent(new String(t), k -> new ArrayList<>()).add(s);
        }
        return new ArrayList<>(g.values());
    }
    /**
     * 哈希表
     */
    public List<List<String>> groupStrings2(String[] strings){
        Map<String, List<String>> map = new HashMap<>();
        for(String str : strings){
            String hash = getHash(str);
            List<String> group = map.get(hash);
            if(group == null){
                group = new ArrayList<>();
                map.put(hash, group);
            }
            group.add(str);
        }
        return new ArrayList<>(map.values());
    }
    //将字符串str移位直到第一个字符变成'a'，返回移位后的字符串
    //返回的字符串可以作为原始字符串的哈希值，通过该哈希值就可以对字符串进行归类
    public static String getHash(String str){
        if(str.isEmpty())
            return str;

        char[] arr = str.toCharArray();
        int tem = arr[0] - 26;
        for(int i = 0; i < arr.length; i++)
            arr[i] = (char)((arr[i] - tem) % 26 + 'a');

        return new String(arr).intern();
    }
}
