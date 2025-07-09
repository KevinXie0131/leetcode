package com.answer.hashmap;

import java.util.ArrayList;
import java.util.Collections;

public class Q170_Two_Sum_III_Data_structure_design {
    /**
     * 两数之和 III - 数据结构设计
     * 设计并实现一种数据结构，支持以下操作：
     *  add(number)：向数据结构添加一个数 number。
     *  find(value)：查找是否存在任意两数之和等于 value，如果存在返回 true，否则返回 false。
     * 请实现 TwoSum 类：
     *  TwoSum() 初始化 TwoSum 对象，数据结构为空。
     *  void add(int number) 向数据结构添加一个数 number。
     *  boolean find(int value) 如果存在任意两个数的和等于 value，返回 true，否则返回 false。
     *
     * Design and implement a TwoSum class. It should support the following operations: add and find.
     *  add - Add the number to an internal data structure.
     *  find - Find if there exists any pair of numbers which sum is equal to the value.
     *
     * Example 1:
     *  add(1); add(3); add(5);
     *  find(4) -> true
     *  find(7) -> false
     * Example 2:
     *  add(3); add(1); add(2);
     *  find(3) -> true
     *  find(6) -> false
     */
    /**
     * Your TwoSum object will be instantiated and called as such:
     * TwoSum obj = new TwoSum();
     * obj.add(number);
     * boolean param_2 = obj.find(value);
     */
    private ArrayList<Integer> nums;
    private boolean is_sorted;

    /** Initialize your data structure here. */
    public Q170_Two_Sum_III_Data_structure_design() {
        this.nums = new ArrayList<Integer>();
        this.is_sorted = false;
    }

    /** Add the number to an internal data structure.. */
    public void add(int number) {
        this.nums.add(number);
        this.is_sorted = false;
    }
    /**
     * Find if there exists any pair of numbers which sum is equal to the value.
     */
    public boolean find(int value) {
        if (!this.is_sorted) {
            Collections.sort(this.nums);
            this.is_sorted = true;
        }
        int low = 0, high = this.nums.size() - 1;
        while (low < high) {
            int twosum = this.nums.get(low) + this.nums.get(high);
            if (twosum < value)
                low += 1;
            else if (twosum > value)
                high -= 1;
            else
                return true;
        }
        return false;
    }
}
