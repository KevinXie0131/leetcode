package com.answer.topological_sort;

import java.util.*;

public class Q207_Course_Schedule_2 {
    /**
     * 这种叫 有向无环图，把一个 有向无环图 转成 线性的排序 就叫 拓扑排序。
     * 有向图有 入度 和 出度 的概念：
     *  如果存在一条有向边 A --> B，则这条边给 A 增加了 1 个出度，给 B 增加了 1 个入度。
     *
     * 这很像 BFS
     *  让入度为 0 的课入列，它们是能直接选的课。
     *  然后逐个出列，出列代表着课被选，需要减小相关课的入度。
     *  如果相关课的入度新变为 0，安排它入列、再出列……直到没有入度为 0 的课可入列。
     *
     * BFS 前的准备工作
     *  每门课的入度需要被记录，我们关心入度值的变化。
     *  课程之间的依赖关系也要被记录，我们关心选当前课会减小哪些课的入度。
     *  因此我们需要选择合适的数据结构，去存这些数据：
     *  入度数组：课号 0 到 n - 1 作为索引，通过遍历先决条件表求出对应的初始入度。
     *  邻接表：用哈希表记录依赖关系（也可以用二维矩阵，但有点大）
     *      key：课号
     *      value：依赖这门课的后续课（数组）
     * 怎么判断能否修完所有课？
     *  BFS 结束时，如果仍有课的入度不为 0，无法被选，完成不了所有课。否则，能找到一种顺序把所有课上完。
     *  或者：用一个变量 count 记录入列的顶点个数，最后判断 count 是否等于总课程数。
     *
     * 总结：拓扑排序问题
     *  根据依赖关系，构建邻接表、入度数组。
     *  选取入度为 0 的数据，根据邻接表，减小依赖它的数据的入度。
     *  找出入度变为 0 的数据，重复第 2 步。
     *  直至所有数据的入度为 0，得到排序，如果还有数据的入度不为 0，说明图中存在环。
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 入度数组，用于记录每门课程的入度
        int[] inDegree = new int[numCourses];
        // 邻接表，存储每门课程的后续课程
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adjList.add(new ArrayList<>());
        }
        // 计算每门课程的入度，并构建邻接表
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int preCourse = prerequisite[1];
            inDegree[course]++;
            adjList.get(preCourse).add(course);
        }
        // 存储入度为 0 的课程的队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        // 记录已完成课程的数量
        int count = 0;
        while (!queue.isEmpty()) {
            int selectedCourse = queue.poll();
            count++;
            // 获取当前课程的后续课程列表
            List<Integer> nextCourses = adjList.get(selectedCourse);
            for (int nextCourse : nextCourses) {
                // 后续课程的入度减 1
                inDegree[nextCourse]--;
                if (inDegree[nextCourse] == 0) {
                    queue.offer(nextCourse);
                }
            }
        }
        // 如果已完成课程的数量等于总课程数，则可以完成所有课程
        return count == numCourses;
    }
    /**
     * anther form
     */
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];//定义入度数组，索引处（课程号）对应入度，比如课程0的入度为0

        Map<Integer,List<Integer>> map = new HashMap<>(); //定义map数组，key课程号，value：依赖key的课程号，比如key为1，依赖的value为3，4
        for(int i = 0; i < prerequisites.length;i++){
            inDegree[prerequisites[i][0]]++;  //遍历依赖关系表；在入度数组对应索引处++
            map.putIfAbsent(prerequisites[i][1],new ArrayList<>()); //没有对map初始化，这里对map初始化一个list数组，存放依赖的课程
            map.get(prerequisites[i][1]).add(prerequisites[i][0]); //在对应被依赖课程key处添加依赖key的课程
        }

        Queue<Integer> que = new LinkedList<>(); //新建列表，把入度为0的课放进来
        for(int i = 0 ; i < inDegree.length; i++){
            if(inDegree[i] == 0){
                que.offer(i);
            }
        }

        while(!que.isEmpty()){
            int course = que.poll(); //弹出已选课程，在map找到依赖它的课程，在入度数组--
            numCourses--;
            for(int nextCourse : map.getOrDefault(course, new ArrayList<>())){
                if(--inDegree[nextCourse] == 0){
                    que.offer(nextCourse);
                }
            }
        }
        return numCourses == 0;
    }
}
