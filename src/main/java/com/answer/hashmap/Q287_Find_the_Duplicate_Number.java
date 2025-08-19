package com.answer.hashmap;

import java.util.*;

public class Q287_Find_the_Duplicate_Number {
    /**
     * 寻找重复数
     * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
     * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
     * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
     * Given an array of integers nums containing n + 1 integers where each integer is in the range [1, n] inclusive.
     * There is only one repeated number in nums, return this repeated number.
     * You must solve the problem without modifying the array nums and using only constant extra space
     * 示例 1：
     * 输入：nums = [1,3,4,2,2]
     * 输出：2
     * 示例 2：
     * 输入：nums = [3,1,3,4,2]
     * 输出：3
     * 示例 3 :
     * 输入：nums = [3,3,3,3,3]
     * 输出：3
     */
    public static void main(String[] args) {
        int[] nums = {1,3,4,2,2};
        int res = findDuplicate_fastSlow(nums);
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
     * 只需要遍历数组把出现的元素的位置的数的对应下标取相反数，下一次再访问到负数就是重复数了，比如[1,3,2,4,3]，
     * 遍历到3时，nums[3]= -nums[3]，遍历到第二个重复的3时，此时nums[3]<0 就return 3，类似41.缺失的第一个正数/First Missing Positive
     */
    public static int findDuplicate_mark(int[] nums) {
        int len = nums.length;
        for (int num : nums) {
            int idx = Math.abs(num); // 求绝对值
            if (nums[idx] < 0) {
                return idx; // 这个下标已被访问过 则返回
            }
            nums[idx] = -nums[idx]; // 做标记, 每一次遍历就把该索引对应的数组中的元素设为负
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
   static public int findDuplicate8(int[] nums) {
        int i = 0;
        while(i < nums.length) {
            if(nums[i] == i) { //  说明此数字已在对应索引位置，无需交换，因此跳过
                i++;
                continue;
            }
            if(nums[nums[i]] == nums[i]) {
                return nums[i]; // 代表索引 nums[i] 处和索引 i 处的元素值都为 nums[i] ，即找到一组重复值，返回此值 nums[i]
            }
            int tmp = nums[i]; // 交换索引为 i 和 nums[i] 的元素值，将此数字交换至对应索引位置
            nums[i] = nums[tmp]; // 不需要额外空间，需要修改原始数组
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
    static public int findDuplicate_index_sort(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; ) {
            int num = nums[i];
            if (num == i + 1) {
                i++;
            } else if (num == nums[num - 1]) {
                return num;
            } else {
                nums[i] = nums[num - 1];
                nums[num - 1] = num;
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
        while (low <= high) {
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
                high = mid - 1;  // 下一轮搜索的区间 [left..mid]
            }
        }
        return low;
    }
    /**
     * another form
     * 时间复杂度：O(NlogN)，二分法的时间复杂度为 O(logN)，在二分法的内部，执行了一次 for 循环，时间复杂度为 O(N)，
     * 故时间复杂度为 O(NlogN)。
     */
    public int findDuplicate_8(int[] nums) {
        int n = nums.length;
        int l = 1, r = n - 1, ans = -1;
        while (l <= r) {
            int mid = (l + r) >> 1;
            int cnt = 0;
            for (int i = 0; i < n; ++i) { // 遍历数组，统计数组中小于 mid的元素个数
                if (nums[i] <= mid) {
                    cnt++;
                }
            }
            if (cnt <= mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
                ans = mid;  // 个数超出范围长度，即存在重复数
            }
        }
        return ans;
    }
    /**
     * Slow Fast Pointers 快慢指针(数组索引看成链表next指针)
     * Time: N
     * Space: 1
     * refer to 142. Linked List Cycle II
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
        // 将这个题目给的特殊的数组当作一个链表来看，数组的下标就是指向元素的指针，把数组的元素也看作指针。
        // 如 0 是指针，指向 nums[0]，而 nums[0] 也是指针，指向 nums[nums[0]].
        int slow = 0;
        int fast = 0;
        // 寻找慢指针与快指针的相遇点
        do {
            slow = nums[slow]; //慢指针走一步
            fast = nums[nums[fast]]; //快指针走两步
        } while (slow != fast); // 快慢指针相遇
        // 当 fast 和 last 相遇之后，我们设置第三个指针 finder，它从起点开始和 slow(在 fast 和 slow 相遇处)同步前进，
        // 当 finder 和 slow 相遇时，就是在环的入口处相遇，也就是重复的那个数字相遇
        // 寻找环的入口
        slow = 0; // 回到原点 慢指针从头开始走
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        // 根据数学推导，两者将在环入口相遇：
        return slow;
    }
}
