package com.answer.hashmap;

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
}
