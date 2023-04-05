package com.answer.backtracking;

import java.util.*;

public class Q332_Reconstruct_Itinerary {
    /**
     * Backtracking - Hard
     *
     * Overall, we could consider this problem as a graph traversal problem, where an airport can be viewed as a vertex
     * in graph and flight between airports as an edge in graph.
     *
     * The input graph is NOT what we call a DAG (Directed Acyclic Graph), since we could find at least a cycle in the graph.
     *
     * Typically, backtracking is used to enumerate all possible solutions for a problem, in a trial-fail-and-fallback strategy.
     */
    private Deque<String> res;
    private Map<String, Map<String, Integer>> map;

    private boolean backTracking(int ticketNum){
        if(res.size() == ticketNum + 1){
            return true;
        }
        String last = res.getLast();
        if(map.containsKey(last)){//防止出现null
            for(Map.Entry<String, Integer> target : map.get(last).entrySet()){
                int count = target.getValue();
                if(count > 0){
                    res.add(target.getKey());
                    target.setValue(count - 1);
                    if(backTracking(ticketNum)) return true;
                    res.removeLast();
                    target.setValue(count);
                }
            }
        }
        return false;
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        /**
         * Adopted the hashmap (or dictionary) data structure, with each entry as <origin, [destinations]>.
         */
        map = new HashMap<String, Map<String, Integer>>();
        res = new LinkedList<>();
        for(List<String> t : tickets){
            Map<String, Integer> temp;
            if(map.containsKey(t.get(0))){
                temp = map.get(t.get(0));
                temp.put(t.get(1), temp.getOrDefault(t.get(1), 0) + 1);
            }else{
                temp = new TreeMap<>();//升序Map
                temp.put(t.get(1), 1);
            }
            map.put(t.get(0), temp);

        }
        res.add("JFK");
        backTracking(tickets.size());
        return new ArrayList<>(res);
    }
}
