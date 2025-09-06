package com.answer.backtracking;

import java.util.*;

public class Q1079_Letter_Tile_Possibilities {
    /**
     * You have n  tiles, where each tile has one letter tiles[i] printed on it.
     * Return the number of possible non-empty sequences of letters you can make using the letters printed on those tiles.
     * 活字印刷: 有一套活字字模 tiles，其中每个字模上都刻有一个字母 tiles[i]。返回你可以印出的非空字母序列的数目。
     * 注意：本题中，每个活字字模只能使用一次。
     */
    public static void main(String[] args) {
        String tiles = "AAB"; // 解释：可能的序列为 "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA"。
        System.out.println(numTilePossibilities(tiles)); // 8
        String tiles1 = "AAABBC";
        System.out.println(numTilePossibilities(tiles1)); // 188
        String tiles2 = "V";
        System.out.println(numTilePossibilities(tiles2)); // 1
        String tiles3 = "CDC";
        System.out.println(numTilePossibilities(tiles3)); // 8
    }
    /**
     * 要求统计所有可能的字母牌排列数（不重复）
     */
    static public int numTilePossibilities(String tiles) {
        List<String> result = new ArrayList<>();
        StringBuffer path = new StringBuffer();
        int[] used = new int[tiles.length()];

        char[] array = tiles.toCharArray();
        Arrays.sort(array);  //排序将所有使得所有相同字符在数组中连续出现
        backtracking(new String(array), result, path, used);

        return result.size();
    }

    static public void backtracking(String tiles, List<String> result, StringBuffer path, int[] used){
        if(path.length() > 0){
            result.add(path.toString());
        }

        for(int i = 0; i < tiles.length(); i++){
            if (used[i] == 1) { //一个字符只能选择一次，如果当前字符已经选择了，就不能再选了。
                continue;
            }
            if(i > 0 && tiles.charAt(i) == tiles.charAt(i - 1) && used[i - 1] == 0){//过滤掉重复的结果
                continue;
            }

            path.append(tiles.charAt(i));
            used[i] = 1;  //选择当前字符，并把它标记为已选择
            backtracking(tiles,  result, path, used);
            path.deleteCharAt(path.length() - 1);
            used[i] = 0;  //使用完之后再把它给复原。
        }
    }
    /**
     * another form
     */
    int count = 0;

    public int numTilePossibilities1(String tiles) {
        StringBuffer path = new StringBuffer();
        int[] used = new int[tiles.length()];
        char[] array = tiles.toCharArray();
        Arrays.sort(array);
        backtracking1(new String(array), path, used);
        return count;
    }

    public void backtracking1(String tiles, StringBuffer path, int[] used){
        if(path.length() > 0){
            count++;
        }

        for(int i = 0; i < tiles.length(); i++){
            if (used[i] == 1) {
                continue;
            }
            if(i > 0 && tiles.charAt(i) == tiles.charAt(i - 1) && used[i - 1] == 0){
                continue;
            }

            path.append(tiles.charAt(i));
            used[i] = 1;
            backtracking1(tiles, path, used);
            path.deleteCharAt(path.length() - 1);
            used[i] = 0;
        }
    }
    /**
     * another form
     */
    int count1 = 0;

    public int numTilePossibilities1a(String tiles) {
        int[] used = new int[tiles.length()];
        char[] array = tiles.toCharArray();
        Arrays.sort(array);
        backtracking1a( new String(array),  used);
        return count1;
    }

    public void backtracking1a(String tiles, int[] used){
        for(int i = 0; i < tiles.length(); i++){
            if (used[i] == 1) {
                continue;
            }
            if(i > 0 && tiles.charAt(i) == tiles.charAt(i - 1)  && used[i - 1] == 0){
                continue;
            }
            count1++; //选择一个字符，就多了一种结果
            used[i] = 1;
            backtracking1a(tiles, used);
            used[i] = 0;
        }
    }
    /**
     * 方法一：回溯: 要求返回非空字母序列的数目。我们首先统计所有字符的个数，然后用回溯来查找所有排列。
     * 每次搜索中，我们依次遍历所有剩余的字符，每次遍历选用当前字符，将当前字符减一，递归调用搜索函数，
     * 累计搜索完成后的结果，再把当前字符数量加一进行「回溯」。
     * 递归循环的结束条件是第 n 次递归，此时我们用完了所有字符，找到一个可行字母序列，返回结果为 1。
     * 最后我们返回搜索到的所有字符串，因为题目要求返回非空字符串的数目，所以结果还要减一
     */
    public int numTilePossibilities2(String tiles) {
        Map<Character, Integer> count = new HashMap<>();
        for (char t : tiles.toCharArray()) {
            count.put(t, count.getOrDefault(t, 0) + 1);
        }
        Set<Character> tile = new HashSet<>(count.keySet());
        return dfs(tiles.length(), count, tile) - 1;
    }

    private int dfs(int i, Map<Character, Integer> count, Set<Character> tile) {
        if (i == 0) {
            return 1;
        }
        int res = 1;
        for (char t : tile) {
            if (count.get(t) > 0) {
                count.put(t, count.get(t) - 1);
                res += dfs(i - 1, count, tile);
                count.put(t, count.get(t) + 1);
            }
        }
        return res;
    }
    /**
     * 方法一：计数 + 回溯
     * 先用一个哈希表或数组 cnt 统计每个字母出现的次数。
     * 接下来定义一个函数 dfs(cnt)，表示当前剩余字母的计数为 cnt 时，能够组成的不同序列的个数。
     * 在函数 dfs(cnt) 中，我们枚举 cnt 中每个大于 0 的值 cnt[i]，将 cnt[i] 减 1 表示使用了这个字母，序列个数加 1，
     * 然后进行下一层搜索，在搜索结束后，累加返回的序列个数，然后将 cnt[i] 加 1（回溯，恢复现场）。最后返回序列个数。
     */
    public int numTilePossibilities3(String tiles) {
        int[] counter = new int[26];   //统计每个字符的数量
        for (char c : tiles.toCharArray()) {
            ++counter[c - 'A'];
        }
        return dfs1(counter); // tiles 里所有的信息都存在 count 里，对 count 执行深度优先遍历即可
    }

    private int dfs1(int[] counter) {  //遍历所有的字符
        int res = 0; // 递归终止条件是：当前没有可以用的字符（没有显示递归终止条件）
        for (int i = 0; i < counter.length; ++i) {
            if (counter[i] > 0) { //如果当前字符使用完了再查找下一个
                res++;  //使用一个字符，子集数量就会多一个
                counter[i]--; //如果没使用完就继续使用，然后把这个字符的数量减1
                res += dfs1(counter);
                counter[i]++;  // 只需要重置字符频数数组 //当前字符使用完之后，把它的数量还原
            }
        }
        return res;
    }
}
