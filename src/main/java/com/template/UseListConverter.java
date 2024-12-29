package com.template;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UseListConverter {
    public static void main(String[] args) {
        /**
         * List转Array介绍
         */
        // List的toArray()方法可以将List类型的集合转换为数组类型
        List<String> list = new ArrayList<>();
        list.add("Java");
        list.add("Python");
        list.add("JavaScript");
        String[] result = list.toArray(new String[list.size()]);
        System.out.println(Arrays.toString(result));

        List<Integer> list1 = new ArrayList<>();
        list1.add(11);
        list1.add(22);
        list1.add(33);
        list1.add(44);
        Integer[] result1 = list1.toArray(new Integer[list1.size()]);
        System.out.println(Arrays.toString(result1));
        Integer[] result2 = list1.toArray(new Integer[0]);  //该数组的大小会根据 List 的元素数自动调整。
        System.out.println(Arrays.toString(result2));
        Object[] result3 = list1.toArray();  //此时数组的元素类型是 `Object`，如果需要更具体的类型，可以强制转换。
        System.out.println(Arrays.toString(result3));

        List<Integer> list2 = new ArrayList<>();
        list2.add(999);
        list2.add(888);
        list2.add(777);
        list2.add(666);
        int[] result4 = list2.stream().mapToInt(i -> i).toArray();
        System.out.println(Arrays.toString(result4));
        // Or
        int[] result5 = list2.stream().mapToInt(Integer::intValue).toArray();
        System.out.println(Arrays.toString(result5));
    }
}
