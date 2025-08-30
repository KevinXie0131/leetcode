package com.answer.dfs_bfs;

import java.util.*;

public class Q127_Word_Ladder_2 { // Hard 困难
    /**
     * 单向 BFS
     * 同时，要避免重复访问，hot->dot->hot，别这样变回来，徒增转换的次数
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList); // 将 wordList 存储在一个「哈希表」 wordSet 中，方便快速判断某个单词是否出现在单词列表中；
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        Queue<String> queue = new LinkedList<>(); // 「队列」中存储的是当前单词
        queue.offer(beginWord);
        // 本题还可以不使用 visited，在访问完一个单词以后，从 wordSet 中删除，作用是一样的
        Set<String> visited = new HashSet<>(); // 由于「图」中存在环，因此访问过的单词需要标记，通常的做法是使用「哈希表」visited 记录已经访问的单词
        visited.add(beginWord);
        int step = 1; // 初始化的时候step 为 1

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();
                List<String> nextWords = getNextWords(currentWord, wordSet);
                for (String nextWord : nextWords) {
                    if (nextWord.equals(endWord)) {
                        return step + 1; // 当遇到 endWord 时，返回当前路径的单词数目。
                    }
                    if (!visited.contains(nextWord)) {
                        queue.add(nextWord);
                        visited.add(nextWord); // 注意：添加到队列以后，必须马上标记为「已经访问」
                    }
/*                    if (wordSet.contains(nextWord)) { // 避免该词重复入列
                        queue.add(nextWord);
                        wordSet.remove(nextWord);
                    }*/
                }
            }
            step++;
        }
        return 0;
    }

    private List<String> getNextWords(String word, Set<String> wordSet) {
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
    /**
     * 双向 BFS
     * 由于在扩散的时候，需要判断扩撒是不是产生了交汇，即某个单词是不是出现在对面的单词列表中，
     * 所以对面的对面的单词列表就需要用「哈希表」存储，交替使用。因此我们使用 beginVisited 和 endVisited：分别用于正向和反向的「广度优先遍历」
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }
        // 这是「广度优先遍历」要使用的 visited 变量
        Set<String> visited = new HashSet<>(); // 「哈希表」visited 的作用和「单向 BFS」一样，都是记录已经访问过的单词，避免重复访问。
        // 分别用左边和右边扩散的「哈希表」代替「单向 BFS」里的「队列」，它们在「双向 BFS」的过程中交替使用，这是因为需要快速判断某个单词是否在对面的「队列」中
        Set<String> beginVisited = new HashSet<>();
        beginVisited.add(beginWord);
        Set<String> endVisited = new HashSet<>();
        endVisited.add(endWord);

        int step = 1;
        while (!beginVisited.isEmpty()) {
            if (beginVisited.size() > endVisited.size()) {//在每次循环中，选择单词数量较小的那一侧进行扩展，这样可以减少搜索空间；
                Set<String> temp = beginVisited;
                beginVisited = endVisited;
                endVisited = temp;
            }
            // nextLevelVisited 在扩散完成以后，会成为新的 beginVisited
            Set<String> nextLevelVisited = new HashSet<>();
            for (String word : beginVisited) {
                List<String> nextWords = getNextWords(word, wordSet); //对于「哈希表」中的每个单词，对其每个位置的字符进行替换，生成新的单词；
                for (String nextWord : nextWords) {
                    if (endVisited.contains(nextWord)) {
                        // 检测到在对面的单词列表中，相当于又扩散了一次
                        return step + 1; // 如果新单词在另一个「哈希表」中，说明两个方向的搜索相遇了，相当于又扩散了一次，返回当前路径长度加 1；
                    }
                    if (!visited.contains(nextWord)) { // 如果新单词在字典中且未被访问过，将其加入下一层的「哈希表」，并标记为已访问。
                        nextLevelVisited.add(nextWord);
                        visited.add(nextWord);
                    }
                }
            }
            beginVisited = nextLevelVisited;
            step++;
        }
        return 0;
    }
}
