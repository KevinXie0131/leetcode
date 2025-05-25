package com.answer.array;

import java.util.*;

public class Q448_Find_All_Numbers_Disappeared_in_an_Array {
    /**
     * 找到所有数组中消失的数字
     * 给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。
     * 示例 1：
     *  输入：nums = [4,3,2,7,8,2,3,1]
     *  输出：[5,6]
     */
    /**
     * return an array of all the integers in the range [1, n] that do not appear in nums.
     * Follow up: Could you do it without extra space and in O(n) runtime?
     *              You may assume the returned list does not count as extra space.
     * 进阶：你能在不使用额外空间且时间复杂度为 O(n) 的情况下解决这个问题吗? 你可以假定返回的数组不算在额外空间内。
     */
    public static void main(String[] args) {
        int[] nums = {4,3,2,7,8,2,3,1};
      //  int[] nums = {1,1};
        List<Integer> res = findDisappearedNumbers_4(nums);
        System.out.println(res);
    }
    /**
     * Use array as counter
     * 重点1：数组长度是 n；
     * 重点2：每个数字的范围都是 [1,n]。
     * 由于数字范围均在 [1,n] 中，我们也可以用一个长度为 n 的数组来代替哈希表
     */
    public static List<Integer> findDisappearedNumbers(int[] nums) {
        int len = nums.length;
        List<Integer> res = new ArrayList<Integer>();
        int[] array = new int[len + 1];

        for(int i = 0; i <= nums.length - 1; i++){
            array[nums[i]]--;
        }
        for(int i = 1; i <= nums.length; i++){
            if(array[i] == 0){
                res.add(i);
            }
        }
        return res;
    }
    /**
     * Approach 1: Using Hash Map
     * 可以用一个哈希表记录数组 nums 中的数字，由于数字范围均在 [1,n] 中，记录数字后我们再利用哈希表检查 [1,n] 中的每一个数是否出现，从而找到缺失的数字。
     */
    public static List<Integer> findDisappearedNumbers_1(int[] nums) {
        int len = nums.length;
        List<Integer> res = new ArrayList<Integer>();
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i <= nums.length - 1; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for(int i = 1; i <= nums.length; i++){
            if(!map.containsKey(i)){
                res.add(i);
            }
        }
        return res;
    }
    /**
     * Approach 2: O(1) Space In Place Modification Solution
     * 将数组元素对应为索引的位置加n
     * 遍历加n后的数组，若数组元素值小于等于n，则说明数组下标值不存在，即消失的数字
     */
    public static List<Integer> findDisappearedNumbers_2(int[] nums) {
        int len = nums.length;
        List<Integer> res = new ArrayList<>();

        for(int i = 0; i <= nums.length - 1; i++){
            int x = (nums[i] - 1) % len;
            nums[x] += len;
        }
        for(int i = 0; i <= nums.length - 1; i++){
            if(nums[i] <= len){
                res.add(i + 1);
            }
        }
        return res;
    }
    /**
     * O(1) Space In Place
     * 当前元素是 nums[i]，那么我们把第 nums[i]−1 位置的元素 乘以 -1，表示这个该位置出现过。
     * 当然如果 第 nums[i]−1 位置的元素已经是负数了，表示 nums[i] 已经出现过了，就不用再把第 nums[i]−1 位置的元素乘以 -1。
     * 最后，对数组中的每个位置遍历一遍，如果 i 位置的数字是正数，说明 i 未出现过。
     */
    public static List<Integer> findDisappearedNumbers_4(int[] nums) {
        int len = nums.length;
        List<Integer> res = new ArrayList<Integer>();
        // 遍历下数组的元素，对对应的索引位置的元素作标记
        for(int i = 0; i <= nums.length - 1; i++){
            int x = Math.abs(nums[i]) - 1;  // 由于数组的元素有可能被*-1，所以取绝对值
            if(nums[x] > 0){
                nums[x] = -nums[x];
            }
        }
        for(int i = 0; i <= nums.length - 1; i++){   // 寻找没有标记的索引位置
            if(nums[i] > 0){
                res.add(i + 1); //将索引转化为对应的元素
            }
        }
        return res;
    }
}
