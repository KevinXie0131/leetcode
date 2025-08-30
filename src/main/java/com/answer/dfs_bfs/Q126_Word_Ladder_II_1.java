package com.answer.dfs_bfs;

import java.util.*;

public class Q126_Word_Ladder_II_1 { // Hard 困难
    /**
     * Leetcode 126 单词接龙 II
     * 给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。
     * 每次只能改变一个字母，且转换后的新单词必须在字典中。
     */
    /**
     * 主方法：寻找所有最短转换路径
     * @param beginWord 起始单词
     * @param endWord 终止单词
     * @param wordList 单词列表
     * @return 所有最短路径列表
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList); // 将单词列表转为集合，便于查找
        List<List<String>> results = new ArrayList<>(); // 用于存储所有结果路径
        if (!wordSet.contains(endWord)) return results; // 如果字典不包含终止词，直接返回空列表

        Map<String, List<String>> neighbors = new HashMap<>(); // 每个单词的相邻单词（只差一个字符）
        Map<String, Integer> distances = new HashMap<>(); // 每个单词到 beginWord 的距离

        bfs(beginWord, endWord, wordSet, neighbors, distances); // BFS 构建距离和邻居关系
        List<String> path = new ArrayList<>(); // 当前路径
        dfs(beginWord, endWord, neighbors, distances, path, results); // DFS 搜索所有最短路径

        return results;
    }
    /**
     * 广度优先搜索，建立每个单词到起始单词的距离，以及邻居映射
     * @param beginWord 起始单词
     * @param endWord 终止单词
     * @param wordSet 单词集合
     * @param neighbors 邻居映射
     * @param distances 距离映射
     */
    private void bfs(String beginWord, String endWord, Set<String> wordSet,
                     Map<String, List<String>> neighbors, Map<String, Integer> distances) {
        Queue<String> queue = new LinkedList<>(); // 队列用于BFS
        queue.offer(beginWord); // 起始单词入队
        distances.put(beginWord, 0); // 起始单词距离为0

        for (String word : wordSet) neighbors.put(word, new ArrayList<>()); // 初始化邻居映射
        neighbors.put(beginWord, new ArrayList<>());

        boolean foundEnd = false; // 是否找到终止单词
        while (!queue.isEmpty() && !foundEnd) {
            int size = queue.size(); // 当前层的节点数
            Set<String> visited = new HashSet<>(); // 本层访问过的单词集合

            for (int i = 0; i < size; i++) {
                String curr = queue.poll(); // 当前单词出队
                int currDist = distances.get(curr); // 当前单词到起始单词的距离
                List<String> currNeighbors = getNeighbors(curr, wordSet); // 获取所有相邻单词

                for (String next : currNeighbors) {
                    neighbors.get(curr).add(next); // 记录邻居
                    if (!distances.containsKey(next)) { // 如果 next 没访问过
                        distances.put(next, currDist + 1); // 距离加一
                        if (next.equals(endWord)) foundEnd = true; // 找到终止词，后续只处理本层
                        visited.add(next); // 标记访问
                    }
                }
            }

            for (String word : visited) queue.offer(word); // 将本层访问过的单词加入队列，进行下一层的BFS
        }
    }
    /**
     * 获取单词的所有邻居（只差一个字符且在字典中）
     * @param word 当前单词
     * @param wordSet 单词集合
     * @return 所有邻居单词列表
     */
    private List<String> getNeighbors(String word, Set<String> wordSet) {
        List<String> res = new ArrayList<>();
        char[] arr = word.toCharArray(); // 转为字符数组，便于逐位替换
        for (int i = 0; i < arr.length; i++) {
            char oldChar = arr[i]; // 保存原字符
            for (char c = 'a'; c <= 'z'; c++) { // 尝试替换每一个字母
                if (c == oldChar) continue; // 如果相同则跳过
                arr[i] = c; // 替换
                String newWord = new String(arr); // 新单词
                if (wordSet.contains(newWord)) res.add(newWord); // 新单词在集合中则加入结果
            }
            arr[i] = oldChar; // 恢复原字符
        }
        return res;
    }
    /**
     * 深度优先搜索，收集所有最短路径
     * @param curr 当前单词
     * @param endWord 终止单词
     * @param neighbors 邻居映射
     * @param distances 距离映射
     * @param path 当前路径
     * @param results 结果路径列表
     */
    private void dfs(String curr, String endWord, Map<String, List<String>> neighbors,
                     Map<String, Integer> distances, List<String> path, List<List<String>> results) {
        path.add(curr); // 当前单词加入路径
        if (curr.equals(endWord)) {
            results.add(new ArrayList<>(path)); // 找到一条最短路径，加入结果集
        } else {
            for (String next : neighbors.get(curr)) { // 遍历所有邻居
                // 只在最短路径上递归
                if (distances.get(next) == distances.get(curr) + 1) {
                    dfs(next, endWord, neighbors, distances, path, results);
                }
            }
        }
        path.remove(path.size() - 1); // 回溯，移除当前单词
    }
}
