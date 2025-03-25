package com.answer.backtracking;

import java.util.*;

public class Q46_Permutations {
    /**
     * 首先排列是有序的，也就是说 [1,2] 和 [2,1] 是两个集合，这和之前分析的子集以及组合所不同的地方
     *
     * 排列问题的不同:
     *    每层都是从0开始搜索⽽不是startIndex
     *    需要used数组记录path⾥都放了哪些元素了
     * 时间复杂度: O(n!)
     * 空间复杂度: O(n)
     */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Deque<Integer> path = new ArrayDeque<>();
        int[] used = new int[nums.length]; // 排列问题需要一个used数组，标记已经选择的元素

        backtracking(nums, used, result, path);// 回溯法，维护两个数组，分别记录当前排列和每个数字的使用情况，之后枚举每个位置可能出现的数字即可
        return result;
    }

    public void backtracking(int[] nums, int[] used, List<List<Integer>> result, Deque<Integer> path){
        // 当收集元素的数组path的大小达到和nums数组一样大的时候，说明找到了一个全排列，也表示到达了叶子节点。
        if (path.size() == nums.length) {  // 此时说明找到了⼀组
            result.add(new ArrayList<Integer>(path)); // 避免引用传递
            return;
        }
        // i 是从0开始的，这个和Combination不同
        for(int i = 0; i < nums.length; i++){ // 最大的不同就是for循环里不用startIndex了
            if( used[i] == 1){ // used数组，其实就是记录此时path里都有哪些元素使用了，一个排列里一个元素只能使用一次
                continue; // path⾥已经收录的元素，直接跳过
            }
            path.addLast(nums[i]);
            used[i] = 1;
            backtracking(nums, used, result, path); // 所以处理排列问题就不⽤使⽤startIndex了
            path.removeLast();
            used[i] = 0;
        }
        return;
    }

    // 解法2：通过判断path中是否存在数字，排除已经选择的数字
/*    for(int i = 0 ; i < nums.length; i++){
        if(path.contains(nums[i])){ // 如果path中已有，则跳过
            continue;
        }
        path.add(nums[i]);
        backtracking(nums);
        path.remove(path.size()-1);
    }*/
}
