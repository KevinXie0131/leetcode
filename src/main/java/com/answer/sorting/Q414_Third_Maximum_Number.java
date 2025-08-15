package com.answer.sorting;

import java.util.*;

public class Q414_Third_Maximum_Number {
    /**
     * Given an integer array nums, return the third distinct maximum number in this array. If the third maximum does not exist, return the maximum number.
     * 第三大的数
     * 给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。
     * Follow up: Can you find an O(n) solution? 进阶：你能设计一个时间复杂度 O(n) 的解决方案吗？
     *
     * 示例 1：
     *  输入：[3, 2, 1]
     *  输出：1
     *  解释：第三大的数是 1 。
     * 示例 2：
     *  输入：[1, 2]
     *  输出：2
     *  解释：第三大的数不存在, 所以返回最大的数 2 。
     * 示例 3：
     *  输入：[2, 2, 3, 1]
     *  输出：1
     *  解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1
     */
    public static void main(String[] args) {
        int[] nums = {2,2,3,1,4};
        System.out.println(thirdMax1(nums));
    }
    /**
     * 方法一：排序
     * 将数组从大到小排序后，从头开始遍历数组，通过判断相邻元素是否不同，来统计不同元素的个数。如果能找到三个不同的元素，
     * 就返回第三大的元素，否则返回最大的元素。
     */
    public int thirdMax(int[] nums) {
        nums = Arrays.stream(nums).boxed()
                .sorted(Collections.reverseOrder())
                .mapToInt(Integer::intValue) // .mapToInt(i -> i) // works too
                .toArray();

        int diff = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1] && ++diff == 3) { // 此时 nums[i] 就是第三大的数
                return nums[i];
            }
        }
        return nums[0];
    }
    /**
     * 方法二：有序集合
     * 用一个有序集合来维护数组中前三大的数。具体做法是每遍历一个数，就将其插入有序集合，若有序集合的大小超过 3，就删除集合中的最小元素。
     * 这样可以保证有序集合的大小至多为 3，且遍历结束后，若有序集合的大小为 3，其最小值就是数组中第三大的数
     *
     * TreeSet（红黑树） 时间复杂度： O(n * log3) == O(n)
     */
    static public int thirdMax1(int[] nums) {
        TreeSet<Integer> s = new TreeSet<Integer>(); // 维护一个只有3个元素的TreeSet
        for (int num : nums) {
            s.add(num);
            if (s.size() > 3) {
                s.remove(s.first());
            }
        }
        // return s.first()
        return s.size() == 3 ? s.first() : s.last(); // set.last() 里面最大的元素
    }
    /**
     * Set 去重 + 排序
     * 先使用 Set 对重复元素进行去重，然后对去重后的元素进行排序，并返回第三大的元素。
     */
    static public int thirdMax4(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int x : nums){
            set.add(x);
        }
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        return list.size() < 3 ? list.get(list.size() - 1) : list.get(list.size() - 3);
    }
    /**
     * 有限变量 + 遍历
     * 可以遍历数组，并用三个变量 a、b 和 c 来维护数组中的最大值、次大值和第三大值，以模拟方法二中的插入和删除操作
     *
     * 经典的找数组次大值的做法是使用两个变量 a 和 b 分别存储遍历过程中的最大值和次大值
     * 可以使用 a、b 和 c 三个变量来代指「最大值」、「严格次大值」和「严格第三大值」。
     *   x>a，说明最大值被更新，将原本的「最大值」和「次大值」往后顺延为「次大值」和「第三大值」，并用 x 更新 a；
     *   x<a 且 x>b，说明次大值被更新，将原本的「次大值」往后顺延为「第三大值」，并用 x 更新 b；
     *   x<b 且 x>c，说明第三大值被更新，使用 x 更新 c。
     */
    public int thirdMax2(int[] nums) {
        long a = Long.MIN_VALUE, b = Long.MIN_VALUE, c = Long.MIN_VALUE;
        for (long num : nums) {
            if (num > a) {
                c = b;
                b = a;
                a = num;
            } else if (a > num && num > b) {
                c = b;
                b = num;
            } else if (b > num && num > c) {
                c = num;
            }
        }
        return c == Long.MIN_VALUE ? (int) a : (int) c;
    }
    /**
     * another form
     */
    public int thirdMax5(int[] nums) {
        long a = Long.MIN_VALUE, b = Long.MIN_VALUE, c = Long.MIN_VALUE;
        for (long num : nums) {
            if (num == a || num == b || num == c) {
                continue;
            }
            if (num > a) {
                c = b;
                b = a;
                a = num;
            } else if ( num > b) {
                c = b;
                b = num;
            } else if ( num > c) {
                c = num;
            }
        }
        return c == Long.MIN_VALUE ? (int) a : (int) c;

    }
}
