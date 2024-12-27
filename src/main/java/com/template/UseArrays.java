package com.template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UseArrays {
    public static void main(String[] args) {
        /**
         * asList
          */
        String[] original = new String[] { "a1", "b2", "c3", "d4" };
        List<String> list = Arrays.asList(original);
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
         * copyOf
          */
        String[] intro = new String[] { "a", "b", "c", "d" };
        String[] revised = Arrays.copyOf(intro, 3);
        String[] expanded = Arrays.copyOf(intro, 5);
        System.out.println(Arrays.toString(revised));
        System.out.println(Arrays.toString(expanded));
        /**
         * copyOfRange
         */
        String[] abridgement = Arrays.copyOfRange(intro, 0, 3);
        System.out.println(Arrays.toString(abridgement));
        String[] abridgementExpanded = Arrays.copyOfRange(intro, 0, 6);
        System.out.println(Arrays.toString(abridgementExpanded));
        /**
         * fill
         */
        String[] stutter = new String[4];
        Arrays.fill(stutter, "ABC");
        System.out.println(Arrays.toString(stutter));
        //
    }
}
