package com.answer.union_find;

public class Q1319_Number_of_Operations_to_Make_Network_Connected {
    /**
     * There are n computers numbered from 0 to n - 1 connected by ethernet cables connections forming a network where connections[i] = [ai, bi]
     * represents a connection between computers ai and bi. Any computer can reach any other computer directly or indirectly through the network.
     * You are given an initial computer network connections. You can extract certain cables between two directly connected computers, and place them
     * between any pair of disconnected computers to make them directly connected.
     * Return the minimum number of times you need to do this in order to make all the computers connected. If it is not possible, return -1.
     * 连通网络的操作次数
     * 用以太网线缆将 n 台计算机连接成一个网络，计算机的编号从 0 到 n-1。线缆用 connections 表示，其中 connections[i] = [a, b] 连接了计算机 a 和 b。
     * 网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。
     * 给你这个计算机网络的初始布线 connections，你可以拔开任意两台直连计算机之间的线缆，并用它连接一对未直连的计算机。
     * 请你计算并返回使所有计算机都连通所需的最少操作次数。如果不可能，则返回 -1 。
     *
     * 输入：n = 4, connections = [[0,1],[0,2],[1,2]]
     * 输出：1
     * 解释：拔下计算机 1 和 2 之间的线缆，并将它插到计算机 1 和 3 上。
     */
    public static void main(String[] args) {
        int n = 4;
        int[][] connections ={{0,1},{0,2},{1,2}};
        System.out.println(makeConnected(n, connections));
    }
    /**
     * 并查集
     * 当计算机的数量为 n 时，我们至少需要 n−1 根线才能将它们进行连接。如果线的数量少于 n−1，那么我们无论如何都无法将这 n 台计算机进行连接。
     * 因此如果数组 connections 的长度小于 n−1，我们可以直接返回 −1 作为答案，否则我们一定可以找到一种操作方式。
     */
    static int[] connected;
    /**
     * 假设最后形成了n个网络，说明存在n个连通分量，要将n个连通分量合并，很明显至少需要n-1个网络连接线。
     * 那么，这n-1根网络连接线从哪来呢，只有从各个网络中多余的连接线拔过来。
     * 所以在遍历Connections数组时，需要记录有多少根多余的网络连接线。
     */
    static public int makeConnected(int n, int[][] connections) {
        if(connections.length < n - 1) {
            return -1; // 如果初始布线数小于 n - 1，那么一定不能使所有计算机连通
        }
        connected = new int[n];
        for(int i = 0; i < n; i++) {
            connected[i] = i;
        }
        int redundant = 0;
        int group = n;
        for(int[] connection : connections) {
            if (find(connected, connection[0]) != find(connected, connection[1])) {
                union(connected, connection[0], connection[1] );    //合并两个连通分量
                group--;  //连通分量数减1
            } else {
                redundant++; // 如果两个连通分量同源（根节点相同），说明该连接多余，则将多余的连接线数量+1
            }
        }
        int cables = group - 1;  // 所需要的线缆数量
        if(redundant < cables){
            return - 1;
        }
        return cables;
    }
    /**
     * another form
     * 看到联通网络，首先要明确一点：
     *  1、假设connections数为n，机器个数为k。 则n >= k-1时，该网络一定可以联通；反之则一定不能。
     *  2、其次，如果connections将这k个机器分为了m组，则只需要移动m - 1个connection就可以使整个网络联通。
     * 所以就只需要遍历一遍connections，将所有机器一一在并查集中合并，然后计算并查集中的集合个数，返回集合个数-1就好了。
     *
     * 如果其包含 n 个节点，那么初始时连通分量数为 n，每成功进行一次合并操作，连通分量数就会减少 1。
     */
    public int makeConnected_1(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }
        connected = new int[n];
        for (int i = 0; i < n; i++) {
            connected[i] = i;
        }
        int group = n; // 统计连通分量
        for (int[] connection : connections) {
            if (find(connected, connection[0]) != find(connected, connection[1])) {
                union(connected, connection[0], connection[1]);
                group--;
            }
        }
        return group - 1;  // 最少需要的操作次数就是连通分量数量减1
    }

    static public void union(int[] parent, int index1, int index2) {
        parent[find(parent, index2)] = find(parent, index1);
    }

    static public int find(int[] parent, int index) {
        if (parent[index] != index) {
            parent[index] = find(parent, parent[index]);
        }
        return parent[index];
    }
    /**
     * refer to template
     */
    public int makeConnected_2(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }
        connected = new int[n];
        for (int i = 0; i < n; i++) {
            connected[i] = i;
        }
        int group = n; // 统计连通分量
        for (int[] connection : connections) {
            if (!isSame(connection[0], connection[1])) {
                join(connection[0], connection[1]);
                group--;
            }
        }
        return group - 1;  // 最少需要的操作次数就是连通分量数量减1
    }

     public int find1(int n) {
        return n == connected[n] ? n : (connected[n] = find1(connected[n]));
    }

     public void join (int n, int m) {
        n = find1(n);
        m = find1(m);
        if (n == m) {
            return;
        }
        connected[m] = n; // 找到根节点后，x根做y根的子树，y根做x根的子树都可以
    }

      public boolean isSame(int n, int m){
        n = find1(n);
        m = find1(m);
        return n == m;
    }
}
