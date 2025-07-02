package com.answer.binarysearch;

import java.util.*;

public class Q658_Find_K_Closest_Elements {
    /**
     * 找到 K 个最接近的元素
     * 给定一个 排序好 的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
     * iven a sorted integer array arr, two integers k and x, return the k closest integers to x in the array.
     * The result should also be sorted in ascending order.
     * 整数 a 比整数 b 更接近 x 需要满足：
     * An integer a is closer to x than an integer b if:
     *   |a - x| < |b - x| 或者
     *   |a - x| == |b - x| 且 a < b
     * 示例 1：
     *  输入：arr = [1,2,3,4,5], k = 4, x = 3
     *  输出：[1,2,3,4]
     * 示例 2：
     *  输入：arr = [1,1,2,3,4,5], k = 4, x = -1
     *  输出：[1,1,2,3]
     */
    public static void main(String[] args) {
        findClosestElements_2( new int[]{1}, 1, 1);
    }
    /**
     * Two pointers 排除法（双指针）
     * 一个一个删，因为数组有序，且返回的是连续升序子数组，所以 每一次删除的元素一定位于边界 ；
     * 一共 7 个元素，要保留 3 个元素，因此要删除 4 个元素；
     * 因为要删除的元素都位于边界，于是可以使用 双指针 对撞的方式确定保留的区间」。
     */
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        int len = arr.length;
        int left = 0;
        int right = len - 1;
        int count = len - k;
        // 如果左边界与 x 的差值的绝对值较小，删除右边界；
        // 如果右边界与 x 的差值的绝对值较小，删除左边界；
        // 如果左、右边界与 x 的差值的绝对值相等，删除右边界。
        while(count > 0){
            if(x - arr[left] <= arr[right] - x){
                right--;
            }else{
                left++;
            }
            count--; //要删除的个数
        }

        List<Integer> res = new ArrayList<Integer>();
        for(int i = left; i <= right; i++){    //从左到右添加元素
            res.add(arr[i]);// 找到了「合适的」左边界的下标，从左边界开始数 k 个数，返回
        }
        return res;
    }
    /**
     * Approach 3: Binary Search To Find The Left Bound 二分查找最优区间的左边界
     *If the element at arr[mid] is closer to x, then move the right pointer. If the element at arr[mid + k] is closer to x
     * At the end of the binary search, we have located the leftmost index for the final answer. Return the subarray starting at this index that contains k elements.
     */
   static public List<Integer> findClosestElements_2(int[] arr, int k, int x) {
        List<Integer> list = new ArrayList<>();
        int n = arr.length;
        int left = 0;
        int right = n - k; // 「最优区间的左边界」的下标的搜索区间为 [0, size - k]
        while(left <= right){
            int mid = (left + right) >>> 1;
            // 尝试从长度为 k + 1 的连续子区间删除一个元素
            // 从而定位左区间端点的边界值
            if( mid + k > n - 1 || x - arr[mid] <= arr[mid + k] - x){ // mid + k > n - 1 is for new int[]{1}, 1, 1
               right = mid - 1;    // 下一轮搜索区间是 [mid + 1..right]
            } else {
                left = mid + 1;     // 下一轮搜索区间是 [left..mid + 1]
            }
        }
        for (int i = left; i < left + k; i++) {
            list.add(arr[i]);
        }
        return list;
    }
    /**
     * Approach 1: Sort With Custom Comparator 排序
     */
    public List<Integer> findClosestElements_1(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<Integer>();
        for(int i = 0; i <= arr.length - 1; i++){
            res.add(arr[i]);
        }

        Collections.sort(res, (a, b) -> {
            if (Math.abs(a - x) != Math.abs(b - x)) {
                return Math.abs(a - x) - Math.abs(b - x);
            } else {
                return a - b;
            }
        });
        /**
         *  Collections.sort(sortedArr, (num1, num2) -> Math.abs(num1 - x) - Math.abs(num2 - x));
         */
        List<Integer> list = res.subList(0, k);
        Collections.sort(list);
        return list;
    }
    /**
     * 二分查找 + 双指针
     */
    public List<Integer> findClosestElements_6(int[] arr, int k, int x) {
        int right = binarySearch(arr, x); // 将数组 arr 分成两部分，前一部分所有元素 [0,left] 都小于 x，后一部分所有元素 [right,n−1] 都大于等于 x，left 与 right 都可以通过二分查找获得
        int left = right - 1; // left 和 right 指向的元素都是各自部分最接近 x 的元素
        while (k-- > 0) { // 可以通过比较 left 和 right 指向的元素获取整体最接近 x 的元素
            if (left < 0) { // 如果 left 或 right 已经越界，那么不考虑对应部分的元素。
                right++;
            } else if (right >= arr.length) {
                left--;
            } else if (x - arr[left] <= arr[right] - x) { // 如果 x−arr[left]≤arr[right]−x，那么将 left 减一，否则将 right 加一。
                left--;
            } else {
                right++;
            }
        }
        List<Integer> ans = new ArrayList<Integer>();
        for (int i = left + 1; i < right; i++) {
            ans.add(arr[i]); // 区间 [left+1,right−1] 的元素就是我们所要获得的结果，返回答案既可
        }
        return ans;
    }

    public int binarySearch(int[] arr, int x) {
        int low = 0, high = arr.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] >= x) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    /**
     * Approach 2: Sliding Window 滑动窗口
     * 主要利用数组 有序 的特性，通过维护长度为 k 的窗口不断向右移动，找出整体与 x 最接近的那一段。
     */
    public List<Integer> findClosestElements_3(int[] arr, int k, int x) {
        List<Integer> list = new ArrayList<>();
        int n = arr.length;
        int left = 0;
        int right = 0;
        int sum = 0;
        int res = Integer.MAX_VALUE;
        int idx = 0;
        // 1.计算left和right上的值与x差值的绝对
        // 2.判断right与left之间的差距是否大于k，如果大于k，比较步骤1中的差值，从而决定移动left还是移动right
        // 3.确定left和right，输出数组
        while (right < n) {
            sum += Math.abs(arr[right] - x);
            if (right - left + 1 == k) {
                if (sum < res) {
                    res = sum;
                    idx = left;
                }
                sum -= Math.abs(arr[left] - x);
                left++;
            }
            right++;
        }

        for (int i = idx; i < idx + k; i++) {
            list.add(arr[i]);
        }
        return list;
    }
    /**
     * 二分查找 + 双指针
     */
    public List<Integer> findClosestElements_7(int[] arr, int k, int x) {
        int n = arr.length;
        int start = lowerBound(arr, x + 1) - 1; // int j = lowerBound(arr, x) - 1;// works too
        // 先找到第一个值小于等于x的坐标 start;
        // 令 l = start, r = start + 1, 和 x 最接近的元素一定在 l 和 r 之间产生；
        // 依次相向移动，过程中取两者和 x 更接近的那个元素添加到结果中，直到结果集大小达到 k；
        int l = start;
        int r = start + 1;
        while(k > 0){
            if(l >= 0 && r < n){
                if(Math.abs(arr[l] - x) <= Math.abs(arr[r] - x)){ // 若x - arr[left] <= arr[right] - x，则移动右指针，否则移动左指针
                    l--;
                }else{
                    r++;
                }
            }else if(l >= 0){
                l--;
            }else{
                r++;
            }
            k--;
        }
        List<Integer> ans = new ArrayList<>();
        for(int i = l+1; i < r; i++){
            ans.add(arr[i]);
        }
        return ans;
    }
    // 最优解法：只寻找左边界即可
    public int lowerBound(int[] arr, int target){
        int l = 0;
        int r = arr.length - 1;
        while(l <= r){
            int mid = (l + r) / 2;
            if(arr[mid] < target){
                l = mid + 1;
            }else{
                r = mid - 1;
            }
        }
        return l;
    }
    /**
     * Another form of sliding window 二分 + 滑动窗口
     */
    public List<Integer> findClosestElements_4(int[] arr, int k, int x) {
        List<Integer> result = new ArrayList<Integer>();
        // Base case
        if (arr.length == k) {
            for (int i = 0; i < k; i++) {
                result.add(arr[i]);
            }
            return result;
        }
        // Binary search to find the closest element  //利用二分找到≥x的第一个数
        int left = 0;
        int right = arr.length;
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (arr[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        // Initialize our sliding window's bounds
        right = left; //因为left指向的数字很有可能不等于x 所以我们要在left-1 --- left里去找
        left = left - 1;
        // While the window size is less than k
        while (right - left - 1 < k) {  //固定窗口大小为k
            // Be careful to not go out of bounds
            if (left == -1) {
                right += 1;
                continue;
            }
            // Expand the window towards the side with the closer number
            // Be careful to not go out of bounds with the pointers
            if (right == arr.length || Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
                left -= 1;
            } else {
                right += 1;
            }
        }
        // Build and return the window
        for (int i = left + 1; i < right; i++) {
            result.add(arr[i]);
        }
        return result;
    }
}
