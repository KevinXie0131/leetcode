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
     * 示例：
     *  输入：beginWord = "hit", endWord = "cog", wordList = ["hot","dot","dog","lot","log","cog"]
     *  输出：[["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
     *  解释：存在 2 种最短的转换序列：
     *       "hit" -> "hot" -> "dot" -> "dog" -> "cog"
     *       "hit" -> "hot" -> "lot" -> "log" -> "cog"
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
    /** Refer to Q127 Word Ladder
     * Time Limit Exceeded
     */
    static public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<List<String>>();
        Set<String> wordSet = new HashSet<>(wordList); // 将 wordList 存储在一个「哈希表」 wordSet
        if (!wordSet.contains(endWord)) { // 如果不含有结束单词，直接结束，不然后边会造成死循环
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

                HashSet<String> set = new HashSet<>(currentWord);

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
                 //   if (!currentWord.contains(nextWord)) {
                    if (!set.contains(nextWord)) {
                     //   visited.add(nextWord);
                        set.add(nextWord);

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
    /**
     * 本题需要使用「广度优先遍历」来找到「最短转换序列」
     * 求“最短路径”、“最小深度”——BFS
     * 为什么还需要 DFS？
     * 如果是求最短路径的长度，仅 BFS 即可。但要找出最短路径的所有组合，则需要回溯。
     * 我们可以从起点词开始DFS，也可以从终点词开始 DFS，我选择后者，遇到起点词，就找到一条满足条件的路径，推入结果数组，然后回溯，直到找出所有最短路径。
     *
     * 需要使用一个临时的「哈希表」 currentLevelVisited 作为缓冲，等这一层的前驱关系都找到了，
     * 才把 currentLevelVisited 全部加入总的「哈希表」visited 以外，其余就是按照标准的 BFS 写。
     *
     * 总体说来，本题先通过「广度优先遍历」建图，记录每个单词的前驱结点或者后继结点，再通过「回溯算法」把所有的最短路径找出来
     */
    public List<List<String>> findLadders_1(String beginWord, String endWord, List<String> wordList) { // 先执行 BFS 构建图，在 BFS 能够找到目标单词的情况下，再执行 DFS。
        Set<String> wordSet = new HashSet<>(wordList);
        List<List<String>> res = new ArrayList<>();
        if (!wordSet.contains(endWord)) {
            return res;
        }

        Map<String, List<String>> prevWords = new HashMap<>();
        boolean found = bfs(beginWord, endWord, wordSet, prevWords);
        if (found) {
            Deque<String> path = new ArrayDeque<>();
            path.addFirst(endWord);
            dfs(beginWord, endWord, prevWords, path, res);
        }
        return res;
    }
    /**
     * cog 的前驱是 dog 和 log，因此 prevWords 需要记录 dog → cog 和 log → cog，在记录其中一条边的同时，
     * cog 还不能马上加入 visited ，需等待另一条边也记录到 prevWords 以后，才能把 cog 加入 visited ，
     * 因此我们还需要一个「哈希表」作为「缓冲」，这里我们命名为 currentLevelVisited，即当前层级使用的「哈希表」，
     * 这一层的所有单词都访问完才加入 visited
     */
    private boolean bfs(String beginWord, String endWord, Set<String> wordSet, Map<String, List<String>> prevWords) {
        boolean found = false; // 是否到达符合条件的层：如果该层添加的某一单词符合目标单词，则说明截止该层的所有解为最短路径，停止循环

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        Set<String> visited = new HashSet<>();
        visited.add(beginWord);  // 已经访问过的单词集合：只找最短路径，所以之前出现过的单词不用出现在下一层
        Set<String> currentLevelVisited = new HashSet<>(); // currentLevelVisited 仅用于当前层，确保同一层的单词不会重复入队，但仍能记录所有可能的前驱
        while (!queue.isEmpty()) {
            int size = queue.size();
            currentLevelVisited.clear();

            for (int i = 0; i < size; i++) {
                String currentWord = queue.poll();
                List<String> nextWords = getNextWords(currentWord, wordSet);
                for (String nextWord : nextWords) {
                    if (nextWord.equals(endWord)) {
                        // 只标记找到了，还需要完成这一层前驱关系的建立，才能退出循环
                        found = true;
                    }
                    if (!visited.contains(nextWord)) {
                        if (!currentLevelVisited.contains(nextWord)) {   // 累积每一层的结果
                            queue.offer(nextWord);
                            currentLevelVisited.add(nextWord);// 将该单词添加到该层已访问的单词集合中
                        }
                        // 记录前驱
                        prevWords.computeIfAbsent(nextWord, a -> new ArrayList<>()).add(currentWord);
                    }
                }
            }
            visited.addAll(currentLevelVisited);    // 将该层所有访问的单词添加到总的已访问集合中
            if (found) {
                return true;
            }
        }
        return false;
    }
    /**
     * 需要知道当前节点的“父亲们”，它可以从哪些单词变过来——需要prevWords。
     * 根据当前节点的“父亲们”的所在层数，选出满足最短路径的——需要currentLevelVisited。
     * 我们在 BFS 时要构建这种关系，供 DFS 时使用。
     */
    private void dfs(String beginWord, String endWord, Map<String, List<String>> prevWords, Deque<String> path, List<List<String>> res) {
        if (endWord.equals(beginWord)) {
            res.add(new ArrayList<>(path));
            return;
        }
        // 不作此判断会报空指针异常
        if (!prevWords.containsKey(endWord)) {
            return;
        }
        for (String precursor : prevWords.get(endWord)) {
            path.addFirst(precursor);
            dfs(beginWord, precursor, prevWords, path, res);
            path.removeFirst();
        }
    }

}
