package com.answer.dfs_bfs;

import java.util.*;

public class Q126_Word_Ladder_II { // Hard 困难
    /**
     * 单词接龙 II
     * 按字典 wordList 完成从单词 beginWord 到单词 endWord 转化(transformation sequence)，一个表示此过程的 转换序列 是形式上像 beginWord -> s1 -> s2 -> ... -> sk 这样的单词序列，并满足：
     *  每对相邻(adjacent pair)的单词之间仅有单个字母不同。
     *  转换过程中的每个单词 si（1 <= i <= k）必须是字典 wordList 中的单词。注意，beginWord 不必是字典 wordList 中的单词。
     *  sk == endWord
     * 给你两个单词 beginWord 和 endWord ，以及一个字典 wordList 。请你找出并返回所有从 beginWord 到 endWord 的 最短转换序列(shortest transformation sequences)，
     * 如果不存在这样的转换序列，返回一个空列表。每个序列都应该以单词列表 [beginWord, s1, s2, ..., sk] 的形式返回。
     *
     * 输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
     * 输出：[["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
     * 解释：存在 2 种最短的转换序列：
     *      "hit" -> "hot" -> "dot" -> "dog" -> "cog"
     *      "hit" -> "hot" -> "lot" -> "log" -> "cog"
     */
    public static void main(String[] args) {
     /*   String beginWord = "hit";
        String endWord = "cog";
        List<String>  wordList = new ArrayList<>(Arrays.asList("hot","dot","dog","lot","log","cog"));*/
        String beginWord = "red";
        String endWord = "tax";
        List<String>  wordList = new ArrayList<>(Arrays.asList("ted","tex","red","tax","tad","den","rex","pee"));
        List<List<String>> result = findLadders(beginWord, endWord, wordList);
        System.out.println(result);
    }
    // Refer to Q127 Word Ladder
    // Time Limit Exceeded
    static public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<List<String>>();
        Set<String> wordSet = new HashSet<>(wordList); // 将 wordList 存储在一个「哈希表」 wordSet
        if (!wordSet.contains(endWord)) {
            return result;
        }

        Deque<List<String>> queue = new ArrayDeque<>();
        queue.offer(new ArrayList<>(Arrays.asList(beginWord)));

       // HashSet<String> visited = new HashSet<>(); // 由于「图」中存在环，因此访问过的单词需要标记，通常的做法是使用「哈希表」visited 记录已经访问的单词
       // visited.add(beginWord);
        int minlength = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                List<String> currentWord = queue.poll();
                //当前的长度到达了 min，还是没有到达结束单词就提前结束
                if (currentWord.size() >= minlength) {
                    continue;
                }
                String lastWord = currentWord.get(currentWord.size() - 1);

                List<String> nextWords = getNextWords(lastWord, wordSet);
                for (String nextWord : nextWords) {
                    if (nextWord.equals(endWord)) { // 当遇到 endWord 时
                        currentWord.add(endWord);
                        if(currentWord.size() < minlength){ //当前长度更小，清空之前的，加新的路径加入到结果中
                            minlength = currentWord.size();
                            result.clear();
                            result.add(currentWord);
                        } else if(currentWord.size() == minlength){  //相等的话就直接加路径加入到结果中
                            result.add(currentWord);
                        }
                    }

                    if (!currentWord.contains(nextWord)) {
                     //   visited.add(nextWord);
                        List<String> list = new ArrayList<>(currentWord);
                        list.add(nextWord);
                        queue.offer(list);
                    }
                }
            }
        }
       /* int min = Integer.MAX_VALUE;
        for(List<String> list : result){
            if(list.size() < min) min = list.size();
        }

        Iterator<List<String> > iterator = result.iterator();
        while (iterator.hasNext()) {
            List<String>  list = iterator.next();
            if (list.size() > min) {
                iterator.remove(); // Safely removes
            }
        }*/
        return result;
    }

    static private List<String> getNextWords(String word, Set<String> wordSet) {
        List<String> nextWords = new ArrayList<>();
        char[] charArray = word.toCharArray();
        int wordLen = word.length();
        // 尝试对 word 修改每一个字符，得到所有的字符串
        for (int i = 0; i < wordLen; i++) {
            char originChar = charArray[i];
            for (char j = 'a'; j <= 'z'; j++) {
                if (j == originChar) {
                    continue;
                }
                charArray[i] = j;
                String nextWord = String.valueOf(charArray);
                // 在 wordSet 中有的字符才添加进 nextWords，否则 nextWords 里会加入很多单词
                if (wordSet.contains(nextWord)) {
                    nextWords.add(nextWord);
                }
            }
            charArray[i] = originChar;
        }
        return nextWords;
    }
}
