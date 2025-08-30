package com.answer.dfs_bfs;

import java.util.*;

public class Q127_Word_Ladder_1 { // Hard 困难
    /**
     * BFS 广度优先搜索
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        visited.add(beginWord);
        int count = 0;

        while (queue.size() > 0) {
            int size = queue.size();
            ++count;

            for (int i = 0; i < size; ++i) {
                String cur = queue.poll();
                for (String word : wordList) {
                    if (visited.contains(word)) { // 已经遍历的不再重复遍历
                        continue;
                    }
                    if (!canConvert(cur, word)) {  // 不能转换的直接跳过
                        continue;
                    }
                    // 用于调试
                    // System.out.println(count + ": " + start + "->" + s);
                    if (word.equals(endWord)) {// 可以转换，并且能转换成 endWord，则返回 count
                        return count + 1;
                    }
                    visited.add(word); // 保存访问过的单词，同时把单词放进队列，用于下一层的访问
                    queue.offer(word);
                }
            }
        }
        return 0;
    }

    public boolean canConvert(String s1, String s2) {
        if (s1.length() != s2.length()) { // 因为题目说了单词长度相同，可以不考虑长度问题
            return false;
        }
        int count = 0;
        for (int i = 0; i < s1.length(); ++i) {
            if (s1.charAt(i) != s2.charAt(i)) {
                ++count;
                if (count > 1) {
                    return false;
                }
            }
        }
        return count == 1;
    }
    /**
     * 优化 visited 标记
     * 把 visited 从 HashSet 改成 boolean 数组，通过 index 判断是否已访问
     */
    public int ladderLength_1(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        // visited 修改为 boolean 数组
        boolean[] visited = new boolean[wordList.size()];
        int idx = wordList.indexOf(beginWord);
        if (idx != -1)  {
            visited[idx] = true;
        }
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int count = 0;

        while (queue.size() > 0) {
            int size = queue.size();
            ++count;

            while (size-- > 0) {
                String cur = queue.poll();
                for (int i = 0; i < wordList.size(); ++i) {
                    if (visited[i]) {  // 通过 index 判断是否已经访问
                        continue;
                    }
                    String word = wordList.get(i);
                    if (!canConvert(cur, word)) {
                        continue;
                    }
                    if (word.equals(endWord)) {
                        return count + 1;
                    }
                    visited[i] = true;
                    queue.offer(word);
                }
            }
        }
        return 0;
    }
    /**
     * 对双向 BFS 进行优化，主要的优化点就是每次遍历一层时，从节点更少的一端遍历。果然优化后耗时大大下降
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        int end = wordList.indexOf(endWord);
        if (end == -1) {
            return 0;
        }
        wordList.add(beginWord);
        int start = wordList.size() - 1;

        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
        Set<Integer> visited1 = new HashSet<>();
        Set<Integer> visited2 = new HashSet<>();
        queue1.offer(start);
        queue2.offer(end);
        visited1.add(start);
        visited2.add(end);
        int count = 0;

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            count++;
            if (queue1.size() > queue2.size()) {
                Queue<Integer> tmp = queue1;
                queue1 = queue2;
                queue2 = tmp;
                Set<Integer> tmp1 = visited1;
                visited1 = visited2;
                visited2 = tmp1;
            }
            int size1 = queue1.size();
            while (size1-- > 0) {
                String cur = wordList.get(queue1.poll());
                for (int i = 0; i < wordList.size(); ++i) {
                    if (visited1.contains(i)) {
                        continue;
                    }
                    if (!canConvert(cur, wordList.get(i))) {
                        continue;
                    }
                    if (visited2.contains(i)) {
                        return count + 1;
                    }
                    visited1.add(i);
                    queue1.offer(i);
                }
            }
        }
        return 0;
    }
    /**
     * 双向BFS + 单词转换判断的优化
     * 因为单词是由 a~z 这有限数量的字符组成的，可以遍历当前单词能转换成的所有单词，判断其是否包含在候选单词中。候选单词用 HashSet 保存，可以大大提高判断包含关系的性能。
     */
    public int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        int end = wordList.indexOf(endWord);
        if (end == -1) {
            return 0;
        }
        wordList.add(beginWord);
        // 从两端 BFS 遍历要用的队列
        Queue<String> queue1 = new LinkedList<>();
        Queue<String> queue2 = new LinkedList<>();
        // 两端已经遍历过的节点
        Set<String> visited1 = new HashSet<>();
        Set<String> visited2 = new HashSet<>();
        queue1.offer(beginWord);
        queue2.offer(endWord);
        visited1.add(beginWord);
        visited2.add(endWord);

        int count = 0;
        Set<String> allWordSet = new HashSet<>(wordList);// 存储字典中的单词+去重(List->Set)

        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            count++;
            if (queue1.size() > queue2.size()) {  // 从beginSet和endSet中选择当前数目小的进行下一层搜索,令节点数少的为beginSet
                Queue<String> tmp = queue1;
                queue1 = queue2;
                queue2 = tmp;
                Set<String> tmp1 = visited1;
                visited1 = visited2;
                visited2 = tmp1;
            }
            int size1 = queue1.size();
            while (size1-- > 0) {
                String cur = queue1.poll();
                char[] chars = cur.toCharArray();
                for (int j = 0; j < cur.length(); ++j) {
                    // 保存第j位的原始字符
                    char oldChar = chars[j];
                    for (char ch = 'a'; ch <= 'z'; ++ch) {  // 每个字母可改变范围为'a'-'z'
                        chars[j] = ch;
                        String newString = new String(chars);
                        // 已经访问过了，跳过
                        if (visited1.contains(newString)) {
                            continue;
                        }
                        // 两端遍历相遇，结束遍历，返回 count
                        if (visited2.contains(newString)) {   // 若相遇了,直接返回搜索节点总个数,此时的路径最短
                            return count + 1; // 最后包含当前层搜的再+1
                        }
                        // 如果单词在列表中存在，将其添加到队列，并标记为已访问
                        if (allWordSet.contains(newString)) {
                            queue1.offer(newString);
                            visited1.add(newString);
                        }
                    }
                    // 恢复第j位的原始字符
                    chars[j] = oldChar;
                }
            }
        }
        return 0;
    }
}
