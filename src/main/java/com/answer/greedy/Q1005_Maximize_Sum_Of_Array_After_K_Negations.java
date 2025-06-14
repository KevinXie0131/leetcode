package com.answer.greedy;

import java.util.*;
import java.util.Comparator;
import java.util.stream.IntStream;

public class Q1005_Maximize_Sum_Of_Array_After_K_Negations {
    /**
     * K 次取反后最大化的数组和
     * 一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
     *  选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
     * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
     * 以这种方式修改数组后，返回数组 可能的最大和 。
     * 输入：nums = [4,2,3], k = 1
     * 输出：5
     * 解释：选择下标 1 ，nums 变为 [4,-2,3] 。
     */
    public static void main(String[] args) {
        int[] nums = {3,-1,0,2};
        int k = 3;
        System.out.println(largestSumAfterKNegations(nums, k));
    }
    /**
     * Greedy Algorithm （K次取反后最大化的数组和，可以多次选择同一个索引）
     * 贪心的思路，局部最优：让绝对值大的负数变为正数，当前数值达到最大，整体最优：整个数组和达到最大。
     * 局部最优可以推出全局最优。
     *
     * 题是一个有序正整数序列，如何转变K次正负，让 数组和 达到最大。
     * 那么又是一个贪心：局部最优：只找数值最小的正整数进行反转，当前数值和可以达到最大（例如正整数数组{5, 3, 1}，反转1 得到-1 比 反转5得到的-5 大多了），全局最优：整个 数组和 达到最大
     *
     * 解题步骤为：
     *  第一步：将数组按照绝对值大小从大到小排序，注意要按照绝对值的大小
     *  第二步：从前向后遍历，遇到负数将其变为正数，同时K--
     *  第三步：如果K还大于0，那么反复转变数值最小的元素，将K用完
     *  第四步：求和
     *
     * 时间复杂度: O(nlogn)
     */
    public static  int largestSumAfterKNegations(int[] nums, int k) {
  //      Integer[] array = Arrays.stream(nums).boxed().toArray(Integer[]::new);
  //      Arrays.sort(array, Comparator.comparingInt(Math::abs));
  //      Arrays.sort(array, (o1, o2) -> Math.abs(o2) - Math.abs(o1) );

        // 将数组按照绝对值大小从大到小排序，注意要按照绝对值的大小
        nums = IntStream.of(nums) // 第一步
                .boxed()
                .sorted((o1, o2) -> Math.abs(o2) - Math.abs(o1))
                .mapToInt(Integer::intValue).toArray();

        //从前向后遍历，遇到负数将其变为正数，同时K--
        for(int i = 0; i < nums.length && k > 0; i++){  // 第二步
            if(nums[i] < 0){
                nums[i] = -nums[i];
                k--;
            }
        }
        // 如果K还大于0，那么反复转变数值最小的元素，将K用完
        if(k % 2 == 1) {
            nums[nums.length - 1] = -nums[nums.length - 1];// 第三步
        }

        return Arrays.stream(nums).sum();// 第四步
    }
    /**
     * 版本二：排序数组并贪心地尽可能将负数翻转为正数，再根据剩余的k值调整最小元素的符号，从而最大化数组的总和。
     */
    public int largestSumAfterKNegations_1(int[] nums, int k) {
        if (nums.length == 1) return nums[0];

        // 排序：先把负数处理了
        Arrays.sort(nums);

        for (int i = 0; i < nums.length && k > 0; i++) { // 贪心点, 通过负转正, 消耗尽可能多的k
            if (nums[i] < 0) {
                nums[i] = -nums[i];
                k--;
            }
        }

        // 退出循环, k > 0 || k < 0 (k消耗完了不用讨论)
        if (k % 2 == 1) { // k > 0 && k is odd：对于负数：负-正-负-正
            Arrays.sort(nums); // 再次排序得到剩余的负数，或者最小的正数
            nums[0] = -nums[0];
        }
        // k > 0 && k is even，flip数字不会产生影响: 对于负数: 负-正-负；对于正数：正-负-正

        int sum = 0;
        for (int num : nums) { // 计算最大和
            sum += num;
        }
        return sum;
    }
    /**
     * another form
     */
    public int largestSumAfterKNegations3(int[] nums, int k) {
        Arrays.sort(nums); // 1. 先排序
        // 2. 先把负数变成正数，尽可能消耗 k
        for (int i = 0; i < nums.length && k > 0; i++) {
            if (nums[i] < 0) {
                nums[i] = -nums[i];
                k--;
            }
        }
        // 3. 计算当前数组和
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 4. 处理剩余的 k，如果是奇数，需要反转当前最小值
        if (k % 2 == 1) {
            int minVal = Arrays.stream(nums).min().getAsInt(); // 找最小值
            sum -= 2 * minVal; // 相当于将最小值变负
        }
        return sum;
    }
    /**
     * 从小到大修改每个负数
     * 为了实现上面的算法，我们可以对数组进行升序排序，首先依次遍历每一个负数（将负数修改为正数），
     * 再遍历所有的数（将 0 或最小的正数进行修改）。
     *
     * 然而注意到本题中数组元素的范围为 [−100,100]，因此我们可以使用计数数组（桶）或者哈希表，直接统计每个元素出现的次数，
     * 再升序遍历元素的范围，这样就省去了排序需要的时间。
     *
     * 时间复杂度：O(n+C)，其中 n 是数组 nums 的长度，C 是数组 nums 中元素的范围，本题中 C=201。
     *            我们需要 O(n) 的时间使用桶或哈希表统计每个元素出现的次数，随后需要 O(C) 的时间对元素进行操作。
     * 空间复杂度：O(C)，即为桶或哈希表需要使用的空间。
     */
    public int largestSumAfterKNegations4(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int ans = Arrays.stream(nums).sum();

        for (int i = -100; i < 0; ++i) {
            if (freq.containsKey(i)) {
                int ops = Math.min(k, freq.get(i));
                ans += (-i) * ops * 2;
                freq.put(i, freq.get(i) - ops);
                freq.put(-i, freq.getOrDefault(-i, 0) + ops);
                k -= ops;
                if (k == 0) {
                    break;
                }
            }
        }
        if (k > 0 && k % 2 == 1 && !freq.containsKey(0)) {
            for (int i = 1; i <= 100; ++i) {
                if (freq.containsKey(i)) {
                    ans -= i * 2;
                    break;
                }
            }
        }
        return ans;
    }
    /**
     * 计数排序 / 桶排序
     * 通过使用 counter 数组，将各数字出现次数存入其中，可不排序就快速找到最小值。
     */
    public int largestSumAfterKNegations5(int[] nums, int k) {
        int[] counter = new int[201];//-100 <= A[i] <= 100,这个范围的大小是201
        for(int i = 0 ; i< nums.length; i++){
            counter[nums[i] + 100]++; //将[-100,100]映射到[0,200]上
        }

        int index = 0;
        while(k > 0){
            while(counter[index] == 0){//找到A[]中最小的数字
                index++;
            }

            if(index >= 100){// 遇到非负数，K直接根据奇偶判断，结束
                if(k % 2 == 1){ // 奇数
                    k = 0;
                } else {
                    break;
                }
            }
            counter[index]--;//此数字个数-1
            counter[200 -index]++;//其相反数个数+1
            k--;
        }

        int sum = 0;
        for(int i = 0; i < counter.length; i++){//遍历number[]求和
            sum += (i - 100) * counter[i];//i-100是数字大小,counter[j]是该数字出现次数.
        }
        return sum;
    }
    /**
     * 优先级队列
     * 维护一个小根堆，每次翻转前取出堆里面的最小数字，翻转之后的结果仍然放入堆中，以便进行下次翻转。
     * 使用优先级队列来求解，我们让所有元素入队，再取出最小的反转后再入队，计算 k 次后返回。
     */
    public int largestSumAfterKNegations7(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>();
            for (int num : nums) {
            heap.offer(num);
        }

        while (k > 0) {
            int num = heap.poll();
            if (num < 0) {
                heap.offer(-num);  // 负数反转再放回去
                k--;
            } else {
                if (k % 2 != 0) { // 正数或0
                    heap.offer(-num);    // k为奇数，反转放回去
                } else {
                    heap.offer(num);    // k为偶数，原数放回去
                }
                break;
            }
        }

        int ans = 0;
        while (!heap.isEmpty()) {
            ans += heap.poll();
        }
        return ans;
    }
}
