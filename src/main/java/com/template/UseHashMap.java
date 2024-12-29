package com.template;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

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
        /**
         * Map 保存的是键值对，键要求保持唯一性，值可以重复
         */
        /**
         * HashMap 中的键和值都可以为 null。如果键为 null，则将该键映射到哈希表的第一个位置。
         * 可以使用迭代器或者 forEach 方法遍历 HashMap 中的键值对。
         * HashMap 有一个初始容量和一个负载因子。初始容量是指哈希表的初始大小，负载因子是指哈希表在扩容之前可以存储的键值对数量与哈希表大小的比率。默认的初始容量是 16，负载因子是 0.75。
         */
        // 创建一个 HashMap 对象
        HashMap<String, String> hashMap = new HashMap<>();
        // 添加键值对
        hashMap.put("沉默", "cenzhong");
        hashMap.put("王二", "wanger");
        hashMap.put("陈清扬", "chenqingyang");
        hashMap.put(null,null);
        // 获取指定键的值
        String valueA = hashMap.get("沉默");
        System.out.println("沉默对应的值为：" + valueA);
        // 修改键对应的值
        hashMap.put("沉默", "chenmo");
        String valueB = hashMap.get("沉默");
        System.out.println("修改后沉默对应的值为：" + valueB);
        // 删除指定键的键值对
        hashMap.remove("王二");
        hashMap.replace("沉默", "chenmo1");
        System.out.println(hashMap); // {null=null, 沉默=chenmo1, 陈清扬=chenqingyang}
        /**
         * HashMap 已经非常强大了，但它是无序的。如果我们需要一个有序的 Map，就要用到 LinkedHashMap。
         * LinkedHashMap 是 HashMap 的子类，它使用链表来记录插入/访问元素的顺序。
         *
         * LinkedHashMap 可以看作是 HashMap + LinkedList 的合体，它使用了哈希表来存储数据，又用了双向链表来维持顺序。
         */
        // 创建一个 LinkedHashMap，插入的键值对为 沉默 王二 陈清扬
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("8沉默", "cenzhong");
        linkedHashMap.put("1王二", "wanger");
        linkedHashMap.put("2陈清扬", "chenqingyang");
        // 遍历 LinkedHashMap
        for (String key : linkedHashMap.keySet()) {
            String value = linkedHashMap.get(key);
            System.out.println(key + " 对应的值为：" + value); // LinkedHashMap 维持了键值对的插入顺序. 同样的数据HashMap不能, HashMap 没有维持键值对的插入顺序
        }
        /**
         * TreeMap 实现了 SortedMap 接口，可以自动将键按照自然顺序或指定的比较器顺序排序，并保证其元素的顺序。内部使用红黑树来实现键的排序和查找。
         * 与 HashMap 不同的是，TreeMap 会按照键的顺序来进行排序。
         * 默认情况下，已经按照键的自然顺序排序
         */
        // 创建一个 TreeMap 对象
        Map<String, String> treeMap = new TreeMap<>();
        // 向 TreeMap 中添加键值对
        treeMap.put("8沉默", "cenzhong");
        treeMap.put("1王二", "wanger");
        treeMap.put("2陈清扬", "chenqingyang");
       // treeMap.put(null, null); //Exception in thread "main" java.lang.NullPointerException
        // 查找键值对
        String name = "8沉默";
        if (treeMap.containsKey(name)) {
            System.out.println("找到了 " + name + ": " + treeMap.get(name));
        } else {
            System.out.println("没有找到 " + name);
        }
        // 修改键值对
        name = "1王二";
        if (treeMap.containsKey(name)) {
            System.out.println("修改前的 " + name + ": " + treeMap.get(name));
            treeMap.put(name, "newWanger");
            System.out.println("修改后的 " + name + ": " + treeMap.get(name));
        } else {
            System.out.println("没有找到 " + name);
        }
        // 删除键值对
        name = "2陈清扬";
        if (treeMap.containsKey(name)) {
            System.out.println("删除前的 " + name + ": " + treeMap.get(name));
            treeMap.remove(name);
            System.out.println("删除后的 " + name + ": " + treeMap.get(name));
        } else {
            System.out.println("没有找到 " + name);
        }
        // 遍历 TreeMap
        for (Map.Entry<String, String> entry : treeMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
