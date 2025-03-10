package com.answer.greedy;

import java.util.*;

public class Q763_Partition_Labels {
    public static void main(String[] args) {
        String s = "ababcbacadefegdehijhklij";
        System.out.println((partitionLabels(s)));
    }
    /**
     * 贪心
     * 在遍历的过程中相当于是要找每一个字母的边界，如果找到之前遍历过的所有字母的最远边界，说明这个边界就是分割点了。此时前面出现过所有字母，最远也就到这个边界了
     * 可以分为如下两步：
     *  统计每一个字符最后出现的位置
     *  从头遍历字符，并更新字符的最远出现下标，如果找到字符最远出现位置下标和当前下标相等了，则找到了分割点
     *
     *  没有感受到贪心，找不出局部最优推出全局最优的过程。就是用最远出现距离模拟了圈字符的行为。
     */
    public List<Integer> partitionLabels_1(String s) {
        List<Integer> list = new LinkedList<>();
        int[] edge = new int[26]; // 为字符出现的最后位置
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) { // 统计每一个字符最后出现的位置
            edge[chars[i] - 'a'] = i;
        }
        int idx = 0;
        int last = -1;
        for (int i = 0; i < chars.length; i++) {
            idx = Math.max(idx, edge[chars[i] - 'a']); // 找到字符出现的最远边界
            if (i == idx) {
                list.add(i - last);
                last = i;
            }
        }
        return list;
    }
    /**
     * 这里提供一种与452.用最少数量的箭引爆气球、435.无重叠区间 相同的思路。
     * 统计字符串中所有字符的起始和结束位置，记录这些区间(实际上也就是435.无重叠区间 题目里的输入)，将区间按左边界从小到大排序，
     * 找到边界将区间划分成组，互不重叠。找到的边界就是答案。
     */
    public List<Integer> partitionLabels_2(String s) {
        int[][] partitions = findPartitions(s);
        List<Integer> res = new ArrayList<>();
        Arrays.sort(partitions, (o1, o2) -> Integer.compare(o1[0], o2[0]));// 按照左边界从小到大排序
        int right = partitions[0][1]; // 记录最大右边界
        int left = 0;
        for (int i = 1; i < partitions.length; i++) {
            // 由于字符串一定能分割，因此, 一旦下一区间左边界大于当前右边界，即可认为出现分割点
            if (partitions[i][0] > right) { //左边界大于右边界即可记为一次分割
                res.add(right - left + 1);
                left = partitions[i][0];
            }
            right = Math.max(right, partitions[i][1]);
        }
        // 加入最右端字符串
        res.add(right - left + 1);
        return res;
    }
    public int[][] findPartitions(String s) { // 记录每个字母出现的区间
        List<Integer> temp = new ArrayList<>();
        int[][] hash = new int[26][2]; // 26个字母2列 表示该字母对应的区间

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i); // 更新字符c对应的位置i
            if (hash[c - 'a'][0] == 0) {
                hash[c - 'a'][0] = i;
            }
            hash[c - 'a'][1] = i;
            hash[s.charAt(0) - 'a'][0] = 0;   // 第一个元素区别对待一下
        }
        List<List<Integer>> h = new LinkedList<>();
        //组装区间
        for (int i = 0; i < 26; i++) {
            temp.clear();
            temp.add(hash[i][0]);
            temp.add(hash[i][1]);
            h.add(new ArrayList<>(temp));
        }
        int[][] res = new int[h.size()][2];
        for (int i = 0; i < h.size(); i++) {
            List<Integer> list = h.get(i);
            res[i][0] =  list.get(0);
            res[i][1] =  list.get(1);
        }
        return res;
    }

    /**
     * Approach 1: Greedy
     * For each letter encountered, process the last occurrence of that letter, extending the current partition [anchor, j] appropriately.
     *
     * 由于同一个字母只能出现在同一个片段，显然同一个字母的第一次出现的下标位置和最后一次出现的下标位置必须出现在同一个片段。
     *
     * 遍历一遍找到每个字符的最远位置
     * 若找到之前遍历过的字母的最远边界，则找到分割点
     */
    public static List<Integer> partitionLabels(String s) {
        List<Integer> res = new ArrayList<>();
        int[] ch = new int[26];
        Arrays.fill(ch, -1);

        for(int i = 0; i < s.length(); i++){
            ch[s.charAt(i) - 'a'] = i;
        }
        int left = 0, right = 0;
        for(int i = 0; i < s.length(); i++){
            right = Math.max(right, ch[s.charAt(i) - 'a']);
            if(i == right){
                res.add(right - left + 1);
                left = right + 1;
            }
        }

        return res;
    }
}
