package com.answer.trie;

import java.util.*;

public class Q648_Replace_Words {
    /**
     * In English, we have a concept called root, which can be followed by some other word to form another
     * longer word - let's call this word derivative. For example, when the root "help" is followed by the word "ful",
     * we can form a derivative "helpful".
     * Given a dictionary consisting of many roots and a sentence consisting of words separated by spaces,
     * replace all the derivatives in the sentence with the root forming it. If a derivative can be replaced by more
     * than one root, replace it with the root that has the shortest length.
     * Return the sentence after the replacement.
     * 单词替换
     * 在英语中，我们有一个叫做 词根(root) 的概念，可以词根 后面 添加其他一些词组成另一个较长的单词——我们称这个词为
     * 衍生词 (derivative)。例如，词根 help，跟随着 继承词 "ful"，可以形成新的单词 "helpful"。
     * 现在，给定一个由许多 词根 组成的词典 dictionary 和一个用空格分隔单词形成的句子 sentence。你需要将句子中的所有
     * 衍生词 用 词根 替换掉。如果 衍生词 有许多可以形成它的 词根，则用 最短 的 词根 替换它。
     * 你需要输出替换之后的句子。
     * 示例 1：
     *   输入：dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
     *   输出："the cat was rat by the bat"
     * dictionary[i] consists of only lower-case letters.
     * sentence consists of only lower-case letters and spaces.
     * Every two consecutive words in sentence will be separated by exactly one space.
     * sentence does not have leading or trailing spaces.
     */
    public String replaceWords(List<String> dictionary, String sentence) {
        return null;
    }
}
