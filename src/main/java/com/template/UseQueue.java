package com.template;

import java.util.*;

public class UseQueue {
    /**
     * Queue，也就是队列，通常遵循先进先出（FIFO）的原则，新元素插入到队列的尾部，访问元素返回队列的头部。
     */
    public static void main(String[] args) {
        /**
         * ArrayDeque 是一个基于数组实现的双端队列，为了满足可以同时在数组两端插入或删除元素的需求，数组必须是循环的，
         * 也就是说数组的任何一点都可以被看作是起点或者终点。
         * 因为是循环数组，所以 head 不一定从是从 0 开始，tail 也不一定总是比 head 大。
         */
        // 创建一个ArrayDeque
        ArrayDeque<String> myDeque = new ArrayDeque<>();
        // 添加元素
        myDeque.add("沉默");
        myDeque.add("王二");
        myDeque.add("陈清扬");
        // 删除元素
        myDeque.remove("王二");
        // 修改元素
        myDeque.remove("沉默");
        myDeque.add("沉默的力量");
        // 查找元素
        System.out.println(myDeque);
        boolean hasChenQingYang = myDeque.contains("陈清扬");
        System.out.println("deque包含陈清扬吗？" + hasChenQingYang);

        myDeque.push("A");
        myDeque.pop();
        myDeque.addFirst("A");
        myDeque.addLast("A");
        myDeque.offer("A");
        myDeque.offerFirst("A");
        myDeque.offerLast("A");
        myDeque.peek();
        myDeque.peekFirst();
        myDeque.peekLast();
        myDeque.getFirst();
        myDeque.getLast();
        myDeque.poll();
        myDeque.pollFirst();
        myDeque.pollLast();
        myDeque.removeFirst();
        myDeque.removeLast();
        myDeque.isEmpty();
        myDeque.clear();
        myDeque.size();
        /**
         * LinkedList 一般应该归在 List 下，只不过，它也实现了 Deque 接口，可以作为队列来使用。
         * 等于说，LinkedList 同时实现了 Stack、Queue、PriorityQueue 的所有功能。
         *
         * LinkedList 和 ArrayDeque 都是 Java 集合框架中的双向队列（deque），它们都支持在队列的两端进行元素的插入和删除操作。
         * 不过，LinkedList 和 ArrayDeque 在实现上有一些不同：
         * 1. 底层实现方式不同：LinkedList 是基于链表实现的，而 ArrayDeque 是基于数组实现的。
         * 2. 随机访问的效率不同：由于底层实现方式的不同，LinkedList 对于随机访问的效率较低，时间复杂度为 O(n)，
         *    而 ArrayDeque 可以通过下标随机访问元素，时间复杂度为 O(1)。
         * 3. 迭代器的效率不同：LinkedList 对于迭代器的效率比较低，因为需要通过链表进行遍历，时间复杂度为 O(n)，
         *    而 ArrayDeque 的迭代器效率比较高，因为可以直接访问数组中的元素，时间复杂度为 O(1)。
         * 4. 内存占用不同：由于 LinkedList 是基于链表实现的，它在存储元素时需要额外的空间来存储链表节点，因此内存占用相对较高，
         *    而 ArrayDeque 是基于数组实现的，内存占用相对较低。
         *
         * 如果需要在双向队列的两端进行频繁的插入和删除操作，并且需要随机访问元素，可以考虑使用 ArrayDeque；
         * 如果需要在队列中间进行频繁的插入和删除操作，可以考虑使用 LinkedList。
         */
        //  在使用 LinkedList 作为队列时，可以使用 offer() 方法将元素添加到队列的末尾，使用 poll() 方法从队列的头部删除元素。
        Deque<String> queue = new LinkedList<>();
        // 添加元素
        queue.offer("沉默");
        queue.offer("王二");
        queue.offer("陈清扬");
        System.out.println(queue); // 输出 [沉默, 王二, 陈清扬]
        // 删除元素
        queue.poll();
        System.out.println(queue); // 输出 [王二, 陈清扬]
        // 修改元素：LinkedList 中的元素不支持直接修改，需要先删除再添加
        String first = queue.poll(); // 王二
        System.out.println(first);
        queue.offer("王大二");
        System.out.println(queue); // 输出 [陈清扬, 王大二]
        // 查找元素：LinkedList 中的元素可以使用 get() 方法进行查找
     //   System.out.println(queue.get(0)); // 输出 陈清扬
        System.out.println(queue.contains("沉默")); // 输出 false
        // 查找元素：使用迭代器的方式查找陈清扬
        // 使用迭代器依次遍历元素并查找
        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            if (element.equals("陈清扬")) {
                System.out.println("找到了：" + element);
                break;
            }
        }
        /**
         * PriorityQueue 是一种优先级队列，它的出队顺序与元素的优先级有关，执行 remove 或者 poll 方法，返回的总是优先级最高的元素
         */
        // 创建一个 PriorityQueue 对象
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        // 添加元素
        priorityQueue.offer("xxx");
        priorityQueue.offer("n沉默");
        priorityQueue.offer("g王二");
        priorityQueue.offer("a陈清扬");
        priorityQueue.offer("bbb");
        System.out.println(priorityQueue); // 输出 [a陈清扬, bbb, n沉默, xxx, g王二]
        // 删除元素
        priorityQueue.poll();
        System.out.println(priorityQueue); // 输出 [bbb, g王二, n沉默, xxx]
        // 修改元素：PriorityQueue 不支持直接修改元素，需要先删除再添加
        System.out.println(priorityQueue.poll()); // bbb
        priorityQueue.offer("h张三");
        System.out.println(priorityQueue); // 输出 [g王二, h张三, n沉默, xxx]
        // 查找元素：PriorityQueue 不支持随机访问元素，只能访问队首元素
        System.out.println(priorityQueue.peek()); // 输出 g王二
        System.out.println(priorityQueue.contains("n沉默")); // 输出 true
        // 通过 for 循环的方式查找陈清扬
        for (String element : priorityQueue) {
            if (element.equals("n沉默")) {
                System.out.println("找到了：" + element);
                break;
            }
        }
        /**
         * 要想有优先级，元素就需要实现 Comparable 接口或者 Comparator 接口
         * 通过实现 Comparator 接口按照年龄姓名排序的优先级队列
         */
        // 创建一个按照总成绩排序的优先级队列
        PriorityQueue<MyStudent> myStudentQueue = new PriorityQueue<>(new MyStudentComparator());
        // 添加元素
        myStudentQueue.offer(new MyStudent("王二", 80, 90));
        System.out.println(myStudentQueue);
        myStudentQueue.offer(new MyStudent("陈清扬", 95, 95));
        System.out.println(myStudentQueue);
        myStudentQueue.offer(new MyStudent("小驼铃", 90, 95));
        System.out.println(myStudentQueue);
        myStudentQueue.offer(new MyStudent("沉默", 90, 80));
        System.out.println(myStudentQueue);
        while (!myStudentQueue.isEmpty()) {
            System.out.println(myStudentQueue.poll() + " ");
        }
        /**
         * 小顶堆
         */
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(5);
        minHeap.add(4);
        minHeap.add(10);
        minHeap.add(1);
        minHeap.add(7);
        minHeap.add(3);
        while(minHeap.size() > 0){
            System.out.print(minHeap.poll() + " -> ");
        }
        System.out.println();
        /**
         * 大顶堆
         * 括号里是重写比较器的lambda表达式,k是初始化大小, 小顶堆可以省略
         */
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(5, (a,b)->b-a);
        maxHeap.add(4);
        maxHeap.add(10);
        maxHeap.add(1);
        maxHeap.add(7);
        maxHeap.add(3);
        while(maxHeap.size() > 0){
            System.out.print(maxHeap.poll() + " -> ");
        }
        System.out.println();

    }

}

class MyStudent {
    private String name;
    private int chineseScore;
    private int mathScore;

    public MyStudent(String name, int chineseScore, int mathScore) {
        this.name = name;
        this.chineseScore = chineseScore;
        this.mathScore = mathScore;
    }

    public String getName() {
        return name;
    }

    public int getChineseScore() {
        return chineseScore;
    }

    public int getMathScore() {
        return mathScore;
    }

    @Override
    public String toString() {
        return "Student{" + "name='" + name + '\'' + ", 总成绩=" + (chineseScore + mathScore) + '}';
    }
}

class MyStudentComparator implements Comparator<MyStudent> {
    @Override
    public int compare(MyStudent s1, MyStudent s2) {
        // 比较总成绩
        return Integer.compare(s2.getChineseScore() + s2.getMathScore(),
                s1.getChineseScore() + s1.getMathScore());
    }
}
