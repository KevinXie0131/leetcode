package com.answer.priorityqueue;

import java.util.*;

public class Q373_Find_K_Pairs_with_Smallest_Sums {
    /**
     * 查找和最小的 K 对数字
     * 给定两个以 非递减顺序排列 的整数数组 nums1 和 nums2 , 以及一个整数 k 。
     * You are given two integer arrays nums1 and nums2 sorted in non-decreasing order and an integer k.
     * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
     * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
     * Return the k pairs (u1, v1), (u2, v2), ..., (uk, vk) with the smallest sums.
     *
     * 示例 1:
     *  输入: nums1 = [1,7,11], nums2 = [2,4,6], k = 3
     *  输出: [1,2],[1,4],[1,6]
     *  解释: 返回序列中的前 3 对数：
     *      [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
     * 示例 2:
     *  输入: nums1 = [1,1,2], nums2 = [1,2,3], k = 2
     *  输出: [1,1],[1,1]
     *  解释: 返回序列中的前 2 对数：
     *      [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
     *
     * nums1 and nums2 both are sorted in non-decreasing order. / nums1 和 nums2 均为 升序排列
     */
    public static void main(String[] args) {
        int[] nums1 = {1,2,4,5,6};
        int [] nums2 = {3,5,7,9};
        int k = 3;
        System.out.println(kSmallestPairs_1(nums1, nums2, k));
    }
    /**
     * PriorityQueue 优先队列
     * 时间复杂度：O(klogk)
     * 空间复杂度：O(k)
     */
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        PriorityQueue<int[]> heap = new PriorityQueue<>(k, (o1, o2)->{
            return nums1[o1[0]] + nums2[o1[1]] - (nums1[o2[0]] +  nums2[o2[1]]);
        });
        int m = nums1.length;
        int n = nums2.length;
        for(int i = 0; i < Math.min(k, m); i++){ // 至多 k 个
            heap.offer(new int[]{i, 0});
        }

        while(k > 0 && !heap.isEmpty()){
            int[] pos = heap.poll();
            res.add(Arrays.asList(nums1[pos[0]], nums2[pos[1]]));
            int index = pos[1];
            if(index < n - 1){
                heap.offer(new int[]{pos[0], index + 1});
            }
            k--;
        }
        return res;
    }
    /**
     * another form
     */
    public static List<List<Integer>> kSmallestPairs_a(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>(k);
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int m = nums1.length;
        int n = nums2.length;
        // 把 nums1 的所有索引入队，nums2 的索引初始时都是 0
        // 优化：最多入队 k 个就可以了，因为提示中 k 的范围较小，这样可以提高效率
        for(int i = 0; i < Math.min(k, m); i++){ // 至多 k 个
            heap.offer(new int[]{nums1[i] + nums2[0], i, 0});
        }
        // 最多弹出 k 次
        while(k > 0 && !heap.isEmpty()){
            int[] pos = heap.poll();
            int x = pos[1];
            int y = pos[2];
            res.add(Arrays.asList(nums1[x], nums2[y]));
            if(y < n - 1){
                heap.offer(new int[]{nums1[x] + nums2[y + 1], x, y + 1});// 将 index2 加 1 之后继续入队
            }
            k--;
        }
        return res;
    }
    /**
     * 多路归并
     * 每次需要从 n 个元素中找出最小的元素，需要找 k 次，所以时间复杂度为 O(klogn)
     * 所以为了更优的时间复杂度，尽量让 nums1 长度更短；如果 nums1 长度更长，我们就交换两个数组的位置
     */
    private boolean flag = true; // 标志是否交换了位置 true : 未交换；false : 交换了

    public List<List<Integer>> kSmallestPairs0(int[] nums1, int[] nums2, int k) {
        int n = nums1.length, m = nums2.length;
        // 判断是否需要交换顺序
        if (n > m && !(flag = false)){
            return kSmallestPairs0(nums2, nums1, k);
        }
        // 注意：队列中存储的只是下标
        // 按照「两数和」递增排列
        Queue<int[]> q = new PriorityQueue<>((a, b) -> nums1[a[0]] + nums2[a[1]] - nums1[b[0]] - nums2[b[1]]);
        // 加入头节点
        // 这里有一个技巧：如果 k < n，那么一开始只需要往队列中添加前 k 个元素即可
        // 后面的 n - k 个元素肯定比前面 k 个元素大，所以加入没有意义
        for (int i = 0; i < Math.min(n, k); i++){
            q.offer(new int[]{i, 0});
        }
        List<List<Integer>> ans = new ArrayList<>();
        while (ans.size() < k && !q.isEmpty()) {
            // 弹出队顶元素，即最小元素
            int[] cur = q.poll();
            int a = cur[0], b = cur[1];
            ans.add(new ArrayList<Integer>(){{
                add(flag ? nums1[a] : nums2[b]);
                add(flag ? nums2[b] : nums1[a]);
            }});
            // 如果 b + 1 < m 表示该条链条后面还有元素，可以继续加入队列中
            if (b + 1 < m){
                q.offer(new int[]{a, b + 1});
            }
        }
        return ans;
    }
    /**
     * Binary Search
     */
    static int[] nums1, nums2;
    static int n, m;
    public static List<List<Integer>> kSmallestPairs_1(int[] n1, int[] n2, int k) {
        nums1 = n1; nums2 = n2;
        n = nums1.length; m = nums2.length;
        List<List<Integer>> ans = new ArrayList<>();
        int l = nums1[0] + nums2[0];
        int r = nums1[n - 1] + nums2[m - 1];
        while (l < r) {
            int mid = (int)(0L + l + r >> 1);
            if (check(mid, k)) {
                r = mid;
            }else{
                l = mid + 1;
            }
        }
        int x = r;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (nums1[i] + nums2[j] < x) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums1[i]);
                    temp.add(nums2[j]);
                    ans.add(temp);
                } else {
                    break;
                }
            }
        }
        for (int i = 0; i < n && ans.size() < k; i++) {
            int a = nums1[i], b = x - a;
            int c = -1, d = -1;
            l = 0; r = m - 1;
            while (l < r) {
                int mid = (int)(0L + l + r >> 1);
                if (nums2[mid] >= b) {
                    r = mid;
                }else{
                    l = mid + 1;
                }
            }
            if (nums2[r] != b) {
                continue;
            }
            c = r;
            l = 0; r = m - 1;
            while (l < r) {
                int mid = (int)(0L + l + r + 1) >> 1;
                if (nums2[mid] <= b) {
                    l = mid;
                }else{
                    r = mid - 1;
                }
            }
            d = r;
            for (int p = c; p <= d && ans.size() < k; p++) {
                List<Integer> temp = new ArrayList<>();
                temp.add(a);
                temp.add(b);
                ans.add(temp);
            }
        }
        return ans;
    }

    static boolean check(int x, int k) {
        int ans = 0;
        for (int i = 0; i < n && ans < k; i++) {
            for (int j = 0; j < m && ans < k; j++) {
                if (nums1[i] + nums2[j] <= x) {
                    ans++;
                }else{
                    break;
                }
            }
        }
        return ans >= k;
    }
}
