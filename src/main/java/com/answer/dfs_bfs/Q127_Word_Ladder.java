package com.answer.dfs_bfs;

import java.util.*;

public class Q127_Word_Ladder {
    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        String[] wordList = {"hot","dot","dog","lot","log","cog"};
        System.out.println(ladderLength(beginWord, endWord, Arrays.asList(wordList)));

    }
    /**
     * 序列中第一个字符串是 beginStr。
     * 序列中最后一个字符串是 endStr。
     * 每次转换只能改变一个字符。
     * 转换过程中的中间字符串必须是字典 strList 中的字符串。
     *
     * 思路: 本题只需要求出最短路径的长度就可以了，不用找出具体路径. 如果差一个字符，那就是有链接。
     *       然后就是求起点和终点的最短路径长度，这里无向图求最短路，广搜最为合适，广搜只要搜到了终点，那么一定是最短的路径。
     *       因为广搜就是以起点中心向四周扩散的搜索。
     *       本题如果用深搜，会比较麻烦，要在到达终点的不同路径中选则一条最短路。 而广搜只要达到终点，一定是最短路
     * 注意点：
     *       本题是一个无向图，需要用标记位，标记着节点是否走过，否则就会死循环！
     *       使用set来检查字符串是否出现在字符串集合里更快一些
     */
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int count = bfs(beginWord, endWord, wordList);
        return count;
    }
    /**
     * 广度优先搜索-寻找最短路径
     */
    public static int bfs(String beginStr, String endStr, List<String> wordList) {
        int len = 1;
        Set<String> set = new HashSet<>(wordList); // 使用set来检查字符串是否出现在字符串集合里更快一些

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        visited.add(beginStr);
        queue.offer(beginStr);
        queue.offer(null);

        while (!queue.isEmpty()) {
            String node = queue.poll();
            //上一层结束，若下一层还有节点进入下一层
            if (node == null) {
                if (!queue.isEmpty()) {
                    len++;
                    queue.offer(null);
                }
                continue;
            }
            char[] charArray = node.toCharArray();
            //寻找邻接节点
            for (int i = 0; i < charArray.length; i++) {
                //记录旧值，用于回滚修改
                char old = charArray[i];
                for (char j = 'a'; j <= 'z'; j++) { // 遍历26的字母
                    charArray[i] = j;
                    String newWord = new String(charArray); // 用一个新字符串替换str，因为每次要置换一个字符
                    if (set.contains(newWord) && !visited.contains(newWord)) {  // 字符串集合里出现了newWord，并且newWord没有被访问过
                        queue.offer(newWord);
                        visited.add(newWord); // 添加访问信息，并将新字符串放到队列中
                        //找到结尾
                        if (newWord.equals(endStr)) { // 发现替换字母后，字符串与终点字符串相同
                            return len + 1; // 找到了路径
                        }
                    }
                }
                charArray[i] = old;
            }
        }
        return 0;
    }
}
