package com.answer.sorting;

import java.util.*;

public class Q2343_Query_Kth_Smallest_Trimmed_Number {
    /**
     * 裁剪数字后查询第 K 小的数字
     * 一个下标从 0 开始(0-indexed)的字符串数组 nums ，其中每个字符串 长度相等 且只包含数字(equal length and consists of only digits)。
     * 再给你一个下标从 0 开始的二维整数数组 queries ，其中 queries[i] = [ki, trimi] 。对于每个 queries[i] ，你需要：
     *  将 nums 中每个数字 裁剪(Trim ) 到剩下 最右边(rightmost ) trimi 个数位。
     *  在裁剪过后的数字(smallest trimmed number )中，找到 nums 中第 ki 小数字对应的 下标(index) 。如果两个裁剪后数字一样大，那么下标 更小(lower index) 的数字视为更小的数字。
     *  将 nums 中每个数字恢复(Reset)到原本字符串。
     * 请你返回一个长度与 queries 相等的数组 answer，其中 answer[i]是第 i 次查询的结果。
     * 提示：
     *  裁剪到剩下最右边 x 个数位的意思是不断删除最左边的数位(keep removing the leftmost digit)，直到剩下 x 个数位。
     *  nums 中的字符串可能会有前导 0 (leading zeros)。
     * Follow up: Could you use the Radix Sort Algorithm to solve this problem? What will be the complexity of that solution?
     * 进阶：你能使用 基数排序算法 解决此问题吗？这种解法的复杂度又是多少？
     *
     * 示例 1：
     * 输入：nums = ["102","473","251","814"], queries = [[1,1],[2,3],[4,2],[1,2]]
     * 输出：[2,2,1,0]
     * 解释：
     * 1. 裁剪到只剩 1 个数位后，nums = ["2","3","1","4"] 。最小的数字是 1 ，下标为 2 。
     * 2. 裁剪到剩 3 个数位后，nums 没有变化。第 2 小的数字是 251 ，下标为 2 。
     * 3. 裁剪到剩 2 个数位后，nums = ["02","73","51","14"] 。第 4 小的数字是 73 ，下标为 1 。
     * 4. 裁剪到剩 2 个数位后，最小数字是 2 ，下标为 0 。
     *    注意，裁剪后数字 "02" 值为 2 。
     * 示例 2：
     * 输入：nums = ["24","37","96","04"], queries = [[2,1],[2,2]]
     * 输出：[3,0]
     * 解释：
     * 1. 裁剪到剩 1 个数位，nums = ["4","7","6","4"] 。第 2 小的数字是 4 ，下标为 3 。
     *    有两个 4 ，下标为 0 的 4 视为小于下标为 3 的 4 。
     * 2. 裁剪到剩 2 个数位，nums 不变。第二小的数字是 24 ，下标为 0 。
     */
    public static void main(String[] args) {
        String[] nums = {"24","37","96","04"};
       int[][] queries = {{2,1}, {2,2}};
       System.out.println(Arrays.toString(smallestTrimmedNumbers(nums, queries)));
    }
    /**
     * 自定义类Arrays.sort排序
     * 自定义类(值,下标)实现Comparable接口用Arrays.sort对每个长度的对象数组按自定义compareTo方法离线排序。
     */
    static public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
        int n = nums.length, m = queries.length, len = nums[0].length();
        int[] ans = new int[m];
        Pair[][] rank = new Pair[len][n];

        for(int i = 0; i < n; i++){
            StringBuilder sb = new StringBuilder(nums[i]);
            for(int j = len - 1; j >= 0; j--){
                rank[j][i] = new Pair(i, sb.toString());
                sb.deleteCharAt(0);
            }
        }
        for(int i = 0; i < len; i++){
            Arrays.sort(rank[i]);
        }
        for(int i = 0; i < m; i++){
            ans[i] = rank[queries[i][1] - 1][queries[i][0] - 1].p;
        }
        return ans;
    }
    /**
     * Java自定义排序规则即可
     * 注意即使后trim位相同，也必须返回第k小的index
     */
    public int[] smallestTrimmedNumbers1(String[] nums, int[][] queries) {
        int numLen = nums.length, qLen = queries.length, strLen = nums[0].length();
        int[] result = new int[qLen];

        List<Pair1<String, Integer>> list = new ArrayList<>(); //也可用二维数组代替,Integer表示该String在原nums数组中的index
        for (int i = 0; i < numLen; i++) {
            list.add(new Pair1<>(nums[i], i));
        }
        for (int i = 0; i < qLen; i++) {
            int k = queries[i][0], trim = queries[i][1];
            list.sort((o1, o2) -> { //自定义排序规则
                for (int j = strLen - trim; j < strLen; j++) {
                    if (o1.key.charAt(j) > o2.key.charAt(j)) return 1; //o1.key的后trim位大于o2.key的后trim位
                    else if (o1.key.charAt(j) < o2.key.charAt(j)) return -1; //o1.key的后trim位小于o2.key的后trim位
                }
                return o1.value - o2.value; //o1.key的后trim位等于o2.key的后trim位，则再比较o1和o2的value值
            });
            result[i] = list.get(k - 1).value; //第k小的index
        }
        return result;
    }
}

class Pair implements Comparable<Pair>{
    int p;
    String s;
    public Pair(int p,String s){
        this.p = p;
        this.s = s;
    }
    @Override
    public int compareTo(Pair b){
        int d = s.compareTo(b.s);
        if(d != 0) return d;
        return p - b.p;
    }
}

class Pair1<T1, T2> {
    T1 key;
    T2 value;

    public Pair1(T1 key, T2 value) {
        this.key = key;
        this.value = value;
    }

    public Pair1(String s) {

    }


    public T1 getKey() {
        return key;
    }

    public void setKey(T1 key) {
        this.key = key;
    }

    public T2 getValue() {
        return value;
    }

    public void setValue(T2 value) {
        this.value = value;
    }
}
