package com.answer.hashmap;

import java.util.*;

public class Q599_Minimum_Index_Sum_of_Two_Lists {
    /**
     * 两个列表的最小索引总和
     * 给定两个字符串数组 list1 和 list2，找到 索引和最小的公共字符串。
     * 公共字符串 是同时出现在 list1 和 list2 中的字符串。
     * 具有 最小索引和的公共字符串 是指，如果它在 list1[i] 和 list2[j] 中出现，那么 i + j 应该是所有其他 公共字符串 中的最小值。
     * 返回所有 具有最小索引和的公共字符串。以 任何顺序 返回答案。
     * Given two arrays of strings list1 and list2, find the common strings with the least index sum.
     * A common string is a string that appeared in both list1 and list2.
     * A common string with the least index sum is a common string such that if it appeared at list1[i] and list2[j] then i + j should be the minimum value among all the other common strings.
     * Return all the common strings with the least index sum. Return the answer in any order.
     *
     * 示例 1:
     * 输入: list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["Piatti", "The Grill at Torrey Pines", "Hungry Hunter Steakhouse", "Shogun"]
     * 输出: ["Shogun"]
     * 解释: 唯一的公共字符串是 “Shogun”。
     * 示例 2:
     * 输入:list1 = ["Shogun", "Tapioca Express", "Burger King", "KFC"]，list2 = ["KFC", "Shogun", "Burger King"]
     * 输出: ["Shogun"]
     * 解释: 具有最小索引和的公共字符串是 “Shogun”，它有最小的索引和 = (0 + 1) = 1。
     */
    /**
     * 哈希表
     * 如果 list2[i] 在哈希表中：
     *  索引之和等于 min，将 list2[i] 加入 res；
     *  索引之和小于 min，更新 min，清空 res，将 list2[i] 加入 res；
     *  索引之和大于 min，跳过。
     */
    public String[] findRestaurant(String[] list1, String[] list2) {
        // 只需要加这三行代码
        // 以考虑只保存长度更小的那个到哈希表
        if (list1.length > list2.length) {
            return findRestaurant(list2, list1); // 优化
        }

        Map<String, Integer> map = new HashMap<>();
        for(int i = 0; i < list1.length; i++){
            map.put(list1[i], i);
        }

        List<String> res = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < list2.length; i++){
            if (i > min) {
                break;  // 优化
            }

            if(map.containsKey(list2[i])){
                int index = i + map.get(list2[i]);
                if(index < min){ // //寻找更小索引和. 如果该索引和比最小索引和小，则清空结果, 该索引和作为最小索引和
                    min = index;
                    res.clear(); // res = new ArrayList<>();
                    res.add(list2[i]);
                } else if (index == min){ // 如果该索引和等于最小索引和，则直接加入结果中。
                    res.add(list2[i]);
                }
            }
        }
        return res.toArray(new String[0]);
        // return res.toArray(new String[res.size()]); // works too
        // return res.toArray(new String[]); // doesn't work
    }
}
