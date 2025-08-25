package com.answer.union_find;

//并查集(DisJoint)模板
public class _UnionFindTemplate{
    private int[] father;

    public _UnionFindTemplate(int N) {
        father = new int[N];
        for (int i = 0; i < N; ++i){
            father[i] = i;    // 每个新元素的根节点一开始都是指向自己的
        }
    }

    public int find(int n) {
        return n == father[n] ? n : (father[n] = find(father[n])); // 路径压缩

        // 如果此时父节点指向自己，说明已经是根结点了
  //      if (father[n] == n){
  //         return n;
  //      }
        // 否则，根据父节点继续往上找，直到找到根节点
  //      else{
  //          return find(father[x]);
  //      }
    }

    public void join (int n, int m) {
        n = find(n);
        m = find(m);
        if (n == m) {
            return;
        }
        father[m] = n; // 找到根节点后，x根做y根的子树，y根做x根的子树都可以
    }

    public boolean isSame(int n, int m){
        n = find(n);
        m = find(m);
        return n == m;
    }
}
