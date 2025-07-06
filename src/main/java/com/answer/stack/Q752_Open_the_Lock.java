package com.answer.stack;

import java.util.*;

public class Q752_Open_the_Lock {
    /**
     * 打开转盘锁
     * 有一个带有四个圆形拨轮circular wheels的转盘锁lock 。每个拨轮wheel 都有10个数字slots： '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
     * 每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
     *
     * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串（ a string representing the state of the 4 wheels.）。
     * 列表 deadends 包含了一组死亡数字deadends ，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
     * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
     * Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.
     *
     * 示例 1:
     *  输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
     *  输出：6
     *  解释：可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
     *      注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
     *      因为当拨动到 "0102" 时这个锁就会被锁定。
     * 示例 2:
     *  输入: deadends = ["8888"], target = "0009"
     *  输出：1
     *  解释：把最后一位反向旋转一次即可 "0000" -> "0009"。
     * 示例 3:
     *  输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
     *  输出：-1
     *  解释：无法旋转到目标数字且不被锁定。
     */
    public static void main(String[] args) {
        String[] deadends = {"8888"};
        String target = "0009";
        System.out.println(openLock(deadends, target));
    }
    /**
     * 广度优先搜索
     * 找出从初始数字 0000 到解锁数字 target 的最小旋转次数
     */
    static public int openLock(String[] deadends, String target) {
        HashSet<String> set = new HashSet();
        for(String s : deadends){
            set.add(s);
        }
        if(set.contains("0000")) return -1;

        HashSet<String> visited = new HashSet();//需要一个 visited 集合来存储这些已经遍历过的密码
        visited.add("0000");

        Deque<String> queue = new ArrayDeque<>();
        queue.offer("0000");

        int step = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0; i < size; i++){
                String cur = queue.poll();
                if(cur.equals(target)){
                    return step;
                }
                for(String next : getNext(cur)) {// 将当前队列汇总的所有节点向周围扩散
                    if(!set.contains(next) && !visited.contains(next)){
                        queue.offer(next);
                        visited.add(next);
                    }
                }
            }
            step++;
        }
        return -1;
    }
    // 枚举 通过一次旋转得到的数字
    static public List<String> getNext(String cur) {
        List<String> res = new ArrayList<>();
        char[] array = cur.toCharArray();
        for(int i = 0; i < 4; i++){
            char ch = array[i];
            array[i] = ch == '0' ? '9' : (char)(ch - 1);
            res.add(new String(array));
            array[i] = ch == '9' ? '0' : (char)(ch + 1);
            res.add(new String(array));
            array[i] = ch;
        }

        return res;
    }
    /**
     * another form
     */
    static public List<String> getNext_1(String cur) {
        List<String> res = new ArrayList<>();
        char[] array = cur.toCharArray();
        for(int i = 0; i < 4; i++){
            int temp = cur.charAt(i) - '0';
            int up = (temp + 1) % 10;
            int down = (temp + 9) % 10;

            char[] clone = array.clone();
            clone[i] = (char)(up + '0');
            res.add(new String(clone));
            clone[i] = (char)(down + '0');
            res.add(new String(clone));
        }

        return res;
    }
    // 向上拨动
    public String plusOne(String cur, int j) {
        char[] ch = cur.toCharArray();

        if (ch[j] == '9'){
            ch[j] = '0';
        } else {
            ch[j] += 1;
        }
        return new String(ch);
    }
    // 向下拨动
    public String minusOne(String cur, int j) {
        char[] ch = cur.toCharArray();

        if (ch[j] == '0'){
            ch[j] = '9';
        } else {
            ch[j] -= 1;
        }
        return new String(ch);
    }
    /**
     * similar with 二叉树的BFS
     */
    public int openLock5(String[] deadends, String target) {
        Set<String> set = new HashSet<>(Arrays.asList(deadends));
        //开始遍历的字符串是"0000"，相当于根节点
        String startStr = "0000";
        if (set.contains(startStr))
            return -1;
        //创建队列
        Queue<String> queue = new LinkedList<>();
        //记录访问过的节点
        Set<String> visited = new HashSet<>();
        queue.offer(startStr);
        visited.add(startStr);
        //树的层数
        int level = 0;
        while (!queue.isEmpty()) {
            //每层的子节点个数
            int size = queue.size();
            while (size-- > 0) {
                //每个节点的值
                String str = queue.poll();
                //对于每个节点中的4个数字分别进行加1和减1，相当于创建8个子节点，这八个子节点
                //可以类比二叉树的左右子节点
                for (int i = 0; i < 4; i++) {
                    char ch = str.charAt(i);
                    //strAdd表示加1的结果，strSub表示减1的结果
                    String strAdd = str.substring(0, i) + (ch == '9' ? '0' :(char)(ch + 1)) + str.substring(i + 1);
                    String strSub = str.substring(0, i) + (ch == '0' ? '9' : (char)(ch - 1)) + str.substring(i + 1);
                    //如果找到直接返回
                    if (str.equals(target)) {
                        return level;
                    }
                    //不能包含死亡数字也不能包含访问过的字符串
                    if (!visited.contains(strAdd) && !set.contains(strAdd)) {
                        queue.offer(strAdd);
                        visited.add(strAdd);
                    }
                    if (!visited.contains(strSub) && !set.contains(strSub)) {
                        queue.offer(strSub);
                        visited.add(strSub);
                    }
                }
            }
            //当前层访问完了，到下一层，层数要加1
            level++;
        }
        return -1;
    }
    /**
     * 双向 BFS
     * 在朴素的 BFS 实现中，空间的瓶颈主要取决于搜索空间中的最大宽度
     * 双向 BFS同时从两个方向开始搜索，一旦搜索到相同的值，意味着找到了一条联通起点和终点的最短路径。
     *
     * 创建「两个队列」分别用于两个方向的搜索；
     * 创建「两个哈希表」用于「解决相同节点重复搜索」和「记录转换次数」；
     * 为了尽可能让两个搜索方向“平均”，每次从队列中取值进行扩展时，先判断哪个队列容量较少；
     * 如果在搜索过程中「搜索到对方搜索过的节点」，说明找到了最短路径。
     */
    String target, source;
    Set<String> set = new HashSet<>();
    public int openLock1(String[] deadends, String _target) {
        source = "0000";
        target = _target;
        if (source.equals(target)) return 0;
        for (String d : deadends) set.add(d);
        if (set.contains(source)) return -1;

        int ans = bfs();
        return ans;
    }

    int bfs() {
        // d1 代表从起点 s 开始搜索（正向）
        // d2 代表从结尾 t 开始搜索（反向）
        Deque<String> d1 = new ArrayDeque<>(), d2 = new ArrayDeque<>();
        /*
         * m1 和 m2 分别记录两个方向出现的状态是经过多少次转换而来
         * e.g.
         * m1 = {"1000":1} 代表 "1000" 由 s="0000" 旋转 1 次而来
         * m2 = {"9999":3} 代表 "9999" 由 t="9996" 旋转 3 次而来
         */
        Map<String, Integer> m1 = new HashMap<>(), m2 = new HashMap<>();
        d1.offer(source);
        m1.put(source, 0);
        d2.offer(target);
        m2.put(target, 0);
        /*
         * 只有两个队列都不空，才有必要继续往下搜索
         * 如果其中一个队列空了，说明从某个方向搜到底都搜不到该方向的目标节点
         * e.g.
         * 例如，如果 d1 为空了，说明从 source 搜索到底都搜索不到 target，反向搜索也没必要进行了
         */
        while (!d1.isEmpty() && !d2.isEmpty()) {
            int step = -1;
            if (d1.size() <= d2.size()) {
                step = update(d1, m1, m2);
            } else {
                step = update(d2, m2, m1);
            }
            if (step != -1) return step;
        }
        return -1;
    }

    int update(Deque<String> deque, Map<String, Integer> cur, Map<String, Integer> other) {
        int size = deque.size();
        while (size-- > 0) {
            String poll = deque.poll();
            char[] array = poll.toCharArray();
            int step = cur.get(poll);
            // 枚举替换哪个字符
            for (int i = 0; i < 4; i++) {
                // 能「正向转」也能「反向转」，这里直接枚举偏移量 [-1,1] 然后跳过 0
                for (int j = -1; j <= 1; j++) {
                    if (j == 0) continue;
                    // 求得替换字符串 str
                    int origin = array[i] - '0';
                    int next = (origin + j) % 10;
                    if (next == -1) next = 9;

                    char[] clone = array.clone();
                    clone[i] = (char)(next + '0');
                    String str = String.valueOf(clone);

                    if (set.contains(str)) continue;
                    if (cur.containsKey(str)) continue;
                    // 如果在「另一方向」找到过，说明找到了最短路，否则加入队列
                    if (other.containsKey(str)) {
                        return step + 1 + other.get(str);
                    } else {
                        deque.offer(str);
                        cur.put(str, step + 1);
                    }
                }
            }
        }
        return -1;
    }
    /**
     * 双向 BFS（用哈希集合代替队列，进行遍历）
     */
    public int openLock2(String[] deadends, String target) {
        Set<String> deads = new HashSet<>();
        for (String s : deadends) deads.add(s);
        // 在双向 BFS 中用集合来代替队列，可以快速判断元素是否存在并且在遍历过程中不能修改哈希集合，要用 temp 来存储 q1 的扩散结果。
        Set<String> q1 = new HashSet<>();
        Set<String> q2 = new HashSet<>();
        Set<String> visited = new HashSet<>();

        int step = 0;
        q1.add("0000"); // 初始化起点和终点
        q2.add(target);

        while (!q1.isEmpty() && !q2.isEmpty()) {
            Set<String> temp = new HashSet<>(); // 用 temp 存储 q1 的扩散结果

            for (String cur : q1) { // 将 q1 中的所有节点向周围扩散
                if (deads.contains(cur)) continue;
                if (q2.contains(cur)) return step;
                visited.add(cur);

                for (int j = 0; j < 4; j++) { // 将一个节点未遍历的相邻节点加入集合
                    String up = plusOne(cur, j);
                    if (!visited.contains(up)) temp.add(up);

                    String down = minusOne(cur, j);
                    if (!visited.contains(down)) temp.add(down);
                }
            }
            step++; // 增加步数
            // 在 while 循环的最后交换 q1 和 q2 的内容（此时 temp 相当于 q1），
            // 所以只要默认扩散 q1 就相当于轮流扩散 q1 和 q2 了（交替逐层搜索）。
            q1 = q2; // 此时 temp 相当于 q1，交换 q1 和q2，下一轮 while 会扩散 q2
            q2 = temp;
        }
        return -1;
    }
}
