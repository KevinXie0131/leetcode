package com.answer.backtracking;

import java.util.*;

public class Q332_Reconstruct_Itinerary { // 困难 Hard
    /**
     * a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight.
     * Reconstruct the itinerary in order and return it.
     * You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.
     * 重新安排行程: 一份航线列表 tickets ，其中 tickets[i] = [fromi, toi] 表示飞机出发和降落的机场地点。请你对该行程进行重新规划排序。
     * 所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。如果存在多种有效的行程，
     * 请你按字典排序返回最小的行程组合。
     * 假定所有机票至少存在一种合理的行程。且所有的机票 必须都用一次 且 只能用一次。
     */
    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<List<String>>();
        tickets.add(Arrays.asList("MUC","LHR"));
        tickets.add(Arrays.asList("JFK","MUC"));
        tickets.add(Arrays.asList("SFO","SJC"));
        tickets.add(Arrays.asList("LHR","SFO"));
        System.out.println(findItinerary(tickets)); // 输出：["JFK","MUC","LHR","SFO","SJC"]
    }
    /**
     * 首先明确几点：
     * 1.肯定能找到一条路线
     * 2.每张机票只能使用一次
     * 3.要找降落地字典序最小的路线结果
     */
    List<String> path_5 = new ArrayList<>();// path记录路线，res存所有路线
    List<List<String>> res_5 = new ArrayList<>();
    //used数组用于标记同一树枝不能重复使用！即不能重复使用一张票
    boolean[] used_5 = new boolean[301]; // 1 <= tickets.length <= 300
    boolean isFound;

    public List<String> findItinerary_5(List<List<String>> tickets) {
        //先按字典序从小到大排列降落地
        tickets.sort((o1, o2) -> o1.get(1).compareTo(o2.get(1)));
        path_5.add("JFK");
        backTracking_5(tickets, "JFK");
        return res_5.get(0);
    }
    // Time Limit Exceeded
    void backTracking_5(List<List<String>> tickets, String outset) {
        //算个小剪枝吧，找到一条就行
        if (isFound) {
            return;
        }
        //因为这些航班肯定会有一条路线是正确的
        //所以我们加入path的size如果等于tickets.size()+1说明我们找到路线了
        if (path_5.size() == tickets.size() + 1) {
            isFound = true;
            res_5.add(new ArrayList<>(path_5));
            return;
        }
        for (int i = 0; i < tickets.size(); i++) {
            if(i > 0 && tickets.get(i).get(0).equals(tickets.get(i - 1).get(0)) // 再添加一个筛选条件
                     && tickets.get(i).get(1).equals(tickets.get(i - 1).get(1))
                     && !used_5[i - 1]){
                continue;
            }
            //如果出发地和上一个的降落地相同 并且 同一条路线中没有重复使用一张票
            if(tickets.get(i).get(0).equals(outset)  && !used_5[i]){
                used_5[i] = true; //标记该票已经使用过
                path_5.add(tickets.get(i).get(1));
                backTracking_5(tickets, tickets.get(i).get(1)); //把现在的降落地加入递归函数
                used_5[i] = false; //回溯！ 该票标记为未使用 路线中移除该票
                path_5.remove(path_5.size() - 1);
            }
        }
    }
    /**
     * 这道题目还是很难的, 实际上确实是图论中的深度优先搜索，但这是深搜中使用了回溯的例子
     * Backtracking - Hard
     *
     * Overall, we could consider this problem as a graph traversal problem, where an airport can be viewed as a vertex
     * in graph and flight between airports as an edge in graph.
     *
     * The input graph is NOT what we call a DAG (Directed Acyclic Graph), since we could find at least a cycle in the graph.
     * Typically, backtracking is used to enumerate all possible solutions for a problem, in a trial-fail-and-fallback strategy.
     */
    static private Deque<String> res;
    static private Map<String, Map<String, Integer>> map;
    // 方案2:
    static private boolean backTracking(int ticketNum){
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
                    // backtracking
                    if(backTracking(ticketNum)) {
                        return true;
                    }
                    res.removeLast();
                    target.setValue(count);
                }
            }
        }
        return false;
    }

    static public List<String> findItinerary(List<List<String>> tickets) {
        /**
         * Adopted the hashmap (or dictionary) data structure, with each entry as <origin, [destinations]>.
         */
        map = new HashMap<String, Map<String, Integer>>();
        res = new LinkedList<>();
        // build the graph first
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
        System.out.println(map);

        res.add("JFK");
        backTracking(tickets.size());
        return new ArrayList<>(res);
    }
    /**
     * 方案1： Time Limit Exceeded
     */
    private LinkedList<String> res1;
    private LinkedList<String> path1 = new LinkedList<>();

    public List<String> findItinerary1(List<List<String>> tickets) {
        Collections.sort(tickets, (a, b) -> a.get(1).compareTo(b.get(1)));
        path1.add("JFK");
        boolean[] used = new boolean[tickets.size()];
        backTracking1((ArrayList) tickets, used);
        return res1;
    }

    public boolean backTracking1(ArrayList<List<String>> tickets, boolean[] used) {
        if (path1.size() == tickets.size() + 1) {
            res1 = new LinkedList(path1);
            return true;
        }
        for (int i = 0; i < tickets.size(); i++) {
            if (!used[i] && tickets.get(i).get(0).equals(path1.getLast())) {
                path1.add(tickets.get(i).get(1));
                used[i] = true;

                if (backTracking1(tickets, used)) {
                    return true;
                }
                used[i] = false;
                path1.removeLast();
            }
        }
        return false;
    }
    /**
     * 该方法是对第二个方法的改进，主要变化在于将某点的所有终点变更为链表的形式，优点在于
     *         1.添加终点时直接在对应位置添加节点，避免了TreeMap增元素时的频繁调整
     *         2.同时每次对终点进行增加删除查找时直接通过下标操作，避免hashMap反复计算hash
     */
    Map<String, LinkedList<String>> ticketMap = new HashMap<>();//key为起点，value是有序的终点的列表
    LinkedList<String> result = new LinkedList<>();
    int total;

    public List<String> findItinerary2(List<List<String>> tickets) {
        total = tickets.size() + 1;
        //遍历tickets，存入ticketMap中
        for (List<String> ticket : tickets) {
            addNew(ticket.get(0), ticket.get(1));
        }
        deal("JFK");
        return result;
    }

    boolean deal(String currentLocation) {
        result.add(currentLocation);
        if (result.size() == total) {  //机票全部用完，找到最小字符路径
            return true;
        }
        LinkedList<String> targetLocations = ticketMap.get(currentLocation);  //当前位置的终点列表
        if (targetLocations != null && !targetLocations.isEmpty()) {  //没有从当前位置出发的机票了，说明这条路走不通
            String targetLocation;//终点列表中遍历到的终点
            for (int i = 0; i < targetLocations.size(); i++) {  //遍历从当前位置出发的机票
                if(i > 0 && targetLocations.get(i).equals(targetLocations.get(i - 1))) {   //去重，否则在最后一个测试用例中遇到循环时会无限递归
                    continue;
                }
                targetLocation = targetLocations.get(i);
                targetLocations.remove(i);   //删除终点列表中当前的终点
                if (deal(targetLocation)) { //递归
                    return true;
                }
                targetLocations.add(i, targetLocation);  //路线走不通，将机票重新加回去
                result.removeLast();
            }
        }
        return false;
    }
    /**
     * 在map中按照字典顺序添加新元素
     * @param start 起点
     * @param end   终点
     */
    void addNew(String start, String end) {
        LinkedList<String> startAllEnd = ticketMap.getOrDefault(start, new LinkedList<>());
        if (!startAllEnd.isEmpty()) {
            for (int i = 0; i < startAllEnd.size(); i++) {
                if (end.compareTo(startAllEnd.get(i)) < 0) {
                    startAllEnd.add(i, end);
                    return;
                }
            }
            startAllEnd.add(startAllEnd.size(), end);
       //     startAllEnd.add(end);  // works too
       //     Collections.sort(startAllEnd);
        } else {
            startAllEnd.add(end);
            ticketMap.put(start, startAllEnd);
        }
    }
}
