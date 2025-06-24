package com.answer.hashmap;

public class Q249_Group_Shifted_Strings {
    /**
     * Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:
     *  "abc" -> "bcd" -> ... -> "xyz"
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
}
