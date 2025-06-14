package com.answer.greedy;

import com.template.TreeNode;

public class Q968_Binary_Tree_Cameras { // Hard 困难
    /**
     * 监控二叉树
     * 给定一个二叉树，我们在树的节点上安装摄像头。
     * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。计算监控树的所有节点所需的最小摄像头数量。
     */
    /**
     * 贪心算法
     * 因为头结点放不放摄像头也就省下一个摄像头， 叶子节点放不放摄像头省下了的摄像头数量是指数阶别的。
     * 所以我们要从下往上看，局部最优：让叶子节点的父节点安摄像头，所用摄像头最少，整体最优：全部摄像头数量所用最少！
     * 这道题目还有两个难点：
     *  二叉树的遍历: 可以使用后序遍历也就是左右中的顺序，这样就可以在回溯的过程中从下到上进行推导了。
     *  如何隔两个节点放一个摄像头
     *
     * 分别有三个数字来表示每个节点可能有几种状态：
     *   0：该节点无覆盖
 *       1：本节点有摄像头
     *   2：本节点有覆盖
     */
    int res = 0;
    public int minCameraCover_1(TreeNode root) {
        if(minCame(root) == 0){ // 对根节点的状态做检验,防止根节点是无覆盖状态
            res++;
        }
        return res;
    }
    // 节点的状态值： 0 表示无覆盖  1 表示有摄像头 2 表示有覆盖
    // 后序遍历，根据左右节点的情况,来判读 自己的状态
    public int minCame(TreeNode root){
        if(root == null){ // 空节点默认为 有覆盖状态，避免在叶子节点上放摄像头
            return 2;
        }
        int left = minCame(root.left);
        int right = minCame(root.right);

        if(left == 2 && right == 2){ // 如果左右节点都覆盖了的话, 那么本节点的状态就应该是无覆盖,没有摄像头(2,2)
            return 0;
        }else if(left == 0 || right == 0){ // 左右节点都是无覆盖状态,那 根节点此时应该放一个摄像头 (0,0) (0,1) (0,2) (1,0) (2,0)
            res++; // 状态值为 1 摄像头数 ++;
            return 1;
        }else{
            // 左右节点的 状态为 (1,1) (1,2) (2,1) 也就是左右节点至少存在 1个摄像头，
            return 2;// 那么本节点就是处于被覆盖状态
        }
    }
    /**
     * 简化分支版本
     */
    int ans;
    public int minCameraCover_2(TreeNode root) {
        ans = 0; // 初始化
        if(f(root) == 0) ans ++;
        return ans;
    }
    // 定义 f 函数有三种返回值情况
    // 0：表示 x 节点没有被相机监控，只能依靠父节点放相机
    // 1：表示 x 节点被相机监控，但相机不是放在自身节点上
    // 2：表示 x 节点被相机监控，但相机放在自身节点上
    public  int f(TreeNode x) {
        if(x == null) return 1; // 空树认为被监控，但没有相机
        // 左右递归到最深处
        int l = f(x.left);
        int r = f(x.right);
        // 有任意一个子节点为空，就需要当前节点放相机，不然以后没机会
        if(l == 0 || r == 0) {
            ans ++; // 放相机
            return 2;
        }
        // 贪心策略，左右子树都被监控，且没有监控到当前节点，
        // 那么最有利的情况就是将相机放置在当前节点父节点上，
        // 因为这样能多监控可能的子树节点和父父节点
        if(l == 1 && r == 1) return 0;
        // 剩下情况就是左右子树有可能为 2，即当前节点被监控
        return 1;
    }
    /**
     * Approach 2: Greedy
     * If a node has children that are not covered by a camera, then we must place a camera here.
     * Additionally, if a node has no parent and it is not covered, we must place a camera here.
     */
    int result = 0;

    public int minCameraCover(TreeNode root) {
        if(dfs(root) == 0){
            result++;
        }
        return result;
    }
    /*
      dfs返回节点的状态
         0=>这个结点待覆盖
         1=>这个结点已经覆盖
         2=>这个结点上安装了相机
      */
    public int dfs(TreeNode root){
        /*为了保证摄像头数目最小，叶子节点不能放置摄像头。所以root是null时，设置其状态是已覆盖。*/
        if(root == null){
            return 1;
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        // 左右孩子一共有 00,01,02,11,12,22 这些状态
        // 包含了 00 01 02 状态，左右孩子只要有一个未被覆盖，root就需要放置摄像头
        if(left == 0 || right == 0){
            result++;
            return 2;
        }
        // 11状态  root需要被父节点的摄像头覆盖，设置root的状态是0
        if(left == 1 && right == 1){
            return 0;
        }
        //12 22状态，root被儿子覆盖 设置root的状态是1
        if(left + right >= 3){
            return 1;
        }
        // 所有的状态都已经被包含，这里随便返回一个值
        return 0;
    }
}
