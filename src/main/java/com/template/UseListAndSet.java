package com.template;

import java.util.*;

public class UseListAndSet {
    public static void main(String[] args) {
        /**
         * ArrayList 是由数组实现的，支持随机存取，也就是可以通过下标直接存取元素；
         * 从尾部插入和删除元素会比较快捷，从中间插入和删除元素会比较低效，因为涉及到数组元素的复制和移动；
         * 如果内部数组的容量不足时会自动扩容，因此当元素非常庞大的时候，效率会比较低。
         */
        // 创建一个集合
        ArrayList<String> list = new ArrayList<String>();
        // 添加元素
        list.add("王二");
        list.add("沉默");
        list.add("陈清扬");
        // 遍历集合 for 循环
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            System.out.println(s);
        }
        // 遍历集合 for each
        for (String s : list) {
            System.out.println(s);
        }
        // 删除元素
        list.remove(1);
        // 修改元素
        list.set(1, "王二狗");
        System.out.println(list);
        /**
         * LinkedList 是由双向链表实现的，不支持随机存取，只能从一端开始遍历，直到找到需要的元素后返回；
         * 任意位置插入和删除元素都很方便，因为只需要改变前一个节点和后一个节点的引用即可，不像 ArrayList 那样需要复制和移动数组元素；
         * 因为每个元素都存储了前一个和后一个节点的引用，所以相对来说，占用的内存空间会比 ArrayList 多一些。
         */
        // 创建一个集合
        LinkedList<String> linkedList = new LinkedList<String>();
        // 添加元素
        linkedList.add("王二1");
        linkedList.add("沉默1");
        linkedList.add("陈清扬1");
        // 遍历集合 for 循环
        for (int i = 0; i < linkedList.size(); i++) {
            String s = linkedList.get(i);
            System.out.println(s);
        }
        // 遍历集合 for each
        for (String s : linkedList) {
            System.out.println(s);
        }
        // 删除元素
        linkedList.remove(1);
        // 修改元素
        linkedList.set(1, "王二狗1");
        System.out.println(linkedList);
        linkedList.addFirst("A"); // offerFirst(), offerLast(), peekFirst(), peekLast()
        linkedList.addLast("B");  // peekFirst(), peekLast()
        System.out.println(linkedList);
        linkedList.removeFirst(); // pollFirst(), pollLast()
        linkedList.removeLast();
        System.out.println(linkedList);
        /**
         * HashSet 其实是由 HashMap 实现的，只不过值由一个固定的 Object 对象填充，而键用于操作。
         * HashSet 主要用于去重，比如，我们需要统计一篇文章中有多少个不重复的单词，就可以使用 HashSet 来实现。
         */
        // 创建一个新的HashSet
        HashSet<String> set = new HashSet<>();
        // 添加元素
        set.add("沉默");
        set.add("王二");
        set.add("陈清扬");
        // 输出HashSet的元素个数
        System.out.println("HashSet size: " + set.size()); // output: 3
        // 判断元素是否存在于HashSet中
        boolean containsWanger = set.contains("王二");
        System.out.println("Does set contain '王二'? " + containsWanger); // output: true
        // 删除元素
        boolean removeWanger = set.remove("王二");
        System.out.println("Removed '王二'? " + removeWanger); // output: true
        // 修改元素，需要先删除后添加
        boolean removeChenmo = set.remove("沉默");
        boolean addBuChenmo = set.add("不沉默");
        System.out.println("Modified set? " + (removeChenmo && addBuChenmo)); // output: true
        // 输出修改后的HashSet
        System.out.println("HashSet after modification: " + set); // output: [陈清扬, 不沉默]
        // HashSet 会自动去重，因为它是用 HashMap 实现的，HashMap 的键是唯一的（哈希值），相同键的值会覆盖掉原来的值
        set.add("陈清扬");
        System.out.println("HashSet 会自动去重: " + set);
        /**
         * LinkedHashSet 虽然继承自 HashSet，其实是由 LinkedHashMap 实现的。
         * LinkedHashSet 是一种基于哈希表实现的 Set 接口，它继承自 HashSet，并且使用链表维护了元素的插入顺序。
         * 因此，它既具有 HashSet 的快速查找、插入和删除操作的优点，又可以维护元素的插入顺序。
         */
        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<>();
        // 添加元素
        linkedHashSet.add("沉默1");
        linkedHashSet.add("王二2");
        linkedHashSet.add("陈清扬3");
        System.out.println(linkedHashSet);
        // 删除元素
        linkedHashSet.remove("王二2");
        // 修改元素
        linkedHashSet.remove("沉默1");
        set.add("沉默的力量4");
        // 查找元素
        boolean hasChenQingYang = linkedHashSet.contains("陈清扬3");
        System.out.println("set包含陈清扬吗？" + hasChenQingYang);
        /**
         * TreeSet 是由 TreeMap（后面会讲） 实现的，只不过同样操作的键位，值由一个固定的 Object 对象填充
         * 与 TreeMap 相似，TreeSet 是一种基于红黑树实现的有序集合，它实现了 SortedSet 接口，可以自动对集合中的元素进行排序。
         * 按照键的自然顺序或指定的比较器顺序进行排序。
         * TreeSet 不允许插入 null 元素，否则会抛出 NullPointerException 异常。
         */
        // 创建一个 TreeSet 对象
        TreeSet<String> treeSet = new TreeSet<>();
        // 添加元素
        treeSet.add("b沉默");
        treeSet.add("t王二");
        treeSet.add("a陈清扬");
        System.out.println(treeSet); // 输出 [a陈清扬, b沉默, t王二]
        System.out.println(treeSet.ceiling("c")); // t王二
        System.out.println(treeSet.floor("c")); // b沉默
        System.out.println(treeSet.higher("b沉默")); // t王二
        System.out.println(treeSet.lower("b沉默")); // a陈清扬
        // 删除元素
        treeSet.remove("t王二");
        System.out.println(treeSet); // 输出 [a陈清扬, b沉默]
        // 修改元素：TreeSet 中的元素不支持直接修改，需要先删除再添加
        treeSet.remove("a陈清扬");
        treeSet.add("n陈青阳");
        System.out.println(treeSet); // 输出 [b沉默, n陈青阳]
        // 查找元素
        System.out.println(treeSet.contains("b沉默")); // 输出 true
        System.out.println(treeSet.contains("t王二")); // 输出 false
        treeSet.pollFirst();
        treeSet.pollLast();
        System.out.println(treeSet); // []
    }
}
