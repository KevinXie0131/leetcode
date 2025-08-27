package com.answer.union_find;

import java.util.*;

public class Q399_Evaluate_Division_2 {
    /**
     * 深度优先搜索
     */
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        // 生成存储变量所构成的图结构
        Map<String, Map<String, Double>> graph = new HashMap<>();
        int n = equations.size();
        for(int i = 0; i < n; i++){
            String s = equations.get(i).get(0), e = equations.get(i).get(1);
            double v = values[i];
            if(!graph.containsKey(s)){
                graph.put(s, new HashMap<>());
            }
            if(!graph.containsKey(e)){
                graph.put(e, new HashMap<>());
            }
            graph.get(s).put(e, v);         // 生成一条s指向e，权重为v的路径，表示 s / e = v
            graph.get(e).put(s, 1 / v);     // 生成一条反向路径，权重为1 / v，表示 e / s = 1 /v
            graph.get(s).put(s, 1.0);       // 生成一个指向自己、权重为1的路径，表示自己除自己等于1
            graph.get(e).put(e, 1.0);
        }

        int m = queries.size();
        double[] ans = new double[m];                   // 答案列表
        Arrays.fill(ans, -1.0);                         // 初始都为-1表示未定义
        Set<String> visited;

        // 对于每个query，寻找从起点qx到终点qy的最短路径，并计算权重积
        for(int i = 0; i < m; i++){
            String qx = queries.get(i).get(0), qy = queries.get(i).get(1);
            if(!graph.containsKey(qx) || !graph.containsKey(qy)){
                continue;   // 未出现的变量，跳过处理
            }
            visited = new HashSet<>();                                      // 存储已处理的节点
            visited.add(qx);                                                // 初始化起点节点已处理
            ans[i] = dfs(qx, 1.0, qy, graph, visited);                      // DFS搜索答案
        }
        return ans;
    }

    private double dfs(String node, double mul, String qy, Map<String, Map<String, Double>> graph, Set<String> visited){
        if(qy.equals(node)){
            return mul;                 // 找到终点，直接返回累乘的权重
        }
        visited.add(node);              // 将当前节点标记为已处理
        double res = -1.0;              // 初始结果为-1.0，表示以当前节点无法到达目标节点qy
        for(Map.Entry<String, Double> entry: graph.get(node).entrySet()){   // 枚举该节点的所有邻节点
            String ngh = entry.getKey();
            double weight = entry.getValue();
            if(!visited.contains(ngh)){                                     // 找到一个未处理的邻节点
                res = dfs(ngh, mul * weight, qy, graph, visited);           // 递归处理邻节点
            }
            if(res != -1.0){
                break;                                           // 如果根据这个邻节点到目标节点，直接返回计算结果
            }
        }
        return res;
    }
    /**
     * Graph(HashMap) + DFS
     */
    public double[] calcEquation1(List<List<String>> equations, double[] values, List<List<String>> queries) {
        //初始化Graph(以HashMap形式)
        Map<String, List<Cell>> graph = new HashMap<>();
        //对于每个Equation和其结果答案，将其加入Graph中
        for(int i = 0; i < values.length; i++) {
            String s1 = equations.get(i).get(0), s2 = equations.get(i).get(1);
            graph.computeIfAbsent(s1, k -> new ArrayList<>()).add(new Cell(s2, values[i]));
            graph.computeIfAbsent(s2, k -> new ArrayList<>()).add(new Cell(s1, 1.0 / values[i]));
        }

        //创建答案result数组以及访问过的HashSet: visited
        double[] res = new double[queries.size()];
        //首先将答案中所有答案值置为-1.0，出现(x / x)情况可以直接不用修改
        Arrays.fill(res, -1.0);
        //对于每个query中的值调用dfs函数
        for(int i = 0; i < queries.size(); i++) {
            dfs(queries.get(i).get(0), queries.get(i).get(1), 1.0, graph, res, i, new HashSet<>());
        }
        return res;
    }

    //src: 当前位置; dst: 答案节点; cur: 当前计算值; graph: 之前建的图; res: 答案array; index: 当前遍历到第几个query; visited: 查重Set
    private void dfs(String src, String dst, double cur, Map<String, List<Cell>> graph, double[] res, int index, Set<String> visited) {
        //base case: 在visited中加入当前位置信息；如果加不了代表已经访问过，直接返回
        if(!visited.add(src)) {
            return;
        }
        //如果当前位置src = 答案节点dst，并且此节点在graph中(避免x/x的情况)，用当前计算值cur来填充答案res[index]
        if(src.equals(dst) && graph.containsKey(src)) {
            res[index] = cur;
            return;
        }
        //对于邻居节点，调用dfs函数
        for(Cell nei : graph.getOrDefault(src, new ArrayList<>())) {
            dfs(nei.str, dst, cur * nei.div, graph, res, index, visited);
        }
    }
}

class Cell {
    String str;
    double div;

    Cell(String str, double div) {
        this.str = str;
        this.div = div;
    }
}

