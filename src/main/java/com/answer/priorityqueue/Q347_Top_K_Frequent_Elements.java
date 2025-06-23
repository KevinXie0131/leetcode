package com.answer.priorityqueue;

import java.util.*;
import java.util.stream.*;

public class Q347_Top_K_Frequent_Elements {
    /**
     * 前 K 个高频元素
     * 给你一个整数数组 nums 和一个整数 k ，请你返回其中出现频率前 k 高的元素。你可以按 任意顺序 返回答案。
     * Given an integer array nums and an integer k, return the k most frequent elements. You may return the answer in any order.
     *
     * 示例 1:
     *  输入: nums = [1,1,1,2,2,3], k = 2
     *  输出: [1,2]
     * 示例 2:
     *  输入: nums = [1], k = 1
     *  输出: [1]
     * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的 It is guaranteed that the answer is unique.
     * 进阶：你所设计算法的时间复杂度 必须 优于 O(n log n) ，其中 n 是数组大小。
     */
    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,3,4,4,4,5,5,5};
        int k = 2;

        int[] result = topKFrequent0(nums, k);
        System.out.println(Arrays.toString(result));
    }
    /**
     * 要统计元素出现频率 (首先统计元素出现的频率，这一类的问题可以使用map来进行统计)
     * 对频率排序 (然后是对频率进行排序，这里我们可以使用一种 容器适配器就是优先级队列。)
     * 找出前K个高频元素
     *
     * 优先级队列 就是一个披着队列外衣的堆. 堆是一棵完全二叉树，树中每个结点的值都不小于（或不大于）其左右孩子的值。
     * 如果父亲结点是大于等于左右孩子就是大顶堆，小于等于左右孩子就是小顶堆。
     * 大顶堆（堆头是最大元素），小顶堆（堆头是最小元素）
     *
     * 所以我们要用小顶堆，因为要统计最大前k个元素，只有小顶堆每次将最小的元素弹出，最后小顶堆里积累的才是前k个最大元素。
     * 时间复杂度: O(nlogk) (本题就要使用优先级队列来对部分频率进行排序。 注意这里是对部分数据进行排序而不需要对所有数据排序！) 空间复杂度: O(n)
     */
    public static int[] topKFrequent0(int[] nums, int k) {
        // 优先级队列，为了避免复杂 api 操作，pq 存储数组
        // lambda 表达式设置优先级队列从大到小存储 o1 - o2 为从小到大，o2 - o1 反之
        PriorityQueue<int[]> queue = new PriorityQueue<>((e1, e2)->e1[1] - e2[1]);
        Map<Integer, Integer> map = new HashMap<>();  // 记录元素出现次数 key为数组元素值,val为对应出现次数
        int[] result = new int[k]; // 答案数组为 k 个元素

        for(int i : nums){
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            queue.offer(new int[]{entry.getKey(), entry.getValue()}); // 将 kv 转化成数组
        /*    int[] tmp = new int[2];
            tmp[0] = x.getKey();
            tmp[1] = x.getValue();
            pq.offer(tmp);*/
            if(queue.size() > k){ // 下面的代码是根据小根堆实现的，我只保留优先队列的最后的k个，只要超出了k我就将最小的弹出，剩余的k个就是答案
                queue.poll();
            }
        }
        for(int i = 0; i < k; i++){
            // res[i] = pq.poll()[0]; // 获取优先队列里的元素
            int[] res = queue.poll();
            result[i] = res[0];
        }
        return result;
    }
    /**
     * 解法1：基于大顶堆实现 max heap
     * Comparator接口说明:
     *   返回负数，形参中第一个参数排在前面；返回正数，形参中第二个参数排在前面
     *   对于队列：排在前面意味着往队头靠
     *   对于堆（使用PriorityQueue实现）：从队头到队尾按从小到大排就是最小堆（小顶堆），
     *                                  从队头到队尾按从大到小排就是最大堆（大顶堆）--->队头元素相当于堆的根节点
     */
    public static int[] topKFrequent(int[] nums, int k) {
        int[] result = new int[k];
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        /**
         * 根据map的value值，构建于一个大顶堆（o1 - o2: 小顶堆， o2 - o1 : 大顶堆）
         * 在优先队列中存储二元组(num, cnt),cnt表示元素值num在数组中的出现次数
         * 出现次数按从队头到队尾的顺序是从大到小排,出现次数最多的在队头(相当于大顶堆)
         */
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((o1, o2) -> o2.getValue() - o1.getValue());
/*        PriorityQueue<int[]> pq = new PriorityQueue<>((pair1, pair2) -> pair2[1] - pair1[1]);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {//大顶堆需要对所有元素进行排序
            pq.add(new int[]{entry.getKey(), entry.getValue()});
        }*/
        for(Map.Entry<Integer, Integer> entry : entries){
            queue.offer(entry);
        }

        for(int i = k-1; i >= 0; i--){  //依次弹出小顶堆,先弹出的是堆的根,出现次数少,后面弹出的出现次数多
            result[i] = queue.poll().getKey();
        }
        return result;
    }
    /**
     * 解法2：基于小顶堆实现 min heap
     */
    public static int[] topKFrequent_1(int[] nums, int k) {
        int[] result = new int[k];
        HashMap<Integer, Integer> map = new HashMap<>(); //key为数组元素值,val为对应出现次数

        for(int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        /**
         * 根据map的value值，构建于一个大顶堆（o1 - o2: 小顶堆， o2 - o1 : 大顶堆）
         * 在优先队列中存储二元组(num, cnt),cnt表示元素值num在数组中的出现次数
         * 出现次数按从队头到队尾的顺序是从小到大排,出现次数最低的在队头(相当于小顶堆)
         */
        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((o1, o2) -> o1.getValue() - o2.getValue());

        for(Map.Entry<Integer, Integer> entry : entries){ //小顶堆只需要维持k个元素有序
            if(queue.size() == k){
                if(queue.peek().getValue() < entry.getValue()){
                    queue.poll();
                    queue.offer(entry);
                }
            } else {
                queue.offer(entry);
            }
        }
      /*  for (Map.Entry<Integer, Integer> entry : map.entrySet()) { //小顶堆只需要维持k个元素有序
            if (pq.size() < k) { //小顶堆元素个数小于k个时直接加
                pq.add(new int[]{entry.getKey(), entry.getValue()});
            } else {
                if (entry.getValue() > pq.peek()[1]) { //当前元素出现次数大于小顶堆的根结点(这k个元素中出现次数最少的那个)
                    pq.poll(); //弹出队头(小顶堆的根结点),即把堆里出现次数最少的那个删除,留下的就是出现次数多的了
                    pq.add(new int[]{entry.getKey(), entry.getValue()});
                }
            }
        }*/
        for(int i = k-1; i >= 0; i--){
            result[i] = queue.poll().getKey(); //依次弹出小顶堆,先弹出的是堆的根,出现次数少,后面弹出的出现次数多
        }
        return result;
    }

    /**
     * TreeMap
     */
    public static int[] topKFrequent_2(int[] nums, int k) {
        // 统计每个数字出现的次数
        Map<Integer, Integer> counterMap = IntStream.of(nums).boxed().collect(Collectors.toMap(e -> e, e -> 1, Integer::sum));
        // 定义二叉搜索树：key 是数字出现的次数，value 是出现了key次的数字列表。
        TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>();
        // 维护一个有 k 个数字的二叉搜索树：
        // 不足 k 个直接将当前数字加入到树中；否则判断当前树中的最小次数是否小于当前数字的出现次数，若是，则删掉树中出现次数最少的一个数字，将当前数字加入树中。
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : counterMap.entrySet()) {
            int num = entry.getKey();
            int cnt = entry.getValue();
            if (count < k) {
                treeMap.computeIfAbsent(cnt, ArrayList::new).add(num);
                count++;
            } else {
                Map.Entry<Integer, List<Integer>> firstEntry = treeMap.firstEntry();
                if (cnt > firstEntry.getKey()) {
                    treeMap.computeIfAbsent(cnt, ArrayList::new).add(num);
                    List<Integer> list = firstEntry.getValue();
                    if (list.size() == 1) {
                        treeMap.pollFirstEntry();
                    } else {
                        list.remove(list.size() - 1);
                    }
                }
            }
        }
        // 构造返回结果
        int[] res = new int[k];
        int idx = 0;
        for (List<Integer> list : treeMap.values()) {
            for (int num : list) {
                res[idx++] = num;
            }
        }
        return res;
    }
}
