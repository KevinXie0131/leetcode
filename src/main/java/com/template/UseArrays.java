package com.template;

import java.util.*;
import java.util.stream.Collectors;

public class UseArrays {
    public static void main(String[] args) {
        /**
         * asList
          */
        String[] original = new String[] { "a1", "b2", "c3", "d4" };
        List<String> list = Arrays.asList(original); //引用类型的数组
        List<Character> listA = Arrays.asList('X','Y','Z'); //一组相同类型的动态参数
        System.out.println(listA.toString());
         // 通过asList()方法获得的列表是固定大小的，不支持添加（add）或删除（remove）元素操作
        // 尝试进行这些操作会抛出 UnsupportedOperationException 异常
        // list.add("e5");
        // 转换为ArrayList，才能进行add()操作。
        ArrayList<String> list1 = new ArrayList<>(list);
        list1.add("e5");
        System.out.println(list1);
        // 修改原始数组会影响列表。
        original[3] = "d44";
        System.out.println(list);
        // asList()方法接受的是对象数组，因此如果你尝试将一个基本类型的数组（如 int[], double[] 等）直接传递给 asList，它不会按你期望的方式工作。
        // 为了解决这个问题，你需要将基本类型数组转换为包装类型的数组，例如使用 Integer[] 而不是 int[]。
        System.out.println(Arrays.asList(new int[]{10, 20, 30}));
        System.out.println(Arrays.asList(new Integer[]{10, 20, 30}));
        // asList()方法常用于需要将数组作为列表传递给需要 List 类型参数的方法时，
        // 或者当你需要快速地将数组转换为一个列表以便使用列表的某些方法（如 contains, indexOf 等）时。
        System.out.println(list.contains("b2"));
        System.out.println(list.indexOf("c3"));
        Collections.reverse(list);
        System.out.println(list);
        // JDK8 特性 Stream
        int[] cards = {100,200,300};
        List<Integer> colloctor1 = Arrays.stream(cards).boxed().collect(Collectors.toList());
        System.out.println(colloctor1);
        Integer[] cardsArray = {100,200,300,400};
        List<Integer> colloctor2 = Arrays.stream(cardsArray).collect(Collectors.toList());
        colloctor2.add(500); // 此处可以add
        System.out.println(colloctor2);
        /**
         * copyOf 复制指定的数组，截取或用 null 填充
          */
        String[] intro = new String[] { "a", "b", "c", "d" };
        String[] revised = Arrays.copyOf(intro, 3);
        String[] expanded = Arrays.copyOf(intro, 5);
        System.out.println(Arrays.toString(revised));
        System.out.println(Arrays.toString(expanded));
        /**
         * copyOfRange 复制指定范围内的数组到一个新的数组
         * copyOfRange() 方法需要三个参数，第一个是指定的数组，第二个是起始位置（包含），第三个是截止位置（不包含）
         */
        String[] abridgement = Arrays.copyOfRange(intro, 0, 3);
        System.out.println(Arrays.toString(abridgement));
        String[] abridgementExpanded = Arrays.copyOfRange(intro, 0, 6);
        System.out.println(Arrays.toString(abridgementExpanded));
        /**
         * fill 对数组进行填充
         */
        String[] stutter = new String[4];
        Arrays.fill(stutter, "ABC");
        System.out.println(Arrays.toString(stutter)); // [ABC, ABC, ABC, ABC]
        Arrays.fill(stutter, 1, 3,"ABCD"); // [ABC, ABCD, ABCD, ABC]
        System.out.println(Arrays.toString(stutter));
        /**
         * equals 比较数组 (能够进行数组内容的比较, Arrays.equals()才是比较数组内容是否相同的正确方法)
         */
        String[] intro1 = new String[] { "A", "B", "C", "D" };
        boolean result = Arrays.equals(new String[] { "A", "B", "C", "D" }, intro1);
        System.out.println(result);
        /**
         * sort 数组排序
         */
        String[] intro2 = new String[] { "chen", "mo", "wang", "er" };
        Arrays.sort(intro2); // 排序会改变原有的数组
        System.out.println(Arrays.toString(intro2));

        String[] intro2a = new String[] { "chen", "mo", "wang", "er" };
        Arrays.sort(intro2a, Collections.reverseOrder()); // Collections.reverseOrder()
        System.out.println(Arrays.toString(intro2a));

        String[] intro2b = new String[] { "chen", "mo", "p", "wang", "er" };
        Arrays.sort(intro2b, new Comparator<String>() { // 匿名内部类
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        System.out.println(Arrays.toString(intro2b));

        String[] intro2c = new String[] { "chen", "mo", "p", "wang", "er" };
        Arrays.sort(intro2c, (e1, e2) -> e2.length() - e1.length()); // lambda
        System.out.println(Arrays.toString(intro2c));

        /**
         * binarySearch 数组检索。 通过二分查找在 “有序数组”中查找目标值
         */
        String[] intro3 = new String[] { "chen", "mo", "wang", "er" };
        int exact = Arrays.binarySearch(intro3, "wang");
        System.out.println(exact);
        int caseInsensitive = Arrays.binarySearch(intro3, "Wang", String::compareToIgnoreCase);
        System.out.println(caseInsensitive);
        /**
         * setAll: Java 8 新增了 setAll() 方法, 它提供了一个函数式编程的入口，可以对数组的元素进行填
         */
        int[] array = new int[10];
        Arrays.setAll(array, i -> i * 10);
        System.out.println(Arrays.toString(array));
        /**
         * parallelPrefix() 是 Java 8 提供的，提供了一个函数式编程的入口，通过遍历数组中的元素，
         * 将当前下标位置上的元素与它之前下标的元素进行操作，然后将操作后的结果覆盖当前下标位置上的元素。
         */
        int[] arr = new int[] { 1, 2, 3, 4};
        Arrays.parallelPrefix(arr, (left, right) -> left + right);
        System.out.println(Arrays.toString(arr)); // [1, 3, 6, 10]
        /**
         * deepToString 打印二维数组
         */
        int[][] matrix = new int[][] {{1,2,3}, {4,5,6}, {7,8,9}, {10,11,12}};
        System.out.println(Arrays.deepToString(matrix)); // [[1, 2, 3], [4, 5, 6], [7, 8, 9], [10, 11, 12]]
    }
}
