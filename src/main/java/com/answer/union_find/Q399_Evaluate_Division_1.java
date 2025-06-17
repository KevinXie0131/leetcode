package com.answer.union_find;

import java.util.*;

public class Q399_Evaluate_Division_1 {
    /**
     * 求解 a / c 相当于计算从节点 a 到 节点 c 的路径的权重乘积
     * 根据上面的分析，我们可以根据输入 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 进行构图：
     *  构建一条从 Ai 节点 指向 Bi 节点，权重为 values[i] 的边；
     *  构建一条从 Bi 节点 指向 Ai 节点，权重为 1 / values[i] 的边;【表示 Bi / Ai = values[i]】；
     *  构建一条从 Ai 节点 指向 Ai 节点，权重为 1 的边；【表示 Ai / Ai = 1 】
     *  构建一条从 Bi 节点 指向 Bi 节点，权重为 1 的边；【表示 Bi / Bi = 1】
     *
     *  广度优先搜索
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
            graph.get(s).put(e, v);     // 生成一条s指向e，权重为v的路径，表示 s / e = v
            graph.get(e).put(s, 1 / v); // 生成一条反向路径，权重为1 / v，表示 e / s = 1 /v
            graph.get(s).put(s, 1.0);   // 生成一个指向自己、权重为1的路径，表示自己除自己等于1
            graph.get(e).put(e, 1.0);
        }

        Queue<NodeData> queue = new LinkedList<>();   // 用于广度优先搜索的队列
        int m = queries.size();
        double[] ans = new double[m];    // 答案列表
        Arrays.fill(ans, -1.0);          // 初始都为-1表示未定义
        Set<String> visited;

        // 对于每个query，寻找从起点qx到终点qy的最短路径，并计算权重积
        for(int i = 0; i < m; i++){
            String qx = queries.get(i).get(0), qy = queries.get(i).get(1);
            if(!graph.containsKey(qx) || !graph.containsKey(qy))continue;  // 未出现的变量，跳过处理
            queue.offer(new NodeData(qx, 1.0));     // 初始将起点节点入队
            visited = new HashSet<>();      // 存储已处理的节点
            visited.add(qx);                // 初始化起点节点已处理
            while(!queue.isEmpty()){
                NodeData nd = queue.poll();    // 获取当前处理的节点node以及到该节点所得到的权重积mul
                for(Map.Entry<String, Double> entry: graph.get(nd.var).entrySet()){
                    // 遍历当前节点数据存储变量的所有邻节点
                    String neighbor = entry.getKey();
                    double weight = entry.getValue();
                    // 枚举该节点的所有邻节点
                    if(qy.equals(neighbor)){
                        ans[i] = nd.mulWeight * weight;   // 找到终点，更新权重积后存储到答案并退出查找
                        break;
                    }
                    if(!visited.contains(neighbor)){ // 找到一个未处理的邻节点加入队列
                        visited.add(neighbor);
                        queue.offer(new NodeData(neighbor, nd.mulWeight * weight));  // 将未处理的邻节点及到达该节点时的权重积加入队列
                    }
                }
            }
        }
        return ans;
    }
}
// 用于广度优先搜索存储数据的节点数据结构
class NodeData{
    String var;         // 当前变量名
    double mulWeight;   // 到达该节点时的累乘权重积

    NodeData(String var, double weight){
        this.var = var;
        this.mulWeight = weight;
    }
}

