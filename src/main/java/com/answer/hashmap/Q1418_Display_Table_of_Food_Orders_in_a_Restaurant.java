package com.answer.hashmap;

import java.util.*;

public class Q1418_Display_Table_of_Food_Orders_in_a_Restaurant {
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
