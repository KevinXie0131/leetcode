package com.template;

import java.util.HashMap;

public class UseHashMap {
    /**
     * putIfAbsent(): 会先判断指定的键（key）是否存在，不存在则将键/值对插入到 HashMap 中。
     * getOrDefault(): 法获取指定 key 对应对 value，如果找不到 key ，则返回设置的默认值。
     * forEach(): 用于对 HashMap 中的每个映射执行指定的操作。
     * compute(): 对 hashMap 中指定 key 的值进行重新计算。
     * computeIfAbsent(): 对 hashMap 中指定 key 的值进行重新计算，如果不存在这个 key，则添加到 hashMap 中。
     * computeIfPresent(): 对 hashMap 中指定 key 的值进行重新计算，前提是该 key 存在于 hashMap 中。
     */
    public static void main(String[] args) {
        HashMap<Integer, String> sites = new HashMap<>();
        // 往 HashMap 添加一些元素
        sites.put(1, "Google");
        sites.put(2, "Runoob");
        sites.put(3, "Taobao");
        // HashMap 中存在 Key
        sites.putIfAbsent(2, "Wiki"); //key 为 2 已经存在于 sites 中，所以不会执行插入操作。
        System.out.println("Updated Languages: " + sites);

        // key 的映射存在于 HashMap 中
        // Not Found - 如果 HashMap 中没有该 key，则返回默认值
        String value1 = sites.getOrDefault(1, "Not Found");
        System.out.println("Value for key 1:  " + value1);

        // key 的映射不存在于 HashMap 中
        // Not Found - 如果 HashMap 中没有该 key，则返回默认值
        String value2 = sites.getOrDefault(4, "Not Found");
        System.out.println("Value for key 4: " + value2);

        //通过 lambda 表达式使用 forEach()
        sites.forEach((key, value) -> {
            System.out.print(key + "=" + value + " ");
        });

        System.out.println();
        sites.compute(2, (key, value) -> value + "!");
        System.out.println(sites);

        sites.compute(4, (key, value) ->  "Baidu"); // 4 Shirt 在 HashMap 中不存在，所以是新增了 key/value 对。
        System.out.println(sites);

        sites.compute(1, (key, value) -> value + "?");
        System.out.println(sites);
    }

}
