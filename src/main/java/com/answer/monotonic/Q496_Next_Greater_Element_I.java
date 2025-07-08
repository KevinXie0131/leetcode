package com.answer.monotonic;

import java.util.*;

public class Q496_Next_Greater_Element_I {
    /**
     * The next greater element of some element x in an array is the first greater element that is to the right of x in the same array.
     * You are given two distinct 0-indexed integer arrays nums1 and nums2, where nums1 is a subset of nums2.
     * For each 0 <= i < nums1.length, find the index j such that nums1[i] == nums2[j] and determine the next greater element of nums2[j] in nums2. If there is no next greater element, then the answer for this query is -1.
     * Return an array ans of length nums1.length such that ans[i] is the next greater element as described above.
     * nums1 中数字 x 的 下一个更大元素 是指 x 在 nums2 中对应位置 右侧 的 第一个 比 x 大的元素。
     * 下一个更大元素
     * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，下标从 0 开始计数，其中nums1 是 nums2 的子集。
     * 对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2[j] 的 下一个更大元素 。
     * 如果不存在下一个更大元素，那么本次查询的答案是 -1 。
     * 返回一个长度为 nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。
     * All integers in nums1 and nums2 are unique.
     * All the integers of nums1 also appear in nums2.
     */
    public static void main(String[] args) {
        /**
         * 输入：nums1 = [4,1,2], nums2 = [1,3,4,2].
         * 输出：[-1,3,-1]
         * 解释：nums1 中每个值的下一个更大元素如下所述：
         * - 4 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。
         * - 1 ，用加粗斜体标识，nums2 = [1,3,4,2]。下一个更大元素是 3 。
         * - 2 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。
         */
        int[] nums1 = {4,1,2};
        int[]nums2 = {1,3,4,2};
        int[] output = nextGreaterElement_0(nums1, nums2);
        System.out.println(Arrays.toString(output));
    }
    /**
     * 暴力 时间复杂度：O(mn)
     */
    public int[] nextGreaterElement0(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] res = new int[m];
        for (int i = 0; i < m; ++i) {
            int j = 0;
            while (j < n && nums2[j] != nums1[i]) {
                j++;
            }
            int k = j + 1;
            while (k < n && nums2[k] < nums2[j]) {
                k++;
            }
            res[i] = k < n ? nums2[k] : -1;
        }
        return res;
    }
    /**
     * 使用stack做brute force
     */
   static public int[] nextGreaterElement_0(int[] nums1, int[] nums2) {
        Deque<Integer> stack = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        for(int num : nums2){
            stack.push(num);
        }

        Deque<Integer> temp = new LinkedList<>(); ;
        for(int num : nums1) {
            int max = -1;
            boolean isFound = false;
            while(!stack.isEmpty() && !isFound){
                int top = stack.pop();
                if(top > num){ // 找到了比该数值大的值
                    max = top;
                } else if(top == num){ // 找到了该数值
                    isFound = true;
                }
                temp.push(top);
            }
            result.add(max);
            while(!temp.isEmpty()){ // 从temp中的数值将stack复原
                stack.push(temp.pop());
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }
    /**
     * 使用Stack和HashMap（简化一些）
     * 单调栈 + 哈希表
     * 将题目分解为两个子问题：
     *  第 1 个子问题：如何更高效地计算 nums2中每个元素右边的第一个更大的值；
     *  第 2 个子问题：如何存储第 1 个子问题的结果。
     *
     * 只要遍历到比栈顶元素更大的数，就意味着栈顶元素找到了答案，记录答案，并弹出栈顶。
     * 时间复杂度降到 n+m
     *
     * 单调栈一些特点：
     *  单调递减栈：栈内元素从栈底到栈顶是递减的（严格或非严格递减）。常用于找“下一个更大元素”。
     *  单调递增栈：栈内元素从栈底到栈顶是递增的（严格或非严格递增）。常用于找“下一个更小元素”。
     *  栈内存储索引还是值：一般存储索引（下标），方便在结果数组中直接更新
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        Stack<Integer> stack = new Stack<>();
        Map<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < nums2.length; i++) { // 把nums2中每个元素的下一个最大值存入hashmap
      /*      while (!stack.isEmpty() && nums2[i] > nums2[stack.peek()]) { //如果比栈顶元素大，则存入hashmap
                int prevIndex = stack.pop();
                map.put(nums2[prevIndex], nums2[i]);
            }
            stack.push(i);*/
           while (!stack.isEmpty() && nums2[i] > stack.peek()) { //把数值存入stack
                int top = stack.pop(); // top 是栈顶的下一个更大元素, 既然栈顶已经算出答案，弹出
                map.put(top, nums2[i]); // 当前数字 > 栈顶，代表栈顶对应下一个更大的数字就是当前数字，则将该组数字对应关系，记录到哈希表。
            }
            stack.push(nums2[i]); // 当前数字 < 栈顶，当前数字压入栈，供后续数字判断使用。
        }

        for (int i = 0 ; i < nums1.length; i++) {
            result[i] = map.getOrDefault(nums1[i], -1); // 在hashmap中寻找nums1中每个元素
        }
        return result;
    }
    /**
     * 使用单调栈: 栈顶到栈底的顺序，要从小到大，也就是保持栈里的元素为递增顺序。只要保持递增，才能找到右边第一个比自己大的元素。
     *
     * 情况一：当前遍历的元素T[i]小于栈顶元素T[st.top()]的情况
     *    此时满足递增栈（栈顶到栈底的顺序），所以直接入栈。
     * 情况二：当前遍历的元素T[i]等于栈顶元素T[st.top()]的情况
     *    如果相等的话，依然直接入栈，因为我们要求的是右边第一个比自己大的元素，而不是大于等于！
     * 情况三：当前遍历的元素T[i]大于栈顶元素T[st.top()]的情况
     *    此时如果入栈就不满足递增栈了，这也是找到右边第一个比自己大的元素的时候。
     *    判断栈顶元素是否在nums1里出现过，（注意栈里的元素是nums2的元素），如果出现过，开始记录结果。
     *    记录结果这块逻辑有一点小绕，要清楚，此时栈顶元素在nums2数组中右面第一个大的元素是nums2[i]（即当前遍历元素）
     */
    /**
     * 单调栈 / 把数值存入stack
     * 单调栈实际上就是栈，只是利用了一些巧妙的逻辑，使得每次新元素入栈后，栈内的元素都保持有序（单调递增或单调递减）。
     * 听起来有点像堆（heap）？不是的，单调栈用途不太广泛，只处理一种典型的问题，叫做 Next Greater Element
     * 实际上算法的复杂度只有 O(n)
     *
     * 逆向遍历时，栈中存的是有可能成为前一个元素右边界的元素
     * 正向遍历时，栈中存到是还未找到右边界的元素
     */
    public int[] nextGreaterElement2a(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums1.length; i++){
            map.put(nums1[i], i);
        }

        int[] result = new int[nums1.length];
        Arrays.fill(result, -1); // result数组如果某位置没有被赋值，那么就应该是是-1，所以就初始化为-1。
        Deque<Integer> stack = new LinkedList<>();

        for(int i = 0; i < nums2.length; i++){
            // 栈不为空且当前元素大于栈顶元素, 说明当前元素是栈顶元素的下一个更大元素
            // while循环表示当前元素是栈中所有已存元素的下一个更大元素
            while(!stack.isEmpty() && stack.peek() < nums2[i] ){
                int top = stack.pop();
                if(map.containsKey(top)){
                    int value = map.get(top);
                    result[value] = nums2[i];
                }
            }
            stack.push(nums2[i]);   // 将当前考察元素入栈，看后面是否有其下一个更大元素
        }
        return result;
    }
    /**
     * 版本2: 单调栈中存放的元素最好是下标而不是值，因为有的题目需要根据下标计算，这样泛化性更好
     */
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums1.length; i++){
            map.put(nums1[i], i);
        }

        int[] result = new int[nums1.length];
        Arrays.fill(result, -1); // result数组如果某位置没有被赋值，那么就应该是是-1，所以就初始化为-1。
        Deque<Integer> stack = new LinkedList<>();

        for(int i = 0; i < nums2.length; i++){
            while(!stack.isEmpty() && nums2[stack.peek()] < nums2[i] ){
                int key = stack.peek();
                if(map.containsKey(nums2[key])){
                    int value = map.get(nums2[key]);
                    result[value] = nums2[i];
                }
                stack.pop();
            }
            stack.push(i);
        }
        return result;
    }
    /**
     * 版本1
     */
    public int[] nextGreaterElement1(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[nums1.length];
        Arrays.fill(res,-1);

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0 ; i< nums1.length ; i++){
            hashMap.put(nums1[i], i);
        }
        stack.push(0);

        for (int i = 1; i < nums2.length; i++) {
            if (nums2[i] <= nums2[stack.peek()]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                    if (hashMap.containsKey(nums2[stack.peek()])){
                        Integer index = hashMap.get(nums2[stack.peek()]);
                        res[index] = nums2[i];
                    }
                    stack.pop();
                }
                stack.push(i);
            }
        }
        return res;
    }
}
