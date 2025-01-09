package com.answer.backtracking;

import java.util.*;

public class Q491_Non_decreasing_Subsequences {
    public static void main(String[] args) {

    }
    /**
     * 这个递增子序列比较像是取有序的子集。而且本题也要求不能有相同的递增子序列。
     * 这又是子集，又是去重, 在90.子集II (opens new window)中我们是通过排序，再加一个标记数组来达到去重的目的,
     * 而本题求自增子序列，是不能对原数组进行排序的, 所以不能使用之前的去重逻辑!
     * 时间复杂度: O(n * 2^n)
     * 空间复杂度: O(n)
     */
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    Deque<Integer> path = new LinkedList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        if(nums.length == 0){
            result.add(new ArrayList());
            return result;
        }

        backtracking(nums,0);
        return result;
    }

    public void backtracking(int[] nums, int startIndex) {
        if(path.size() >= 2){
            result.add(new ArrayList(path));
            //return;  // 注意这⾥不要加return，要取树上的节点
        }
        /**
         * 一定要新建HashSet
         */
        HashSet<Integer> uset = new HashSet<>();
        for (int i = startIndex; i < nums.length; i++) {
            if (!path.isEmpty() && nums[i] < path.getLast()){
                continue;
            }
            // 记录这个元素在本层⽤过了，本层后⾯不能再⽤了
            if(!uset.add(nums[i])) { // 使⽤set来对本层元素进⾏去重 同⼀⽗节点下的同层上使⽤过的元素就不能在使⽤了
                continue;
            }
/*            if(used.contains(nums[i])){
                continue;
            }
            used.add(nums[i]);*/
            path.add(nums[i]);
            backtracking(nums, i + 1);
            path.removeLast();
        }
    }

}
