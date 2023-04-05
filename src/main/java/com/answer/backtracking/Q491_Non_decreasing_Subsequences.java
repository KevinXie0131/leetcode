package com.answer.backtracking;

import java.util.*;

public class Q491_Non_decreasing_Subsequences {

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
        HashSet<Integer> uset = new HashSet<>();
        for (int i = startIndex; i < nums.length; i++) {
            if (!path.isEmpty() && nums[i] < path.getLast()){
                continue;
            }
            // 记录这个元素在本层⽤过了，本层后⾯不能再⽤了
            if(!uset.add(nums[i])) { // 使⽤set来对本层元素进⾏去重 同⼀⽗节点下的同层上使⽤过的元素就不能在使⽤了
                continue;
            }
            path.add(nums[i]);
            backtracking(nums, i + 1);
            path.removeLast();
        }
    }

}
