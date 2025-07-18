package com.answer.greedy;

import java.util.*;

public class Q763_Partition_Labels {
    /**
     * 划分字母区间
     * 一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。例如，字符串 "ababcc" 能够被分为 ["abab", "cc"]，但类似 ["aba", "bcc"] 或 ["ab", "ab", "cc"] 的划分是非法的。
     * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
     * 返回一个表示每个字符串片段的长度的列表。
     * 示例 1：
     * 输入：s = "ababcbacadefegdehijhklij"
     * 输出：[9,7,8]
     * 解释：
     *  划分结果为 "ababcbaca"、"defegde"、"hijhklij" 。
     *  每个字母最多出现在一个片段中。
     *  像 "ababcbacadefegde", "hijhklij" 这样的划分是错误的，因为划分的片段数较少。
     */
    public static void main(String[] args) {
        String s = "caedbdedda";
        System.out.println((partitionLabels_2(s)));
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
    static public List<Integer> partitionLabels_1(String s) {
        List<Integer> list = new LinkedList<>();
        int[] edge = new int[26]; // 为字符出现的最后位置
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) { // 统计每一个字符最后出现的位置
            edge[chars[i] - 'a'] = i;
        }
        int start  = 0;  // 待切割的起始位置
        int maxPos = 0; // 已扫描的字符中最远的位置
        for (int i = 0; i < chars.length; i++) {
            maxPos  = Math.max(maxPos , edge[chars[i] - 'a']); // 找到字符出现的最远边界  // 更新「已扫描的字符中最远的位置」
            if (i == maxPos) {  // 正好扫描到「已扫描的字符的最远位置」，到达切割点
                list.add(i - start + 1);
                start = i + 1;     // 更新，下一个待切割的字符串的起始位置
            }
        }
        return list;
    }
    /**
     * 用 Map
     */
    public List<Integer> partitionLabels5(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] charArray = s.toCharArray();
        int length = charArray.length;
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i<charArray.length; i++){
            map.put(charArray[i], i);
        }
        int max = 0;
        int start = 0;
        for(int i = 0; i<length; i++){
            max = Math.max(max, map.get(charArray[i]));
            if(i == max ){
                //开始下一个
                result.add(max - start + 1);
                start = max + 1;
            }
        }
        return result;
    }
    /**
     * 这里提供一种与452.用最少数量的箭引爆气球、435.无重叠区间 相同的思路。
     * 统计字符串中所有字符的起始和结束位置，记录这些区间(实际上也就是435.无重叠区间 题目里的输入)，将区间按左边界从小到大排序，
     * 找到边界将区间划分成组，互不重叠。找到的边界就是答案。
     *
     * 如果区间重叠，就扩充区间，直到不重叠进入下一个区间
     */
    static public List<Integer> partitionLabels_2(String s) {
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

    static public int[][] findPartitions(String s) { // 记录每个字母出现的区间

        int[][] hash = new int[26][2]; // 26个字母2列 表示该字母对应的区间

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i); // 更新字符c对应的位置i
            if (hash[c - 'a'][0] == 0) {
                hash[c - 'a'][0] = i;
            }
            hash[c - 'a'][1] = i;
        }
        hash[s.charAt(0) - 'a'][0] = 0;   // 第一个元素区别对待一下
  //      return hash;

        //组装区间
        List<List<Integer>> h = new LinkedList<>();
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if(hash[i][0] != 0 || hash[i][1] != 0){
                temp.clear();
                temp.add(hash[i][0]);
                temp.add(hash[i][1]);
                h.add(new ArrayList<>(temp));
            }
        }
        if(hash[s.charAt(0) - 'a'][1] == 0){ // for caedbdedda
            temp.clear();
            temp.add(hash[s.charAt(0) - 'a'][0]);
            temp.add(hash[s.charAt(0) - 'a'][1]);
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
