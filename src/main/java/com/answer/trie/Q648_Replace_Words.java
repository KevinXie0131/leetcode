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
    /**
     * 字典树
     */
    public String replaceWords(List<String> dictionary, String sentence) {
        Trie2 trie2 = new Trie2();
        for(String str : dictionary){  // 构建字典树
            trie2.insert(str);
        }

        String[] words = sentence.split(" ");

        for(int i = 0; i < words.length; i++){  // 在字典树中查找每个单词是否有前缀存在
            String prefix = trie2.searchPrefix(words[i]);
            if(prefix != null){
                words[i] = prefix;
            }
        }
        return String.join(" ", words);
    }
    /**
     * another form
     */
    public String replaceWords_a(List<String> dictionary, String sentence) {
        Trie2 trie2 = new Trie2();
        for(String str : dictionary){  // 构建字典树
            trie2.insert(str);
        }
        StringBuffer ans = new StringBuffer();
        String[] words = sentence.split(" ");

        for(int i = 0; i < words.length; i++){  // 在字典树中查找每个单词是否有前缀存在
            String prefix = trie2.searchPrefix(words[i]);
            if(prefix != null){
                ans.append(prefix).append(" ");
            } else{
                ans.append(words[i]).append(" ");
            }
        }
        ans.deleteCharAt(ans.length() - 1);
        return ans.toString();
    }
    /**
     * 哈希集合
     * 首先将 dictionary 中所有词根放入哈希集合中，然后对于 sentence 中的每个单词，由短至长遍历它所有的前缀，
     * 如果这个前缀出现在哈希集合中，则我们找到了当前单词的最短词根，将这个词根替换原来的单词。最后返回重新拼接的句子。
     */
    public String replaceWords1(List<String> dictionary, String sentence) {
        Set<String> dictionarySet = new HashSet<>();
        for (String root : dictionary) {
            dictionarySet.add(root);
        }
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                if (dictionarySet.contains(word.substring(0, 1 + j))) {
                    words[i] = word.substring(0, 1 + j);
                    break;
                }
            }
        }
        return String.join(" ", words);
    }
    /**
     * 枚举: 直接枚举每个词根和单词进行匹配，然后将匹配的词根替换单词即可。
     */
    public String replaceWords3(List<String> dictionary, String sentence) {
        String[] s = sentence.split(" ");
        for (String root : dictionary) {
            for (int i = 0; i < s.length; i++) {
                if (s[i].startsWith(root)) {
                    s[i] = root;
                }
            }
        }
        return String.join(" ", s);
    }
    /**
     * 字典树
     * 与哈希集合不同，我们用 dictionary 中所有词根构建一棵字典树，并用特殊符号标记结尾。在搜索前缀时，
     * 只需在字典树上搜索出一条最短的前缀路径即可。
     */
    public String replaceWords2(List<String> dictionary, String sentence) {
        Trie3 root = new Trie3();
        for (String word : dictionary) {
            Trie3 cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
             //   cur.children.putIfAbsent(c, new Trie3()); // works too
             //   cur = cur.children.get(c);
                cur =  cur.children.computeIfAbsent(c, e -> new Trie3());
            }
            cur.children.put('#', new Trie3());
        }
        String[] words = sentence.split(" ");
        for (int i = 0; i < words.length; i++) {
            words[i] = findRoot(words[i], root);
        }
        return String.join(" ", words);
    }

    public String findRoot(String word, Trie3 root) {
        StringBuffer sb = new StringBuffer();
        Trie3 cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.children.containsKey('#')) {
                return sb.toString();
            }
            if (!cur.children.containsKey(c)) {
                return word;
            }
            sb.append(c);
            cur = cur.children.get(c);
        }
        return sb.toString();
    }
}

class Trie2 {
    private Trie2[] children; // 字典树的子节点
    private boolean isPrefix; // 是否是前缀

    public Trie2() {
        children = new Trie2[26]; //初始时每个都是26个小写字母
        isPrefix = false;
    }

    public void insert(String word) { // 插入字符串
        Trie2 node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                node.children[index] = new Trie2();
            }
            node = node.children[index]; // 指针下移
        }
        node.isPrefix = true;
    }

    public String searchPrefix(String word) { // 在字典树中查找是否包含前缀
        Trie2 node = this;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';
            if (node.children[index] == null) {
                return null;
            }
            node = node.children[index];
            if(node.isPrefix == true){ // 判断是否是前缀 是直接返回，一定是最短的
                return word.substring(0, i + 1);
            }
        }
        return null;
    }
}

class Trie3 {
    Map<Character, Trie3> children;

    public Trie3() {
        children = new HashMap<Character, Trie3>();
    }
}

