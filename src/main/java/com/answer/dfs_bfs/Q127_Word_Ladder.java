package com.answer.dfs_bfs;

import java.util.*;

public class Q127_Word_Ladder {
    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        String[] wordList = {"hot","dot","dog","lot","log","cog"};
        System.out.println(ladderLength_2(beginWord, endWord, Arrays.asList(wordList)));
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
        int len = 0;
        Set<String> set = new HashSet<>(wordList); // 使用set来检查字符串是否出现在字符串集合里更快一些

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        visited.add(beginStr);
        queue.offer(beginStr);

        while (!queue.isEmpty()) {
            len++;
            int currentLength = queue.size();
            for (int index = 0; index < currentLength; index++) {
                String node = queue.poll();

                char[] charArray = node.toCharArray();
                //寻找邻接节点
                for (int i = 0; i < charArray.length; i++) {
                    //记录旧值，用于回滚修改
                    char old = charArray[i];
                    for (char ch = 'a'; ch <= 'z'; ch++) { // 遍历26的字母
                        charArray[i] = ch;
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
        }
        return 0;
    }
    /**
     * 从 beginWord 和 endWord 进行双向 BFS，相遇时返回扩展步数即可。
     */
    static public int ladderLength_2(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>();
        for(String word : wordList){
            dict.add(word);
        }

        if (!dict.contains(endWord)) return 0; // 无法转换

        Set<String> s1 = new HashSet<>();
        s1.add(beginWord);
        Set<String> s2 = new HashSet<>();
        s2.add(endWord);

        int step = 1;
        while (!s1.isEmpty() && !s2.isEmpty()) {
            step++;
            // 为了提高效率 对比较小的set进行转换
            if (s1.size() > s2.size()) { // 可以不写
                Set<String> temp = s1;
                s1 = s2;
                s2 = temp;
            }
            // 已经转换的单词可以去除
            for (String w: s1) dict.remove(w);
            for (String w: s2) dict.remove(w);
            // 存放转换后的单词
            Set<String> s = new HashSet<>();

            for (String word: s1) {
                for (int i = 0; i < word.length(); i++) {
                    char[] wordArray = word.toCharArray();
                //    char ch = wordArray[i];  // 可以不写
                    for (char j = 'a'; j <= 'z'; j++) {
                        wordArray[i] = j;
                        String newWord = new String(wordArray);
                        if (s2.contains(newWord)) { // 说明相遇
                            return step;
                        }
                        if (!dict.contains(newWord)){ // 不在字典中 则跳过
                            continue;
                        }
                        s.add(newWord); // 加入此单词
                    }
                //    wordArray[i] = ch; // 可以不写
                }
            }
            s1 = s; //当前层转换结束，s赋值给s1
        }
        return 0;
    }
    /**
     * 另一种形式 有些不好理解
     */
    public static int bfs1(String beginStr, String endStr, List<String> wordList) {
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
