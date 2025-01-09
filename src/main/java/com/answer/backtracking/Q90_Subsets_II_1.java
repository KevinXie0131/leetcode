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

        if(startIndex == nums.length){
            return;
        }
        for(int i = startIndex; i < nums.length; i++){
            // used[i - 1] == true，说明同一树枝candidates[i - 1]使用过
            // used[i - 1] == false，说明同一树层candidates[i - 1]使用过
            // 而我们要对同一树层使用过的元素进行跳过
            if(i > 0 && nums[i] == nums[i-1] && used[i-1] == false){
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

        if (startIndex > nums.length) {
            return;
        }

        for (int i = startIndex; i < nums.length; i++) {
            // 而我们要对同一树层使用过的元素进行跳过.  跳过当前树层使用过的、相同的元素
            if (i > startIndex && nums[i] == nums[i - 1]) { // 注意这里使用i > startIndex
                continue;
            }
            path.add(nums[i]);
            backtracking2(nums, i + 1);
            path.removeLast();
        }
    }
}
