package com.answer.priorityqueue;

import java.util.*;

public class Q703_Kth_Largest_Element_in_a_Stream {
    /**
     * 数据流中的第 K 大元素
     * 设计一个找到数据流中第 k 大元素的类（class）(keep track of the kth highest test score from applicants in real-time. )。
     * 注意是排序后的第 k 大元素，不是第 k 个不同的元素。
     * You are tasked to implement a class which, for a given integer k, maintains a stream of test scores and continuously
     * returns the kth highest test score after a new score has been submitted. More specifically, we are looking for
     * the kth highest score in the sorted list of all scores.
     *
     * 请实现 KthLargest 类：
     *  KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
     *  int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
     *
     * 示例 1：
     * 输入：["KthLargest", "add", "add", "add", "add", "add"]
     *      [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
     * 输出：[null, 4, 5, 5, 8, 8]
     * 解释：
     *  KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
     *  kthLargest.add(3); // 返回 4
     *  kthLargest.add(5); // 返回 5
     *  kthLargest.add(10); // 返回 5
     *  kthLargest.add(9); // 返回 8
     *  kthLargest.add(4); // 返回 8
     *
     * 示例 2：
     * 输入：["KthLargest", "add", "add", "add", "add"]
     *      [[4, [7, 7, 7, 7, 8, 3]], [2], [10], [9], [9]]
     * 输出：[null, 7, 7, 7, 8]
     * 解释：
     *  KthLargest kthLargest = new KthLargest(4, [7, 7, 7, 7, 8, 3]);
     *  kthLargest.add(2); // 返回 7
     *  kthLargest.add(10); // 返回 7
     *  kthLargest.add(9); // 返回 7
     *  kthLargest.add(9); // 返回 8
     *
     */
    public static void main(String[] args) {
        Q703_Kth_Largest_Element_in_a_Stream object = new Q703_Kth_Largest_Element_in_a_Stream(3, new int[]{4,5,8,2});
        System.out.println( object.add(3));
        System.out.println( object.add(5));
        System.out.println( object.add(10));
        System.out.println( object.add(9));
        System.out.println( object.add(4));
    }
    /**
     * Approach: Heap - use a min-heap (min means that the heap will remove/find the smallest element, a max heap is the same thing but for the largest element)
     * Time:
     *   initial: O(nlogk)
     *   add: O(logk)
     * Space: O(k)
     * 为什么使用小根堆？
     * 因为我们需要在堆中保留数据流中的前K大元素，使用小根堆能保证每次调用堆的 pop() 函数时，从堆中删除的是堆中的最小的元素（堆顶）
     *
     * 时间复杂度： 初始化时间复杂度为：O(nlogk) ，其中 n 为初始化时 nums 的长度；
     *             单次插入时间复杂度为：O(logk)。
     * 空间复杂度：O(k)。需要使用优先队列存储前 k 大的元素
     */
    PriorityQueue<Integer> heap = null;
    int k = 0;
    /**
     * 经典 TopK: 本题是我们求在一个数据流中的第 K 大元素
     * 本题的操作步骤如下：
     *  使用大小为 K的小根堆，在初始化的时候，保证堆中的元素个数不超过 K。
     *  在每次 add()的时候，将新元素 push()到堆中，如果此时堆中的元素超过了 K，那么需要把堆中的最小元素（堆顶）pop()出来。
     *  此时堆中的最小元素（堆顶）就是整个数据流中的第 K大元素。
     *
     * 为什么能保证堆顶元素是第 K 大元素？
     * 因为小根堆中保留的一直是堆中的前 K 大的元素，堆的大小是 K，所以堆顶元素是第 K 大元素。
     */
    public Q703_Kth_Largest_Element_in_a_Stream(int k, int[] nums) {
        this.heap = new PriorityQueue<>();
        this.k = k;

        for(int n : nums){
            heap.offer(n);
        }
    }

    public int add(int val) {
        this.heap.offer(val);
        while(this.heap.size() > this.k){
            this.heap.poll();
        }
        return this.heap.peek();
    }
    /**
     * 另一种形式 时间复杂度： 初始化时间复杂度为：O(klogk)
     */
    public void KthLargest1(int k, int[] nums) {
        this.heap = new PriorityQueue<>();
        this.k = k;
        // 堆排序解法 - 维护一个大小为k的小根堆, 若当前要插入的数小于等于堆顶元素, 则丢弃; 否则插入, 可以直接插入到堆顶,
        // 在进行调整. 堆顶元素保存了第k大元素.
        for(int n : nums){
            if( this.heap.size() < k){
                this.heap.offer(n); // 当数据不满K位，则直接加入队列
            }
            else if(this.heap.peek() < n){
                this.heap.poll();
                this.heap.offer(n);
            }
        }
    }

    public int add1(int val) {
        if( this.heap.size() < k){
            this.heap.offer(val); // 如果此时堆的 size 还没到 k，直接将 val 入堆
        }
        else if(this.heap.peek() < val){   // 插入元素大于队首时，说明现在队首已经不再是前k大中的元素了，将其移除，插入新的数据
            this.heap.poll();
            this.heap.offer(val);
        }
        return this.heap.peek();
    }
    /**
     * 优先队列 / 大根堆
     * Time Limit Exceeded
     * 10 / 12 testcases passed
     */
    PriorityQueue<Integer> maxHeap = null;
    List<Integer> list = new ArrayList<>();
    int k1 = 0;

    public void KthLargest2(int k, int[] nums) {
        this.k1 = k;
        maxHeap = new PriorityQueue<>((a, b) -> b - a);
        for(int num : nums){
            maxHeap.offer(num);
        }
    }

    public int add2(int val) {
        maxHeap.offer(val);
        int index = 1;

        while(index++ < this.k1){
            list.add(maxHeap.poll());
        }
        int res = maxHeap.peek();

        for(int i : list){
            maxHeap.offer(i);
        }
        list.clear();

        return res;
    }
}
