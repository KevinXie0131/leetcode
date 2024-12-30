package com.template;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class UseDeque {
    public static void main(String[] args) {

        // Deque 双端队列 Double-ended queues (Deque 接口的实现非常好理解：从单向队列演变为双向队列，内部额外提供双向队列的操作方法)
        /* Deque 是 双端队列，支持在队列的 头部 和 尾部 添加、删除、访问元素。
                            它继承自 Queue 接口，是一种更加通用的数据结构。
                            既可以用作 队列（FIFO），也可以用作 栈（LIFO）。
            Deque 是一个接口，常用实现类有：
                ArrayDeque：基于动态数组的双端队列实现。(特点：适合用作栈和队列。线程不安全，但效率较高。不允许存储null元素。)
                LinkedList：基于双向链表的双端队列实现。(特点：支持队列和栈操作。允许存储null元素。适合频繁插入和删除操作的场景。)
            推荐使用场景
                对于简单的栈或队列操作，使用 ArrayDeque。
                对于需要频繁插入、删除或允许存储 null 的场景，选择 LinkedList
                ArrayDeque 作为栈时比 Stack 性能好，作为队列时比 LinkedList 性能好
        */
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(1); // 在头部添加
        deque.offerFirst(1); // 在头部添加
        deque.addLast(2);  // 在尾部添加
        deque.offerLast(2);  // 在尾部添加

    //    deque.removeFirst(); // 删除头部元素
    //    deque.pollFirst(); // 删除头部元素
    //    deque.removeLast();  // 删除尾部元素
    //    deque.pollLast();  // 删除尾部元素

        System.out.println(deque.getFirst()); // 输出 1
        System.out.println(deque.peekFirst()); // 输出 1
        System.out.println(deque.getLast());  // 输出 2
        System.out.println(deque.peekLast());  // 输出 2

        deque.clear();
        deque.size();
        deque.isEmpty();

        //栈操作（LIFO）- push 和 pop 方法，可以方便地模拟栈。
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(1); // 压入栈顶
        System.out.println(stack.peek());
        System.out.println(stack.pop()); // 弹出栈顶元素，输出：1

        // 队列操作（FIFO）- offer 和 poll 方法，可以模拟队列操作。
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(1); // 添加到尾部
        queue.offer(2);
        System.out.println(queue.poll()); // 移除头部元素，输出：1
        System.out.println(queue.peek()); // 获取头部元素，输出：2

        // Deque可以使用Iterator
        Deque<Integer> myDeque = new ArrayDeque<>();
        myDeque.add(3);
        myDeque.add(2);
        myDeque.add(1);
        Iterator iter = myDeque.iterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }

    }
/*  数据类型        插入、删除时间复杂度      查询时间复杂度     底层数据结构               是否线程安全
    Vector          O(N)                    O(1)                数组                    是（已淘汰）
    ArrayList       O(N)                    O(1)                数组                    否
    LinkedList      O(1)                    O(N)                双向链表                 否
    HashSet         O(1)                    O(1)                数组+链表+红黑树	        否
    TreeSet         O(logN)                 O(logN)             红黑树                   否
    LinkedHashSet	O(1)	                O(1)~O(N)	        数组 + 链表 + 红黑树	    否
    ArrayDeque	    O(N)	                O(1)	            数组	                否
    PriorityQueue	O(logN)	                O(logN)	            堆（数组实现）	        否
    HashMap	        O(1) ~ O(N)         	O(1) ~ O(N)	        数组+链表+红黑树	        否
    TreeMap         O(logN)	                O(logN)	            数组+红黑树	            否
    HashTable	    O(1) / O(N)	            O(1) / O(N)     	数组+链表	            是（已淘汰）*/
}
