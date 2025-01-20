package com.answer.backtracking;

import java.util.*;

public class Q90_Subsets_II {
    public static void main(String[] args) {
        int[] nums = {1,2,2};
        subsetsWithDup(nums);
    }
    /**
     * 这道题目和78.子集  区别就是集合里有重复元素了，而且求取的子集要去重。
     * 注意去重需要先对集合排序
     */
    static List<List<Integer>> result = new ArrayList<List<Integer>>();
    static Deque<Integer> path = new LinkedList<>();
    static int[] used;

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        if(nums.length == 0){
            result.add(new ArrayList());
            return result;
        }
        int[]  used = new int[nums.length]; // 定义set对同⼀节点下的本层去重
        used = new int[nums.length];
        backtracking(nums, 0, used);  // 没有排序
        return result;
    }

    static public void backtracking(int[] nums, int startIndex, int[] used){
        result.add(new ArrayList(path));
        if(startIndex == nums.length){
            return;
        }
        for(int i = startIndex; i < nums.length; i++){
            int j = i;
            boolean isFoundSame = false; // 没有排序 所以需要这样做
            while (j > 0) {
                if (nums[i] == nums[j - 1] && used[j-1] == 0) { //used[j-1] 没有使用过
                    isFoundSame = true;
                    break;
                }
                j--;
            }
            if(isFoundSame){
                continue;
            }
            path.add(nums[i]);
            used[i] = 1;
            backtracking(nums, i + 1, used);
            used[i] = 0;
            path.removeLast();
        }
    }

}
