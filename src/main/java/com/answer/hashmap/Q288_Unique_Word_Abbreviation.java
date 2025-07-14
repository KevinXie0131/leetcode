package com.answer.hashmap;

import java.util.*;

public class Q288_Unique_Word_Abbreviation {
    /**
     * https://leetcode.ca/all/288.html
     * 独特的单词缩写
     * 设计一个数据结构，使它能够接收一个单词列表进行初始化，并支持判断某个单词的缩写是否为唯一的。
     *
     * 单词的缩写规则如下：
     *  若单词长度小于等于 2，则缩写为该单词本身；
     *  若单词长度大于 2，则缩写形式为：第一个字母 + (中间字母数) + 最后一个字母。例如：
     *      "dog" 缩写为 "d1g"
     *      "internationalization" 缩写为 "i18n"
     *      "it" 的缩写就是 "it"
     * 实现 WordAbbreviation 类：
     *  WordAbbreviation(String[] dictionary)
     *      使用单词列表 dictionary 初始化对象。
     *  boolean isUnique(String word)
     *      如果该单词的缩写在字典中是唯一的（即没有其他不同的单词拥有相同的缩写），则返回 true，否则返回 false。
     * 示例：
     * 输入：
     * ["WordAbbreviation", "isUnique", "isUnique", "isUnique", "isUnique"]
     * [[["deer","door","cake","card"]], ["dear"], ["cart"], ["cane"], ["make"]]
     * 输出：
     * [null, false, true, false, true]
     * 解释： WordAbbreviation wordAbbr = new WordAbbreviation(["deer","door","cake","card"]);
     * wordAbbr.isUnique("dear"); // 返回 false
     * wordAbbr.isUnique("cart"); // 返回 true
     * wordAbbr.isUnique("cane"); // 返回 false
     * wordAbbr.isUnique("make"); // 返回 true
     */
    public static void main(String[] args) {
        String[] dictionary = {"deer", "door", "cake", "card"};
        Q288_Unique_Word_Abbreviation vwa = new Q288_Unique_Word_Abbreviation(dictionary);

        System.out.println(vwa.isUnique("dear")); // false
        System.out.println(vwa.isUnique("cart")); // true
        System.out.println(vwa.isUnique("cane")); // false
        System.out.println(vwa.isUnique("make")); // true
    }

    private final Map<String, Set<String>> abbrDict = new HashMap<>();

    // Constructor to build the abbreviation dictionary
    public Q288_Unique_Word_Abbreviation(String[] dictionary) {
        for (String word : dictionary) {
            String abbr = getAbbr(word);
            abbrDict.computeIfAbsent(abbr, k -> new HashSet<>()).add(word);
        }
    }

    // Get abbreviation for a word
    private String getAbbr(String word) { //  方法返回单词缩写
        if (word.length() <= 2) {
            return word;
        }
        return "" + word.charAt(0) + (word.length() - 2) + word.charAt(word.length() - 1);
    }

    // Check if word's abbreviation is unique in the dictionary
    public boolean isUnique(String word) { // 判断单词的缩写是否在字典中唯一
        String abbr = getAbbr(word);
        Set<String> set = abbrDict.get(abbr);
        if (set == null) {
            return true;
        }
        // If only this word exists for this abbreviation, it's unique
        return set.size() == 1 && set.contains(word);
    }
    /**
     * 一个单词的缩写可以表示成第一个字母+中间字母个数+最后一个字母。给一个单词字典和一个单词，判断这个单词的缩写是唯一的，
     * 即字典的单词缩写中没有这个缩写,或者有这个缩写,但和这个单词是一样的（注意这种情况的处理）。
     *
     * 解法：定义一个函数用来操作缩写单词，对于字典中的所有单词进行缩写并存入另一个哈希表(key为缩写后的单词，value为set)。
     * 再对单词进行缩写，然后判断单词的缩写是否在哈希表中出现，如果没出现那肯定是唯一的。如果出现了还要看set里存的是不是只是这个单词，
     * 如果有其它单词出现就不是唯一的。
     */
    Map<String, Set<String>> map;
    public void ValidWordAbbr1(String[] dictionary) {
        map = new HashMap<>();
        for (String s : dictionary) {
            String abbr = getAbbr1(s);
            if (!map.containsKey(abbr)) {
                map.put(abbr, new HashSet<String>());
            }
            map.get(abbr).add(s);
        }
    }

    public boolean isUnique1(String word) {
        String abbr = getAbbr(word);
        if (!map.containsKey(abbr) || (map.get(abbr).contains(word) && map.get(abbr).size() == 1)) {
            return true;
        }
        return false;
    }

    private String getAbbr1(String s) {
        if (s.length() < 3) {
            return s;
        }
        int len = s.length();
        return s.substring(0, 1) + (len - 2) + s.substring(len - 1);
    }

}
