package com.template;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UseCollections {
    public static void main(String[] args) {
        /**
         * 排序操作
         */
        List<String> list = new ArrayList<>();
        list.add("A1");
        list.add("B2");
        list.add("C3");
        System.out.println(list);
        // 反转
        Collections.reverse(list);
        System.out.println("reverse：" + list);
        // 洗牌
        Collections.shuffle(list);
        System.out.println("shuffle：" + list);
        // 自然升序
        Collections.sort(list);
        System.out.println("sort：" + list);
        // 交换
        Collections.swap(list, 1, 2);
        System.out.println("swap：" + list);

        List<String> names = new ArrayList<>();
        names.add("Zo");
        names.add("Bob");
        names.add("Alice");
        // 使用自然排序（字母顺序）
        Collections.sort(names);
        System.out.println("Sorted list in natural order: " + names);
        // 使用自定义排序（按长度）
        Collections.sort(names, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
        System.out.println("Sorted list by length: " + names);
        /**
         * 查找操作
         */
        list.add("C3");
        System.out.println("max：" + Collections.max(list));
        System.out.println("min：" + Collections.min(list));
        System.out.println("frequency：" + Collections.frequency(list, "B2"));
        // 没有排序直接调用二分查找，结果是不确定的
        System.out.println("binarySearch before sort：" + Collections.binarySearch(list, "C3"));
        Collections.sort(list);
        // 排序后，查找结果和预期一致
        System.out.println("binarySearch after sort：" + Collections.binarySearch(list, "C3"));
        Collections.fill(list, "C3");
        System.out.println("fill：" + list);
        /**
         * 还有两个方法比较常用：
         * addAll(Collection<? super T> c, T... elements)，往集合中添加元素
         * disjoint(Collection<?> c1, Collection<?> c2)，判断两个集合是否没有交集
         */
        List<String> allList = new ArrayList<>();
        Collections.addAll(allList, "D4", "E5", "F6");
        System.out.println("addAll：" + allList);

        System.out.println("disjoint：" + Collections.disjoint(list, allList));
        allList.removeAll(list); // 从allList中移除所有存在于list中的元素
        System.out.println("allList after removeAll: " + allList);
        /**
         * 复制
         */
        List<String> copyList = new ArrayList<>();
        Collections.copy(allList, copyList);
        System.out.println("copyList: " + allList);
    }
}
