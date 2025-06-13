package com.answer.greedy;

public class Q45_Jump_Game_II {
    /**
     * 跳跃游戏 II
     * 给定一个长度为 n 的 0 索引整数数组 nums。初始位置为 nums[0]。
     * 每个元素 nums[i] 表示从索引 i 向后跳转的最大长度。换句话说，如果你在 nums[i] 处，你可以跳转到任意 nums[i + j] 处:
     *  0 <= j <= nums[i]
     *  i + j < n
     * 返回到达 nums[n - 1] 的最小跳跃次数。生成的测试用例可以到达 nums[n - 1]。
     *
     * 输入: nums = [2,3,1,1,4]
     * 输出: 2
     * 解释: 跳到最后一个位置的最小跳跃数是 2。
     *      从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
     * 题目保证可以到达 nums[n-1]
     */
    public static void main(String[] args) {
     //   int[] nums = {2,3,1,1,4};
        int[] nums = {1,1,1,1,1};
        System.out.println(jump_1(nums));
    }
    /**
     * Greedy
     * A greedy algorithm is any algorithm that follows the problem-solving heuristic of making locally optimal choice at
     * each step, with the intent of reaching the global optimum at the end.
     *
     * A greedy algorithm does not necessarily lead to a globally optimal solution, but rather a reasonable approximation
     * in exchange of less computing time.
     *
     * 可以对每一个能作为 起跳点 的格子都尝试跳一次，把 能跳到最远的距离 不断更新
     *
     * 所以，当一次跳跃结束时，从下一个格子开始，到现在能跳到最远的距离，都是下一次跳跃的起跳点
     * 1. 对每一次跳跃用 for 循环来模拟
     * 2. 跳完一次之后，更新下一次起跳点的范围
     * 3. 在新的范围内跳，更新能跳到最远的距离
     *
     * 目标是使用最少的跳跃次数到达数组的最后一个位置
     * 贪心的思路，局部最优：当前可移动距离尽可能多走，如果还没到终点，步数再加一。整体最优：一步尽可能多走，从而达到最少步数。
     * 所以真正解题的时候，要从覆盖范围出发，不管怎么跳，覆盖范围内一定是可以跳到的，以最小的步数增加覆盖范围，覆盖范围一旦覆盖了终点，得到的就是最少步数！
     * 这里需要统计两个覆盖范围，当前这一步的最大覆盖和下一步最大覆盖。
     * 如果移动下标达到了当前这一步的最大覆盖最远距离了，还没有到终点的话，那么就必须再走一步来增加覆盖范围，直到覆盖范围覆盖了终点。
     */
    public static int jump(int[] nums) {
        if(nums.length == 1) return 0;

        int res = 0;
        int start = 0;
        int end = 1;

        while(end < nums.length){
            int cover = 0;
            for(int i = start; i < end; i++){
                // 能跳到最远的距离
                cover = Math.max(cover, nums[i] + i);
            }
            start = end; // 下一次起跳点范围开始的格子
            end = cover + 1;  // 下一次起跳点范围结束的格子
            res++; // 跳跃次数
        }

        return res;
    }
    /**
     * 贪心 方法一
     * 移动下标达到了当前覆盖的最远距离下标时，步数就要加一，来增加覆盖距离。最后的步数就是最少步数。
     * 这里还是有个特殊情况需要考虑，当移动下标达到了当前覆盖的最远距离下标时
     *  如果当前覆盖最远距离下标不是是集合终点，步数就加一，还需要继续走。
     *  如果当前覆盖最远距离下标就是是集合终点，步数不用加一，因为不能再往后走了。
     */
    public int jump_2(int[] nums) {
        if (nums.length == 1) return 0;
        int curDistance = 0;    // 当前覆盖最远距离下标 // 已建造的桥的右端点
        int ans = 0;            // 记录走的最大步数
        int nextDistance = 0;   // 下一步覆盖最远距离下标 // 下一座桥的右端点的最大值
        for (int i = 0; i < nums.length; i++) {
            nextDistance = Math.max(nums[i] + i, nextDistance);  // 更新下一步覆盖最远距离下标  // 遍历的过程中，记录下一座桥的最远点
            if (i == curDistance) {                         // 遇到当前覆盖最远距离下标  // 无路可走，必须建桥
                ans++;                                  // 需要走下一步
                curDistance = nextDistance;             // 更新当前覆盖最远距离下标（相当于加油了） // 建桥后，最远可以到达 next_right
                if (nextDistance >= nums.length - 1) break;  // 当前覆盖最远距到达集合终点，不用做ans++操作了，直接结束
            }
        }
        return ans;
    }
    /**
     * 贪心 另一种形式
     * 理解本题的关键在于：以最小的步数增加最大的覆盖范围，直到覆盖范围覆盖了终点，这个范围内最少步数一定可以跳到，不用管具体是怎么跳的，不纠结于一步究竟跳一个单位还是两个单位。
     */
    public int jump_3(int[] nums) {
        if (nums.length == 1) return 0;
        int count = 0; //记录跳跃的次数
        int curDistance = 0;   //当前的覆盖最大区域
        int maxDistance = 0;   //最大的覆盖区域

        for (int i = 0; i < nums.length; i++) {
            //找能跳的最远的
            maxDistance = Math.max(maxDistance,i + nums[i]);         //在可覆盖区域内更新最大的覆盖区域

            if (maxDistance >= nums.length-1){  //说明当前一步，再跳一步就到达了末尾
                count++;
                break;
            }
            //走到当前覆盖的最大区域时，更新下一步可达的最大区域
            if (i == curDistance){ //遇到边界，就更新边界，并且步数加一
                curDistance = maxDistance;
                count++;
            }
        }
        return count;
    }
    /**
     * Brute force 反向查找出发位置
     * 如果有多个位置通过跳跃都能够到达最后一个位置，那么我们应该如何进行选择呢？直观上来看，我们可以「贪心」地选择距离最后一个位置最远的那个位置，
     * 也就是对应下标最小的那个位置。因此，我们可以从左到右遍历数组，选择第一个满足要求的位置。
     * Time complexity n^2 for 1,1,1,1,1
     */
    public static int jump_1(int[] nums) {
        int position = nums.length - 1; //要找的位置
        int steps = 0;
        while (position != 0) { //是否到了第 0 个位置
            for (int i = 0; i < position; i++) {
                if (nums[i] + i >= position) {
                    position = i; //更新要找的位置
                    steps++;
                    break;
                }
            }
        }
        return steps;
    }
}
