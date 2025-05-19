package com.answer.backtracking;

import java.util.*;

public class Q140_Word_Break_II {
    /**
     * Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence
     * where each word is a valid dictionary word. Return all such possible sentences in any order.
     * Note that the same word in the dictionary may be reused multiple times in the segmentation.
     * 给定一个字符串 s 和一个字符串字典 wordDict ，在字符串 s 中增加空格来构建一个句子，使得句子中所有的单词都在词典中。
     * 以任意顺序 返回所有这些可能的句子。
     * 注意：词典中的同一个单词可能在分段中被重复使用多次。
     */
    /**
     * 类似Q131 Palindrome Partitioning
     * 这道题是「139. 单词拆分」的进阶，第 139 题要求判断是否可以拆分，这道题要求返回所有可能的拆分结果。
     */
    public static void main(String[] args) {
        String s = "catsanddog";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("and");
        wordDict.add("sand");
        wordDict.add("dog");
        List<String> res = wordBreak_1(s, wordDict);
        System.out.println(res);

        String s1 = "aaaaaaa";
        List<String> wordDict1 = new ArrayList<>();
        wordDict1.add("aaa");
        wordDict1.add("aaaa");
        wordDict1.add("a");
        List<String> res1 = wordBreak_1(s1, wordDict1);
        System.out.println(res1);

    }
    /**
     * 模板
     * private void backtrack("原始参数") {
     *     //终止条件(递归必须要有终止条件)
     *     if ("终止条件") {
     *         //一些逻辑操作（可有可无，视情况而定）
     *         return;
     *     }
     *     for (int i = "for循环开始的参数"; i < "for循环结束的参数"; i++) {
     *         //一些逻辑操作（可有可无，视情况而定）
     *         //做出选择
     *         //递归
     *         backtrack("新的参数");
     *         //一些逻辑操作（可有可无，视情况而定）
     *         //撤销选择
     *     }
     * }
     */
    /**
     * DFS + Backtracking
     */
    private static Set<String> wordSet = new HashSet<>();
    private static List<String> res = new ArrayList<>();

    public static List<String> wordBreak(String s, List<String> wordDict) {
        //集合Set的查找效率要比集合list高，这里为了提高效率，
        //先把list转化为集合set
        for (String word : wordDict) {
            wordSet.add(word);
        }
        backtrack(s, 0, new ArrayDeque<>());
        return res;
    }
    // DFS 枚举字典中出现的单词
    private static void backtrack(String s, int start, Deque<String> track) {
        if(start == s.length()){
            res.add(String.join(" ", track));
            return;
        }

        for(int i = start; i < s.length(); i++){
            String word = s.substring(start, i + 1);
            if(!wordSet.contains(word)){
                continue;
            }
            track.addLast(word);
            backtrack(s, i + 1, track);
            track.removeLast();
        }
    }
    /**
     * 记忆化递归
     */
    // 使用哈希表存储字符串 s 的每个下标和从该下标开始的部分可以组成的句子列表，在回溯过程中如果遇到已经访问过的下标，
    // 则可以直接从哈希表得到结果，而不需要重复计算。如果到某个下标发现无法匹配，则哈希表中该下标对应的是空列表，
    // 因此可以对不能拆分的情况进行剪枝优化
    static public List<String> wordBreak_1(String s, List<String> wordDict) {
        Map<Integer, List<List<String>>> map = new HashMap<Integer, List<List<String>>>();

        List<List<String>> wordBreaks = backtrack_1(s, s.length(), new HashSet<String>(wordDict), 0, map);

        List<String> breakList = new LinkedList<String>();
        for (List<String> wordBreak : wordBreaks) {
            breakList.add(String.join(" ", wordBreak));
        }
        return breakList;
    }

    static public List<List<String>> backtrack_1(String s, int length, Set<String> wordSet, int index, Map<Integer, List<List<String>>> map) {
        if (!map.containsKey(index)) {
            List<List<String>> wordBreaks = new LinkedList<List<String>>();
            if (index == length) {
                wordBreaks.add(new LinkedList<String>());
            }

            for (int i = index + 1; i <= length; i++) {
                String word = s.substring(index, i);
                if (wordSet.contains(word)) {
                    List<List<String>> nextWordBreaks = backtrack_1(s, length, wordSet, i, map);
                    for (List<String> nextWordBreak : nextWordBreaks) {
                        LinkedList<String> wordBreak = new LinkedList<String>(nextWordBreak);
                        wordBreak.offerFirst(word);
                        wordBreaks.add(wordBreak);
                    }
                }
            }
            map.put(index, wordBreaks);
        }
        return map.get(index);
    }

}
