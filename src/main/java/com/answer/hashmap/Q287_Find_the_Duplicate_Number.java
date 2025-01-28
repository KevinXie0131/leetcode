package com.answer.hashmap;

import java.util.*;

public class Q287_Find_the_Duplicate_Number {
    public static void main(String[] args) {
        int[] nums = {1,3,4,2,2};
        int res = findDuplicate_index_sort(nums);
        System.out.println(res);
    }
    /**
     * Sorting 排序法
     * Time: NlogN + N
     * Space: logN
     */
    public int findDuplicate_1(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        for (int i = 1; i < len; i++) {
            if (nums[i] == nums[i - 1]) {
                return nums[i];
            }
        }
        return len;
    }
    /**
     * Use array as counter使用数组计数(需要额外空间，不修改原始数组)
     * Time: N
     * Space: N
     */
    public int findDuplicate_2(int[] nums) {
        int len = nums.length;
        int[] cnt = new int[len];
        for (int i = 0; i < len; i++) {
            cnt[nums[i]]++;
            if (cnt[nums[i]] > 1) {
                return nums[i];
            }
        }
        return len;
    }
    /**
     * Use HashSet哈希集合
     * Time: N
     * Space: N
     */
    public int findDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int n : nums) {
            if (!set.add(n)) {
                return n;
            }
        }
        return -1;
    }
    /**
     * Marked and modifying the array 标记法(不需要额外空间，需要修改原始数组 )
     * Time: N
     * Space: 1
     */
    public static int findDuplicate_mark(int[] nums) {
        int len = nums.length;
        for (int num : nums) {
            int idx = Math.abs(num); // 求绝对值
            if (nums[idx] < 0) {
                return idx; // 这个下标已被访问过 则返回
            }
            nums[idx] = -nums[idx]; // 做标记
        }
        return len;
    }
    /**
     * 原地交换: 数组元素的 索引 和 值 是 一对多 的关系
     * 可遍历数组并通过交换操作，使元素的 索引 与 值 一一对应（即 nums[i]=i ）。因而，就能通过索引映射对应的值，起到与字典等价的作用。
     * 遍历中，第一次遇到数字 x 时，将其交换至索引 x 处；而当第二次遇到数字 x 时，一定有 nums[x]=x ，此时即可得到一组重复数字。
     *  若 nums[i]=i ： 说明此数字已在对应索引位置，无需交换，因此跳过。
     *  若 nums[nums[i]]=nums[i] ： 代表索引 nums[i] 处和索引 i 处的元素值都为 nums[i] ，即找到一组重复值，返回此值 nums[i] 。
     *  否则： 交换索引为 i 和 nums[i] 的元素值，将此数字交换至对应索引位置。
     */
    public int findDuplicate8(int[] nums) {
        int i = 0;
        while(i < nums.length) {
            if(nums[i] == i) {
                i++;
                continue;
            }
            if(nums[nums[i]] == nums[i]) {
                return nums[i];
            }
            int tmp = nums[i]; // 不需要额外空间，需要修改原始数组
            nums[i] = nums[tmp];
            nums[tmp] = tmp;
        }
        return -1;
    }
    /**
     * Index Sort and modifying the array 索引排序
     * Time: N
     * Space: 1
     * 数组排序之后，每个数组元素的值是其索引值 index+1 ，那么就可以进行如此操作：
     *   如果 nums[i]==i+1，说明已经排好序了，那么跳过，i++；
     *   如果 nums[i]==nums[nums[i]−1] ，说明在正确的索引已经有一个值了，那么这个值就是重复的元素；
     *   上述均不满足，交换 nums[i] 和 nums[nums[i]−1] 的值。
     */
    public static int findDuplicate_index_sort(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; ) {
            int n = nums[i];
            if (n == i + 1) {
                i++;
            } else if (n == nums[n - 1]) {
                return n;
            } else {
                nums[i] = nums[n - 1];
                nums[n - 1] = n;
            }
        }
        return 0;
    }
    /**
     * Binary Search 二分查找
     * Time: NlogN
     * Space: 1
     * 因为在区间 [1,n] 内存在重复的数。
     *
     * 我们先累计大小在 [1, n/2] 之间的数字个数，如果重复数在这个范围内，则个数 > n/2，否则可确定区间(n/2 ,n] 内存在重复数。
     * 即可通过二分查找求解。
     *
     * 可以用一个具体的例子来理解：如果遍历一遍输入数组，统计小于 等于 4 的元素的个数，如果小于等于 4 的元素的个数 严格 大于 4 ，
     * 说明重复的元素一定出现在整数区间 [1..4]
     */
    public static int findDuplicate_bs(int[] nums) {
        int len = nums.length;
        int low = 0;
        int high = len - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            int cnt = 0; // 计数
            for (int i = 0; i < len; i++) {
                if (nums[i] <= mid) {
                    cnt++; // nums 中小于等于 mid 的元素的个数
                }
            }
            // 个数超出范围长度，即存在重复数
            if (cnt <= mid) {
                low = mid + 1;  // 下一轮搜索的区间 [mid + 1..right]
            } else {
                high = mid;  // 下一轮搜索的区间 [left..mid]
            }
        }
        return low;
    }
    /**
     * Slow Fast Pointers 快慢指针(数组索引看成链表next指针)
     * Time: N
     * Space: 1
     * 使用 142 题的思想来解决此题的关键是要理解如何将输入的数组看作为链表。
     * 从理论上讲，数组中如果有重复的数，那么就会产生多对一的映射，这样，形成的链表就一定会有环路了，
     * 综上
     *  1.数组中有一个重复的整数 <==> 链表中存在环
     *  2.找到数组中的重复整数 <==> 找到链表的环入口
     *
     * 至此，问题转换为 142 题。那么针对此题，快、慢指针该如何走呢。根据上述数组转链表的映射关系，可推出
     *   142 题中慢指针走一步 slow = slow.next ==> 本题 slow = nums[slow]
     *   142 题中快指针走两步 fast = fast.next.next ==> 本题 fast = nums[nums[fast]]
     */
    public static int findDuplicate_fastSlow(int[] nums) {
        int slow = 0;
        int fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast); // 快慢指针相遇

        slow = 0; // 回到原点
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}
