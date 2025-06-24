package com.answer.hashmap;

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
}
