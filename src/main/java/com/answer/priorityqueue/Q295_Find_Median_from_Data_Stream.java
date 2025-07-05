package com.answer.priorityqueue;

import java.util.*;

public class Q295_Find_Median_from_Data_Stream { // Hard 困难
    /**
     * 数据流的中位数
     * 中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
     * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value, and the median is the mean of the two middle values.
     *  例如 arr = [2,3,4] 的中位数(median )是 3 。
     *  例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
     * 实现 MedianFinder 类:
     *  MedianFinder() 初始化 MedianFinder 对象。
     *  void addNum(int num) 将数据流中的整数 num 添加到数据结构中。
     *  double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10-5 以内的答案将被接受。
     *
     * 示例 1：
     * 输入 ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
     *      [[], [1], [2], [], [3], []]
     * 输出 [null, null, null, 1.5, null, 2.0]
     * 解释
     *  MedianFinder medianFinder = new MedianFinder();
     *  medianFinder.addNum(1);    // arr = [1]
     *  medianFinder.addNum(2);    // arr = [1, 2]
     *  medianFinder.findMedian(); // 返回 1.5 ((1 + 2) / 2)
     *  medianFinder.addNum(3);    // arr[1, 2, 3]
     *  medianFinder.findMedian(); // return 2.0
     */
    /**
     * 优先队列
     * 小于等于中位数的存入大顶堆，大于中位数的存入小顶堆，也就是大顶堆的元素数量等于小顶堆元素数量或者比其多一个，
     * 取的时候也是分两种情况，数量相等则取其平均值，大顶堆的元素数量等于小顶堆元素数量加一则取大顶堆的堆顶：
     */
    PriorityQueue<Integer> l = new PriorityQueue<>((a, b) -> b - a); // 大顶堆
    PriorityQueue<Integer> r = new PriorityQueue<>((a, b) -> a - b); // 小顶堆

    public void addNum(int num) {
        int s1 = l.size(), s2 = r.size();
        if (s1 == s2) {// 期望操作完达到 l 的数量为 r 多一，同时双堆维持有序
            // 如果 r 为空，说明当前插入的是首个元素，直接添加到 l 即可；
            // 如果 r 不为空，且 num <= r.peek()，说明 num 的插入位置不会在后半部分（不会在 r 中），直接加到 l 即可
            if (r.isEmpty() || num <= r.peek()) {
                l.add(num);
            } else {
                // 如果 r 不为空，且 num > r.peek()，说明 num 的插入位置在后半部分，此时将 r 的堆顶元素放到 l 中，
                // 再把 num 放到 r（相当于从 r 中置换一位出来放到 l 中）
                l.add(r.poll());
                r.add(num);
            }
        } else { //期望操作完达到 l 和 r 数量相等，同时双堆维持有序
            if (l.peek() <= num) {
                r.add(num); // 如果 num >= l.peek()，说明 num 的插入位置不会在前半部分（不会在 l 中），直接添加到 r 即可
            } else {
                // 如果 num < l.peek()，说明 num 的插入位置在前半部分，此时将 l 的堆顶元素放到 r 中，再把 num 放入 l 中
                // （相等于从 l 中替换一位出来当到 r 中）
                r.add(l.poll());
                l.add(num);
            }
        }
    }

    public double findMedian() {
        int s1 = l.size(), s2 = r.size();
        if (s1 == s2) {
            return (l.peek() + r.peek()) / 2.0;
        } else {
            return l.peek();
        }
    }
    /**
     * 用两个优先队列queMax 和 queMin  分别记录大于中位数的数和小于等于中位数的数
     * 当累计添加的数的数量为奇数时，queMin 中的数的数量比 queMax 多一个，此时中位数为 queMin 的队头
     * 当累计添加的数的数量为偶数时，两个优先队列中的数的数量相同，此时中位数为它们的队头的平均值。
     *
     * 当我们尝试添加一个数 num 到数据结构中，我们需要分情况讨论：
     * num≤max{queMin}: 此时 num 小于等于中位数，我们需要将该数添加到 queMin 中。
     *                  新的中位数将小于等于原来的中位数，因此我们可能需要将 queMin 中最大的数移动到 queMax 中。
     * num>max{queMin}: 此时 num 大于中位数，我们需要将该数添加到 queMax 中。
 *                      新的中位数将大于等于原来的中位数，因此我们可能需要将 queMax 中最小的数移动到 queMin 中。
     * 特别地，当累计添加的数的数量为 0 时，我们将 num 添加到 queMin 中
     */
    PriorityQueue<Integer> queMin;
    PriorityQueue<Integer> queMax;

    public void MedianFinder1() {
        queMin = new PriorityQueue<Integer>((a, b) -> (b - a));
        queMax = new PriorityQueue<Integer>((a, b) -> (a - b));
    }

    public void addNum1(int num) {
        if (queMin.isEmpty() || num <= queMin.peek()) {
            queMin.offer(num);
            if (queMax.size() + 1 < queMin.size()) {
                queMax.offer(queMin.poll());
            }
        } else {
            queMax.offer(num);
            if (queMax.size() > queMin.size()) {
                queMin.offer(queMax.poll());
            }
        }
    }

    public double findMedian1() {
        if (queMin.size() > queMax.size()) {
            return queMin.peek();
        }
        return (queMin.peek() + queMax.peek()) / 2.0;
    }
    /**
     * 小根堆保存大的那部分元素，这样小根堆堆顶就是大的元素中最小的，同理可得大根堆堆顶就是小的元素中最大的，
     * 这2的堆顶的2个元素就是，就是数组之后最中间的2个元素
     *
     * 用两个优先队列maxHeap和minHeap分别记录小于中位数的数和大于等于中位数的数。
     * 小顶堆，存大的那一半（那么堆顶元素就是大半里面最小的那个，即最接近中心的）
     * 大顶堆，存小的那一半（那么堆顶元素就是小半里面最大的那个，即最接近中心的）
     * 当累计添加的数的数量为奇数时，minHeap中的数的数量比maxHeap多一个，此时中位数为minHeap的队头。
     * 当累计添加的数的数量为偶数时，两个优先队列中的数的数量相同，此时中位数为它们的队头的平均值。
     * 特别地，当累计添加的数的数量为0时，我们将num添加到minHeap中。
     */
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // 小顶堆，存大的那一半（那么堆顶元素就是大半里面最小的那个，即最接近中心的）
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((x, y) -> y - x);// 大顶堆，存小的那一半（那么堆顶元素就是小半里面最大的那个，即最接近中心的）

    public void addNum2(int num) {
        if (minHeap.isEmpty() || num > minHeap.peek()) {
            minHeap.offer(num);
        } else {
            maxHeap.offer(num);
        }
        // -- 维护中位数数量，大堆和小堆堆顶元素是有序的，那堆元素数量超出预期，就移除并放到另一个堆里面
        if (minHeap.size() > maxHeap.size() + 1) {
            maxHeap.offer(minHeap.poll());
        } else if (maxHeap.size() > minHeap.size()) {
            minHeap.offer(maxHeap.poll());
        }
    }

    public double findMedian2() {
        if (minHeap.size() == maxHeap.size()) {
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        } else {
            return minHeap.peek();
        }
    }
    /**
     * left 是最大堆，right 是最小堆。
     * 如果当前有奇数个元素，中位数是 left 的堆顶。
     * 如果当前有偶数个元素，中位数是 left 的堆顶和 right 的堆顶的平均值。
     */
    private final PriorityQueue<Integer> left = new PriorityQueue<>((a, b) -> b - a); // 最大堆
    private final PriorityQueue<Integer> right = new PriorityQueue<>(); // 最小堆
    // 保证 left 的大小和 right 的大小尽量相等。规定：在有奇数个数时，left 比 right 多 1 个数。
    // 保证 left 的所有元素都小于等于 right 的所有元素。
    public void addNum3(int num) {
        if (left.size() == right.size()) { // 如果当前 left 的大小和 right 的大小相等
            right.offer(num); // 无论 num 是大是小，都可以先把 num 加到 right 中，然后把 right 的最小值从 right 中去掉，并添加到 left 中。
            left.offer(right.poll());
        } else { // 如果当前 left 比 right 多 1 个数
            left.offer(num); // 无论 num 是大是小，都可以先把 num 加到 left 中，然后把 left 的最大值从 left 中去掉，并添加到 right 中。
            right.offer(left.poll());
        }
    }

    public double findMedian3() {
        if (left.size() > right.size()) {
            return left.peek();
        }
        return (left.peek() + right.peek()) / 2.0;
    }
    /**
     * anothe form 大顶堆 + 小顶堆
     */
    private final PriorityQueue<Integer> left1 = new PriorityQueue<>((a, b) -> b - a);
    private final PriorityQueue<Integer> right1= new PriorityQueue<>();

    public void addNum4(int num) {
        if (left1.size() <= right1.size()) {
            right1.offer(num);
            left1.offer(right1.poll());
        } else if (left1.size() > right1.size())  {
            left1.offer(num);
            right1.offer(left1.poll());
        }
    }

    public double findMedian4() {
        if (left1.size() > right1.size()) {
            return left1.peek();
        } else if (left1.size() < right1.size()) {
            return right1.peek();
        }
        return (left1.peek() + right1.peek()) / 2.0;
    }

}
