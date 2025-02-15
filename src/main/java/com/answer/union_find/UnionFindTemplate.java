package com.answer.union_find;

public class UnionFindTemplate {
    /**
     * 并查集主要有两个功能：
     *  将两个元素添加到一个集合中。
     *  判断两个元素在不在同一个集合
     *
     * 并查集主要有三个功能。
     *  寻找根节点，函数：find(int u)，也就是判断这个节点的祖先节点是哪个
     *  将两个节点接入到同一个集合，函数：join(int u, int v)，将两个节点连在同一个根节点上
     *  判断两个节点是否在同一个集合，函数：isSame(int u, int v)，就是判断两个节点是不是同一个根节点
     *
     *  空间复杂度： O(n) ，申请一个father数组
     *  路径压缩后的并查集时间复杂度在O(logn)与O(1)之间，且随着查询或者合并操作的增加，时间复杂度会越来越趋于O(1)。
     *  在第一次查询的时候，相当于是n叉树上从叶子节点到根节点的查询过程，时间复杂度是logn，但路径压缩后，后面的查询操作都是O(1)，
     *  而 join 函数 和 isSame函数 里涉及的查询操作也是一样的过程
     */
    int size = 10;
    int[] father = new int[size];
    int n = father.length;
    // 并查集初始化
    void init() {
        for (int i = 0; i < n; ++i) {
            father[i] = i;
        }
    }
    // 将v->u 这条边加入并查集
    // join 函数 一定要先 通过find函数寻根再进行关联
    void join(int u, int v) {
        u = find(u); // 寻找u的根
        v = find(v); // 寻找v的根
        if (u == v) {
            return; // 如果发现根相同，则说明在一个集合，不用两个节点相连直接返回
        }
        father[v] = u;
    }
    // 并查集里寻根的过程
    int find(int u) {
        if (u == father[u]){
            return u; // 如果根就是自己，直接返回
        } else {
            return find(father[u]); // 如果根不是自己，就根据数组下标一层一层向下找
        }
    }
    // 判断 u 和 v是否找到同一个根
    boolean isSame(int u, int v) {
        u = find(u);
        v = find(v);
        return u == v;
    }
    /**
     * 路径压缩 (将非根节点的所有节点直接指向根节点)
     * 只需要在递归的过程中，让 father[u] 接住 递归函数 find(father[u]) 的返回结果
     * 因为 find 函数向上寻找根节点，father[u] 表述 u 的父节点，那么让 father[u] 直接获取 find函数 返回的根节点，这样就让节点 u 的父节点 变成根节点。
     */
    // 并查集里寻根的过程
    int find2(int u) {
        if (u == father[u]) {
            return u;
        } else {
            // 推荐大家直接使用路径压缩的并查集模板就好
            return father[u] = find2(father[u]); // 路径压缩就一行代码
        }
    }

}
