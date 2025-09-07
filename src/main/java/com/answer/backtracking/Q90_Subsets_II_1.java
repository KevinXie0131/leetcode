package com.answer.backtracking;

import java.util.*;

public class Q90_Subsets_II_1 {
    /**
     * 回溯算法：求子集问题的基础上加上了去重
     * 时间复杂度: O(n * 2^n)
     * 空间复杂度: O(n)
     */
    List<List<Integer>> result = new ArrayList<List<Integer>>();
    Deque<Integer> path = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if(nums.length == 0){
            result.add(new ArrayList());
            return result;
        }
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums); // 去重需要排序
        backtracking(nums, 0, used);
        return result;
    }

    public void backtracking(int[] nums, int startIndex, boolean[] used){
        result.add(new ArrayList(path));

        if(startIndex == nums.length){ // if(nums.length == path.size()) return; // works too
            return; // can be commented
        }
        for(int i = startIndex; i < nums.length; i++){
            // used[i - 1] == true，说明同一树枝candidates[i - 1]使用过
            // used[i - 1] == false，说明同一树层candidates[i - 1]使用过
            // 而我们要对同一树层使用过的元素进行跳过
            if(i > 0 && nums[i] == nums[i-1] && used[i - 1] == false){
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            backtracking(nums, i + 1, used);
            used[i] = false;
            path.removeLast();
        }
    }
    /**
     * 本题也可以不使用used数组来去重，因为递归的时候下一个startIndex是i+1而不是0。
     * 如果要是全排列的话，每次要从0开始遍历，为了跳过已入栈的元素，需要使用used。
     */
    List<List<Integer>> result2 = new ArrayList<List<Integer>>();
    Deque<Integer> path2 = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup2(int[] nums) { //没有使用used数组作标记
        Arrays.sort(nums); // 去重需要排序
        backtracking2(nums,0);
        return result2;
    }

    public void backtracking2(int[] nums, int startIndex) {
        result2.add(new ArrayList(path2));

        if (startIndex > nums.length) { // can be commented
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            // 而我们要对同一树层使用过的元素进行跳过.  跳过当前树层使用过的、相同的元素
            if (i > startIndex && nums[i] == nums[i - 1]) { // 注意这里使用i > startIndex
                continue;
            }
            path2.add(nums[i]);
            backtracking2(nums, i + 1);
            path2.removeLast();
        }
    }
    /**
     * Q90有去重要求, 使用set针对同一父节点本层去重，但子集问题一定要排序
     * 不排序，子集会重复
     */
    public void backtracking3(int[] nums, int startIndex) {
        result2.add(new ArrayList(path2));

        if(startIndex >  nums.length ){ // can be commented
            return;
        }
        HashSet<Integer> uset = new HashSet<>();  // 参考Q491使用HashSet本层去重
        for(int i = startIndex; i < nums.length; i++){
            if(uset.contains(nums[i])){  // 如果发现出现过就pass
                continue;
            }
            uset.add(nums[i]); // set更新元素
            path2.add(nums[i]);
            backtracking3(nums, i + 1);
            path2.removeLast();
        }
    }
    /**
     * 错误写法一: 把uset定义放到类成员位置，然后模拟回溯的样子 insert一次，erase一次。
     *            在树形结构中，uset放在类成员的位置（相当于全局变量），就把树枝的情况都记录了，不是单纯的控制某一节点下的同一层了,而是控制整棵树，包括树枝
     * 错误写法二：把uset放到类成员位置，然后每次进入单层的时候用uset.clear()
     *            uset已经是全局变量，本层的uset记录了一个元素，然后进入下一层之后这个uset（和上一层是同一个uset）就被清空了，
     *            也就是说，层与层之间的uset是同一个，那么就会相互影响。
     */
}
