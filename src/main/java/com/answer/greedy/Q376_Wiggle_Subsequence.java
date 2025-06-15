package com.answer.greedy;

public class Q376_Wiggle_Subsequence {
    /**
     * 摆动序列
     * 如果连续数字之间的差严格地在正数和负数之间交替，则数字序列称为 摆动序列 。第一个差（如果存在的话）可能是正数或负数。仅有一个元素或者含两个不等元素的序列也视作摆动序列。
     *  例如， [1, 7, 4, 9, 2, 5] 是一个 摆动序列 ，因为差值 (6, -3, 5, -7, 3) 是正负交替出现的。
     *  相反，[1, 4, 7, 2, 5] 和 [1, 7, 4, 5, 5] 不是摆动序列，第一个序列是因为它的前两个差值都是正数，第二个序列是因为它的最后一个差值为零。
     * 子序列 可以通过从原始序列中删除一些（也可以不删除）元素来获得，剩下的元素保持其原始顺序。
     * 给你一个整数数组 nums ，返回 nums 中作为 摆动序列 的 最长子序列的长度 。
     *
     * 进阶：你能否用 O(n) 时间复杂度完成此题?
     *
     * 输入：nums = [1,7,4,9,2,5]
     * 输出：6
     * 解释：整个序列均为摆动序列，各元素之间的差值为 (6, -3, 5, -7, 3) 。
     */
    public static void main(String[] args) {
        int[] nums = {1,7,4,9,2,5};
        System.out.println(wiggleMaxLength(nums));
    }
    /**
     * 贪心
     * 假设 up[i] 表示 nums[0:i] 中最后两个数字递增的最长摆动序列长度，down[i] 表示 nums[0:i] 中最后两个数字递减的最长摆动序列长度，只有一个数字时默认为 1。
     * 注意到 down 和 up 只和前一个状态有关，所以我们可以优化存储，分别用一个变量即可。
     */
    public int wiggleMaxLength0(int[] nums) {
        int down = 1, up = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1])
                up = down + 1;
            else if (nums[i] < nums[i - 1])
                down = up + 1;
        }
        return nums.length == 0 ? 0 : Math.max(down, up);
    }
    /**
     * 统计变化次数: 只需要统计上升和下降的次数即可！
     * 上升->下降-> 上升 。。。。。
     */
    public int wiggleMaxLength0_a(int[] nums) {
        int result = 1;

        boolean up = true;
        boolean down = true;
        for(int i = 1; i < nums.length; i++){
            if(up && nums[i] > nums[i-1]){  //上升
                up = false;
                down = true;
                result++;
            }else if(down && nums[i] < nums[i-1]){ //下降
                down = false;
                up = true;
                result++;
            }
        }
       return result;
    }
    /**
     * 摆动序列看他定义，说白了就是不断变换的上升下降趋势。
     * 不需要是真正连续的数组才是子序列，而是可以【跳着】找！
     *
     * 在局部可以通过“当前数值和前一个数值的差值以及前一个趋势的状态来判断”，那只有 2 种状态可以增加子序列最大值：
     *  差值是正数，前一个趋势是下降。
     *  差值是负数，前一个趋势是上升。
     * 这样得到全局最优的最多摆动序列。
     */
    public int wiggleMaxLength6(int[] nums) {
        // trend 判断上一轮的趋势，即是上升还是下降
        // 0 是初始状态，1 是上升，-1 是下降
        int trend = 0;
        int res = 1;// 存储最后的结果

        for(int i = 1; i < nums.length; i++){  // 遍历
            if(trend >= 0 && nums[i] < nums[i-1]){     // 如果上一轮趋势是上升或者未知且当前数字比前一个数字小（即当前是下降趋势）
                trend = -1;  // 更新趋势
                res += 1;  // 结果 + 1
            } else if(trend <= 0 && nums[i] > nums[i-1]){ // 如果上一轮趋势是下降或者未知且当前数字比前一个数字大（即当前是上升趋势）
                trend = 1;// 更新趋势
                res += 1;  // 结果 + 1
            }
        }
        return res;
    }
    /**
     * Approach #5 Greedy Approach 贪心
     * 本题要求通过从原始序列中删除一些（也可以不删除）元素来获得子序列，剩下的元素保持其原始顺序 (其实就是让序列有尽可能多的局部峰值)
     *  局部最优：删除单调坡度上的节点（不包括单调坡度两端的节点），那么这个坡度就可以有两个局部峰值。
     *  整体最优：整个序列有最多的局部峰值，从而达到最长摆动序列
     *  实际操作上，其实连删除的操作都不用做，因为题目要求的是最长摆动子序列的长度，所以只需要统计数组的峰值数量就可以了（相当于是删除单一坡度上的节点，然后统计长度）
     *  这就是贪心所贪的地方，让峰值尽可能的保持峰值，然后删除单一坡度上的节点
     *
     *  情况一：上下坡中有平坡
     *  情况二：数组首尾两端
     *  情况三：单调坡中有平坡
     *  本题异常情况的本质，就是要考虑平坡， 平坡分两种，一个是 上下中间有平坡，一个是单调有平坡
     */
    public static int wiggleMaxLength(int[] nums) {
        int res = 1;  // 记录峰值个数，序列默认序列最右边有一个峰值
        int preDiff = 0; // 前一对差值
        int curDiff = 0; // 当前一对差值

        for(int i = 1; i < nums.length; i++){
            curDiff = nums[i] - nums[i - 1];  //得到当前差值
            //如果当前差值和上一个差值为一正一负
            //等于0的情况表示初始时的preDiff
            if((preDiff <= 0 && curDiff > 0) || (preDiff >= 0 && curDiff < 0)){ // 出现峰值
                res++;
                preDiff = curDiff; // 注意这里，只在摆动变化的时候更新prediff
            }
        }
        return res;
    }
    /**
     * 思路 2（动态规划）
     * 很容易可以发现，对于我们当前考虑的这个数，要么是作为山峰（即 nums[i] > nums[i-1]），要么是作为山谷（即 nums[i] < nums[i - 1]）。
     * 设 dp 状态dp[i][0]，表示考虑前 i 个数，第 i 个数作为山峰的摆动子序列的最长长度
     * 设 dp 状态dp[i][1]，表示考虑前 i 个数，第 i 个数作为山谷的摆动子序列的最长长度
     *
     * 则转移方程为：
     * dp[i][0] = max(dp[i][0], dp[j][1] + 1)，其中0 < j < i且nums[j] < nums[i]，表示将 nums[i]接到前面某个山谷后面，作为山峰。
     * dp[i][1] = max(dp[i][1], dp[j][0] + 1)，其中0 < j < i且nums[j] > nums[i]，表示将 nums[i]接到前面某个山峰后面，作为山谷。
     *
     * 初始状态：由于一个数可以接到前面的某个数后面，也可以以自身为子序列的起点，所以初始状态为：dp[0][0] = dp[0][1] = 1。
     *
     */
    public int wiggleMaxLength_2(int[] nums) {
        // 0 i 作为波峰的最大长度
        // 1 i 作为波谷的最大长度
        int dp[][] = new int[nums.length][2];

        dp[0][0] = dp[0][1] = 1;
        for (int i = 1; i < nums.length; i++){
            dp[i][0] = dp[i][1] = 1; //i 自己可以成为波峰或者波谷

            for (int j = 0; j < i; j++){
                if (nums[j] > nums[i]){
                    dp[i][1] = Math.max(dp[i][1], dp[j][0] + 1);   // i 是波谷
                }
                if (nums[j] < nums[i]){
                    dp[i][0] = Math.max(dp[i][0], dp[j][1] + 1);  // i 是波峰
                }
            }
        }
        return Math.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
    }
    /**
     * 输入：nums = [1,17,5,10,13,15,10,5,16,8]
     *          1, 17,  5, 10, 13, 15, 10,  5, 16,  8
     *             升   降  升  升  升   降   降  升  降
     *  降序结尾 0  0   2   2   2   2   4    4   4   6
     *  升序结尾 0  1   1   3   3   3   3    5   5   5
     *  if 升 {
     *     dp[i][降] = dp[i-1][降]
     *     dp[i][升] = dp[i-1][降]+1
     *  }
     *  if 降{
     *      dp[i][降] = dp[i-1][升]+1
     *      dp[i][升] = dp[i-1][升]
     *  }
     */
    public int wiggleMaxLength8(int[] nums) {
        // dp[i][0]表示，到当前位置，以降序结尾的摆动数组的最长子序列的长度
        // dp[i][1]表示，到当前位置，以升序结尾的摆动数组的最长子序列的长度
        int[][] dp = new int[nums.length][2];
        dp[0][0] = dp[0][1] = 1;

        for (int i = 1; i < nums.length; i++) {
            //dp[i][0]降序结尾
            //dp[i][1]升序结尾
            if (nums[i] > nums[i-1]){
                dp[i][0] = dp[i-1][0];
              //  dp[i][1] = dp[i-1][0]+1;
                dp[i][1] = Math.max(dp[i][1], dp[i-1][0] + 1);
            }else if (nums[i] < nums[i-1]){
            //    dp[i][0] = dp[i-1][1]+1;
                dp[i][0] = Math.max(dp[i][0], dp[i - 1][1] + 1);
                dp[i][1] = dp[i-1][1];
            }else{
                //相等
                dp[i][0] = dp[i-1][0];
                dp[i][1] = dp[i-1][1];
            }
        }
        return Math.max(dp[nums.length - 1][0],dp[nums.length - 1][1]);
    }
}
