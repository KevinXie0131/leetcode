package com.answer.hashmap;

import java.util.*;

public class Q1418_Display_Table_of_Food_Orders_in_a_Restaurant {
    /**
     * 点菜展示表
     * 给你一个数组 orders，表示客户在餐厅中完成的订单，确切地说， orders[i]=[customerNamei,tableNumberi,foodItemi] ，其中 customerNamei 是客户的姓名，tableNumberi 是客户所在餐桌的桌号，而 foodItemi 是客户点的餐品名称。
     * 请你返回该餐厅的 点菜展示表 。在这张表中，表中第一行为标题，其第一列为餐桌桌号 “Table” ，后面每一列都是按字母顺序排列的餐品名称。接下来每一行中的项则表示每张餐桌订购的相应餐品数量，第一列应当填对应的桌号，后面依次填写下单的餐品数量。
     * 注意：客户姓名不是点菜展示表的一部分。此外，表中的数据行应该按餐桌桌号升序排列。
     */
    /**
     * HashMap + HashSet
     */
    public List<List<String>> displayTable(List<List<String>> orders) {
        Set<String> nameSet = new HashSet<String>();
        Map<Integer, Map<String, Integer>> foodsCnt =
                new HashMap<Integer, Map<String, Integer>>();
        for(List<String> order : orders){
            nameSet.add(order.get(2));
            int id = Integer.parseInt(order.get(1));
            Map<String, Integer> map = foodsCnt.getOrDefault(id, new HashMap<String, Integer>());
            map.put(order.get(2), map.getOrDefault(order.get(2), 0) + 1);
            foodsCnt.put(id, map);
        }

        int n = nameSet.size();
        List<String> names= new ArrayList<String>();
        for(String name : nameSet){
            names.add(name);
        }
        Collections.sort(names);

        int m = foodsCnt.size();
        List<Integer> ids= new ArrayList<Integer>();
        for(int id : foodsCnt.keySet()){
            ids.add(id);
        }
        Collections.sort(ids);

        List<List<String>> tables = new ArrayList<List<String>>();
        List<String> header = new ArrayList<String>();
        header.add("Table");
        for (String name : names) {
            header.add(name);
        }
        tables.add(header);

        for(int i = 0 ; i < m; i++){
            int id = ids.get(i);
            Map<String, Integer> cnt = foodsCnt.get(id);
            List<String> row = new ArrayList<String>();
            row.add(Integer.toString(id));
            for(int j = 0 ; j < n; j++){
                row.add(Integer.toString(cnt.getOrDefault(names.get(j), 0)));
            }
            tables.add(row);
        }

        return tables;
    }
}
