package com.answer.union_find;

import java.util.*;

public class Q1202_Smallest_String_With_Swaps {
    /**
     * You are given a string s, and an array of pairs of indices in the string pairs where pairs[i] = [a, b]
     * indicates 2 indices(0-indexed) of the string.
     * You can swap the characters at any pair of indices in the given pairs any number of times.
     * Return the lexicographically smallest string that s can be changed to after using the swaps.
     * 交换字符串中的元素
     * 一个字符串 s，以及该字符串中的一些「索引对」数组 pairs，其中 pairs[i] = [a, b] 表示字符串中的两个索引（编号从 0 开始）。
     * 你可以 任意多次交换 在 pairs 中任意一对索引处的字符。
     * 返回在经过若干次交换后，s 可以变成的按字典序最小的字符串。
     *
     * 示例 1:
     *  输入：s = "dcab", pairs = [[0,3],[1,2]]
     *  输出："bacd"
     *  解释：交换 s[0] 和 s[3], s = "bcad"
     *        交换 s[1] 和 s[2], s = "bacd"
     */
    public static void main(String[] args) {
        String s = "dcab";
        int[][] pairs = {{0,3},{1,2}}; // [[0,3],[1,2],[0,2]]
        List<List<Integer>> pairs1 = new ArrayList<>();
        pairs1.add(Arrays.asList(0, 3));
        pairs1.add(Arrays.asList(1, 2));
        pairs1.add(Arrays.asList(0, 2));
        System.out.println(smallestStringWithSwaps(s, pairs1));
    }
    /**
     * 并查集
     * 将每一个字符抽象为「点」，那么这些「索引对」即为「边」，我们只需要维护这个「图」的连通性即可。对于同属一个连通块（极大连通子图）内的字符，我们可以任意地交换它们。
     * 利用并查集维护任意两点的连通性，将同属一个连通块内的点提取出来，直接排序后放置回其在字符串中的原位置即可。
     */
    static int[] connected;

    static public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int length = s.length();
        int n = pairs.size();
        connected = new int[length];
        for(int i = 0; i < length; i++) {
            connected[i] = i;
        }

        for(List<Integer> pair : pairs) {
            if (find(connected, pair.get(0)) != find(connected, pair.get(1))  ) {
                union(connected, pair.get(0), pair.get(1) );    //合并两个连通分量
            }
        }

        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();

        for(int i = 0; i < length; i++){
            int parent = find(connected, i);  //具有同一parent的，把他们放到同一队列中
            PriorityQueue queue = map.getOrDefault(parent, new PriorityQueue<>());
            queue.offer(s.charAt(i));
            map.put(parent, queue);
//            if (map.containsKey(parent)) {
//                map.get(parent).offer(s.charAt(i));
//            } else {
//                PriorityQueue<Character> minHeap = new PriorityQueue<>();
//                minHeap.offer(s.charAt(i));
//                map.put(parent, minHeap);
//            }
            // 上面六行代码等价于下面一行代码，JDK 1.8 以及以后支持下面的写法
    //       map.computeIfAbsent(parent, key -> new PriorityQueue<>()).offer(s.charAt(i));
            // 下面的写法也可以
           /* if (!map.containsKey(parent)) {
                map.put(parent, new PriorityQueue<Character>());
            }
            map.get(parent).offer(s.charAt(i));*/
        }

        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < length; i++){
            int parent = find(connected, i);
            PriorityQueue queue = map.get(parent);
            sb.append(queue.poll());  //找到i所在的队列，然后元素出队（这里的队列使用的是PriorityQueue，其实就是个最小堆，每次出来的都是队列中最小的值）
        }
        return sb.toString();
    }
    /**
     * another form
     */
    static public String smallestStringWithSwaps2(String s, List<List<Integer>> pairs) {
        int length = s.length();
        int n = pairs.size();
        connected = new int[length];
        for(int i = 0; i < length; i++) {
            connected[i] = i;
        }

        for(List<Integer> pair : pairs) {
            if (find(connected, pair.get(0)) != find(connected, pair.get(1))  ) {
                union(connected, pair.get(0), pair.get(1) );    //合并两个连通分量
            }
        }

        Map<Integer, ArrayList<Character>> map = new HashMap<>();

        for(int i = 0; i < length; i++){
            int parent = find(connected, i);
            if (!map.containsKey(parent)) {
                map.put(parent, new ArrayList<Character>());
            }
            map.get(parent).add(s.charAt(i));
        }
        for (Map.Entry<Integer, ArrayList<Character>> entry : map.entrySet()) {
       /*     Collections.sort(entry.getValue(), new Comparator<Character>() {
                public int compare(Character c1, Character c2) {
                    return c2 - c1;
                }
            });*/
            Collections.sort(entry.getValue(),  (c1, c2) -> Character.compare(c1, c2));
        }

        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < length; i++){
            int parent = find(connected, i);
            List<Character> list = map.get(parent);
            sb.append(list.remove(list.size() - 1));
        }
        return sb.toString();
    }

    public static void union(int[] parent, int index1, int index2) {
        parent[find(parent, index2)] = find(parent, index1);
    }

    public static int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }
}
