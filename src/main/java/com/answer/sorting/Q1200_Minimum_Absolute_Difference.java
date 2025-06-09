package com.answer.sorting;

import java.util.*;

public class Q1200_Minimum_Absolute_Difference {
    /**
     * Given an array of distinct integers arr, find all pairs of elements with the minimum absolute difference of any two elements.
     * Return a list of pairs in ascending order(with respect to pairs), each pair [a, b] follows
     *  a, b are from arr
     *  a < b
     *  b - a equals to the minimum absolute difference of any two elements in arr
     * 最小绝对差
     * 整数数组 arr，其中每个元素都 不相同。请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。
     * 每对元素对 [a,b] 如下：
     *  a , b 均为数组 arr 中的元素
     *  a < b
     *  b - a 等于 arr 中任意两个元素的最小绝对差
     *
     * Example 1:
     * Input: arr = [4,2,1,3]
     * Output: [[1,2],[2,3],[3,4]]
     * Explanation: The minimum absolute difference is 1. List all pairs with difference equal to 1 in ascending order.
     */
    public static void main(String[] args) {
        int[] arr = {1,3,6,10,15};
        List<List<Integer>> res = minimumAbsDifference1(arr);
        System.out.println(res);
    }
    /**
     * Time Limit Exceeded
     */
    static public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        Arrays.sort(arr);
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] - arr[i] < min) {
                min = arr[i + 1] - arr[i];
            }
        }

        for(int i = 0; i < arr.length - 1; i++){
            for(int j = i + 1; j < arr.length; j++){
                if(arr[j] - arr[i] == min){
                    res.add(new ArrayList<>(Arrays.asList(arr[i], arr[j])));
                }
            }
        }
        return res;
    }
    /**
     * 排序 + 一次遍历
     * 首先我们对数组 arr 进行升序排序。这样一来，拥有「最小绝对差」的元素对只能由有序数组中相邻的两个元素构成。
     * 如果 δ<min，那么说明我们遇到了更小的差值，需要更新 min，同时 res 清空并放入 arr[i] 和 arr[i+1]；
     * 如果 δ=min，那么我们只需要将 arr[i] 和 arr[i+1] 放入答案数组即可
     */
    static public List<List<Integer>> minimumAbsDifference1(int[] arr) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        Arrays.sort(arr);

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] - arr[i] < min) { // 找到新的最小差，清空原有结果
                min = arr[i + 1] - arr[i];
                res.clear();
                res.add(new ArrayList<>(Arrays.asList(arr[i], arr[i + 1])));
            } else if (arr[i + 1] - arr[i] == min) {  // 如果是最小差，记录
                res.add(new ArrayList<>(Arrays.asList(arr[i], arr[i + 1])));
            }
        }
        return res;
    }
}
