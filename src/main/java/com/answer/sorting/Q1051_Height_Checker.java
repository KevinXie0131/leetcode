package com.answer.sorting;

import java.util.*;

public class Q1051_Height_Checker {
    /**
     * 高度检查器
     * 学校打算为全体学生拍一张年度纪念照。根据要求，学生需要按照 非递减 的高度(non-decreasing order by height)顺序排成一行。
     * 排序后的高度情况用整数数组 expected 表示，其中 expected[i] 是预计排在这一行中第 i 位的学生的高度（下标从 0 开始）。
     * 给你一个整数数组 heights ，表示 当前(current order)学生站位 的高度情况。heights[i] 是这一行中第 i 位学生的高度（下标从 0 开始）(0-indexed)。
     * 返回满足 heights[i] != expected[i] 的 下标数量 (number of indices)。
     * 示例：
     *
     * 输入：heights = [1,1,4,2,1,3]
     *  输出：3
     *  解释：高度：[1,1,4,2,1,3]
 *           预期：[1,1,1,2,3,4]
     *       下标 2 、4 、5 处的学生高度不匹配。
     * 示例 2：
     *  输入：heights = [5,1,2,3,4]
     *  输出：5
     *  解释：高度：[5,1,2,3,4]
 *           预期：[1,2,3,4,5]
     *       所有下标的对应学生高度都不匹配。
     * 示例 3：
     *  输入：heights = [1,2,3,4,5]
     *  输出：0
     *  解释：高度：[1,2,3,4,5]
     *       预期：[1,2,3,4,5]
     *       所有下标的对应学生高度都匹配。
     */
    public static void main(String[] args) {
        int[] heights = {1,1,4,2,1,3};
        System.out.println(heightChecker3(heights));
    }
    /**
     * 基于比较的排序
     * 我们可以直接将数组 heights 复制一份（记为 expected），并对数组 expected 进行排序。
     * 待排序完成后，我们统计 heights[i]=expected[i] 的下标数量即可。
     * 时间复杂度：O(nlogn)
     */
    public int heightChecker(int[] heights) {
        int n = heights.length, ans = 0;
    //    int[] expected = new int[n];
     //   System.arraycopy(heights, 0, expected, 0, n);
        int[] expected = heights.clone();
        Arrays.sort(expected);

        for (int i = 0; i < n; ++i) {
            if (heights[i] != expected[i]) {
                ++ans;
            }
        }
        return ans;
    }
    /**
     * 计数排序
     * 1 <= heights[i] <= 100 注意到本题中学生的高度小于等于 100，因此可以使用计数排序。
     * 在进行计数排序时，我们可以直接使用一个长度为 101 的数组，也可以先对数组 heights 进行一次遍历，找出最大值 m，从而使用一个长度为 m+1 的数组。
     * 当计数排序完成后，我们可以再使用一个长度为 n 的数组，显式地存储排序后的结果。为了节省空间，我们也直接在计数排序的数组上进行遍历
     *
     * 时间复杂度：O(n+C)，其中 n 是数组 heights 的长度，C 是数组 heights 中的最大值。即为计数排序需要的时间。
     * 空间复杂度：O(C)，即为计数排序需要的空间。
     */
    static public int heightChecker1(int[] heights) {
        int max = Arrays.stream(heights).max().getAsInt();
        int[] counter = new int[max + 1];
        for (int h : heights) {
            ++counter[h];
        }
        // 放出的时候如果放出的值不等于要求的顺序的身高(即h)
        // 那么结果++，当然这里如果存在不存在的身高比如
        // [1,1,1,3,4]的时候，m=2不存在那么在放入cnt记录的时候
        // cnt[2]自然等于0，子循环就不会进入了，也不会增加答案
        int idx = 0, ans = 0;
        for (int h = 1; h <= max; ++h) {
            for (int j = 1; j <= counter[h]; ++j) {
                if (heights[idx] != h) {
                    ++ans;
                }
                ++idx;
            }
        }
        return ans;
    }
    /**
     * 计数算法
     * 我们真的需要排序吗？首先我们其实并不关心排序后得到的结果，我们想知道的只是在该位置上，与最小的值是否一致
     * 题目中已经明确了值的范围 1 <= heights[i] <= 100
     * 这是一个在固定范围内的输入，比如输入： [1,1,4,2,1,3]
     * 输入中有 3 个 1,1 个 2，1 个 3 和 1 个 4，3 个 1 肯定会在前面，依次类推
     * 所以，我们需要的仅仅只是计数而已
     *
     * 会桶排序就很容易理解
     */
    static public int heightChecker3(int[] heights) {
        int[] arr = new int[101]; // 值的范围是1 <= heights[i] <= 100，因此需要1,2,3,...,99,100，共101个桶
        // 遍历数组heights，计算每个桶中有多少个元素，也就是数组heights中有多少个1，多少个2，。。。，多少个100
        // 将这101个桶中的元素，一个一个桶地取出来，元素就是有序的
        for (int height : heights) {
            arr[height]++;
        }
        int count = 0;
        for (int i = 1, j = 0; i < arr.length; i++) {
            // arr[i]，i就是桶中存放的元素的值，arr[i]是元素的个数
            // arr[i]-- 就是每次取出一个，一直取到没有元素，成为空桶
            while (arr[i]-- > 0) {
                // 从桶中取出元素时，元素的排列顺序就是非递减的，然后与heights中的元素比较，如果不同，计算器就加1
                if (heights[j++] != i) {
                    count++;
                }
            }
        }
        return count;
    }
}
