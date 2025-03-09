package com.answer.greedy;

public class Q134_Gas_Station {
    /**
     * 贪心算法（方法一）
     * 情况一：如果gas的总和小于cost总和，那么无论从哪里出发，一定是跑不了一圈的
     * 情况二：rest[i] = gas[i]-cost[i]为一天剩下的油，i从0开始计算累加到最后一站，如果累加没有出现负数，说明从0出发，油就没有断过，那么0就是起点。
     * 情况三：如果累加的最小值是负数，汽车就要从非0节点出发，从后向前，看哪个节点能把这个负数填平，能把这个负数填平的节点就是出发节点。
     *
     * 其实我不认为这种方式是贪心算法，因为没有找出局部最优，而是直接从全局最优的角度上思考问题。
     */
    public int canCompleteCircuit_3(int[] gas, int[] cost) {
        int curSum = 0;
        int min = Integer.MAX_VALUE; // 从起点出发，油箱里的油量最小值
        for (int i = 0; i < gas.length; i++) {
            int rest = gas[i] - cost[i];
            curSum += rest;
            if (curSum < min) {
                min = curSum;
            }
        }
        if (curSum < 0) return -1;  // 情况1
        if (min >= 0) return 0;     // 情况2
        // 情况3
        for (int i = gas.length - 1; i >= 0; i--) {
            int rest = gas[i] - cost[i];
            min += rest;
            if (min >= 0) {
                return i;
            }
        }
        return -1;
    }
    /**
     * 贪心算法（方法二）
     * i从0开始累加rest[i]，和记为curSum，一旦curSum小于零，说明[0, i]区间都不能作为起始位置，因为这个区间选择任何一个位置作为起点，到i这里都会断油，那么起始位置从i+1算起，再从0计算curSum
     * 局部最优：当前累加rest[i]的和curSum一旦小于0，起始位置至少要是i+1，因为从i之前开始一定不行。全局最优：找到可以跑一圈的起始位置。
     *
     * 这种解法为贪心算法，才是有理有据的，因为全局最优解是根据局部最优推导出来的
     */
    public int canCompleteCircuit_4(int[] gas, int[] cost) {
        int curSum = 0;
        int totalSum = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            curSum += gas[i] - cost[i];
            totalSum += gas[i] - cost[i];
            if (curSum < 0) {   // 当前累加rest[i]和 curSum一旦小于0
                start =  (i + 1) % gas.length;   // 起始位置更新为i+1
                curSum = 0;     // curSum从0开始
            }
        }
        if (totalSum < 0) return -1; // 说明怎么走都不可能跑一圈了
        return start;
    }
    /**
     * Greedy 贪心
     * Approach 1: One pass.
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int res = 0;
        int total = 0;
        for(int i = 0; i < gas.length; i++){
            total += gas[i];
            total -= cost[i];
        }
        if(total < 0){
            return -1;
        }
        int sum = 0;
        for(int i = 0; i < gas.length; i++){
            sum += gas[i] - cost[i];
            if(sum < 0){
                res = (i + 1) % gas.length;
                sum = 0;
            }
        }
        return res;
    }
    /**
     * Brute force
     * O(n^2)的，遍历每一个加油站为起点的情况，模拟一圈。
     *
     */
    public int canCompleteCircuit_1(int[] gas, int[] cost) {
        int n = gas.length;
        int i = 0;

        // 从头到尾遍历每个加油站，并且检查以该加油站为起点，能否行驶一周
        while(i < n){
            int sumOfGas  = 0; // 总共加的油
            int SumOfCost = 0; // 总共消费的油
            int count = 0;     // 记录能走过几个站点
            while(count < n){  // 退出循环的条件是走过所有的站点
                int j = (i + count) % n; // 加油站是环形的
                sumOfGas += gas[j];
                SumOfCost += cost[j];
                if(SumOfCost > sumOfGas){ // 如果这个站点发现油不够了
                    break;
                }
                count++; // 这个站点满足情况
            }

            if(count == n){  // 如果能环绕一圈
                return i;
            }else{ // 不行的话 从下一个站点开始 检查
                i = i + count + 1;
            }
        }
        // 所有加油站作为起点都不满足
        return -1;
    }
    /**
     * 暴力方法
     * while循环适合模拟环形遍历
     */
    public int canCompleteCircuit_2(int[] gas, int[] cost) {
        for (int i = 0; i < cost.length; i++) {
            int rest = gas[i] - cost[i]; // 记录剩余油量
            int index = (i + 1) % cost.length;

            while (rest > 0 && index != i) { // 模拟以i为起点行驶一圈（如果有rest==0，那么答案就不唯一了）
                rest += gas[index] - cost[index];
                index = (index + 1) % cost.length;
            }
            // 如果以i为起点跑一圈，剩余油量>=0，返回该起始位置
            if (rest >= 0 && index == i) {
                return i;
            }
        }
        return -1;
    }
}
