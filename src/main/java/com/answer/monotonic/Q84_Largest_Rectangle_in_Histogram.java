package com.answer.monotonic;

import java.util.*;

public class Q84_Largest_Rectangle_in_Histogram { // Hard 困难
    /**
     * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
     * return the area of the largest rectangle in the histogram.
     * 柱状图中最大的矩形
     * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
     * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
     */
    public static void main(String[] args) {
        /**
         * 输入：heights = [2,1,5,6,2,3]
         * 输出：10
         * Explanation: The above is a histogram where width of each bar is 1.
         * The largest rectangle is shown in the red area, which has an area = 10 units.
         * 解释：最大的矩形为图中红色区域，面积为 10
         */
        int[] heights = {2,1,5,6,2,3};
        System.out.println(largestRectangleArea2(heights)); // 输出：10
    }
    /**
     * 暴力解法 (超时，因为时间复杂度是 O(n^2))
     * 朴素的想法:遍历每一根柱子的高度然后向两边进行扩散找到最大宽度
     */
    public int largestRectangleArea1(int[] heights) {
        int sum = 0;
        for (int i = 0; i < heights.length; i++) {
            int left = i;
            int right = i;
            for (; left >= 0; left--) {
                if (heights[left] < heights[i]) break;
            }
            for (; right < heights.length; right++) {
                if (heights[right] < heights[i]) break;
            }
        /*    while ( left >= 0) {
                if (heights[left] < heights[i]) break;
                left--;
            }
            while ( right < heights.length) {
                if (heights[right] < heights[i]) break;
                right++;
            }*/
            int w = right - left - 1;
            int h = heights[i];
            sum = Math.max(sum, w * h);
        }
        return sum;
    }
    /**
     * Approach 1: Brute Force 双指针解法
     * 难在本题要记录记录每个柱子 左边第一个小于该柱子的下标，而不是左边第一个小于该柱子的高度
     * 所以需要循环查找，也就是下面在寻找的过程中使用了while
     */
    public static int largestRectangleArea(int[] heights) {
        int length = heights.length;
        int[] minLeftIndex = new int [length];
        int[] minRightIndex = new int [length];
        //  记录每个柱子左边第一个小于该柱子的下标
        minLeftIndex[0] = -1 ; // 注意这里初始化，防止下面while死循环
        for (int i = 1; i < length; i++) {
            int t = i - 1;
            // 这里不是用if，而是不断向左寻找的过程
            while (t >= 0 && heights[t] >= heights[i]) {
                t = minLeftIndex[t]; // 这句是关键：minLeftIndex存的是下标，通过这句不断寻找
            }
            minLeftIndex[i] = t;
        }
        // minLeftIndex: [-1, -1, 1, 2, 1, 4]
        // 记录每个柱子右边第一个小于该柱子的下标
        minRightIndex[length - 1] = length; // 注意这里初始化，防止下面while死循环
        for (int i = length - 2; i >= 0; i--) {
            int t = i + 1;
            // 这里不是用if，而是不断向右寻找的过程
            while(t < length && heights[t] >= heights[i]) {
                t = minRightIndex[t];  // 这句是关键：minRightIndex，通过这句不断寻找
            }
            minRightIndex[i] = t;
        }
        // minRightIndex: [1, 6, 4, 4, 6, 6]
        // 求和
        int result = 0;
        for (int i = 0; i < length; i++) {
            int sum = heights[i] * (minRightIndex[i] - minLeftIndex[i] - 1);
            result = Math.max(sum, result);
        }
        return result;
    }
    /**
     * Using stack单调栈
     * 42. 接雨水 是找每个柱子左右两边第一个大于该柱子高度的柱子，而本题是找每个柱子左右两边第一个小于该柱子的柱子。
     * 因为本题是要找每个柱子左右两边第一个小于该柱子的柱子，所以从栈顶到栈底的顺序应该是从大到小的顺序
     *
     * 栈顶和栈顶的下一个元素以及要入栈的三个元素组成了我们要求最大面积的高度和宽度
     */
    public static int  largestRectangleArea_1(int[] heights) {
        Stack<Integer> st = new Stack<>();

        // 数组扩容，在头和尾各加入一个元素
        int [] newHeights = new int[heights.length + 2];
        newHeights[0] = 0; // 数组头部加入元素0
        newHeights[newHeights.length - 1] = 0; // 数组尾部加入元素0
        for (int index = 0; index < heights.length; index++){
            newHeights[index + 1] = heights[index];
        }

        heights = newHeights;

        st.push(0);
        int result = 0;
        // 第一个元素已经入栈，从下标1开始
        for (int i = 1; i < heights.length; i++) {
            // 注意heights[i] 是和heights[st.top()] 比较 ，st.top()是下标
            if (heights[i] > heights[st.peek()]) {
                st.push(i);
            } else if (heights[i] == heights[st.peek()]) {
                st.pop(); // 这个可以加，可以不加，效果一样，思路不同
                st.push(i);
            } else {
                while (heights[i] < heights[st.peek()]) { // 注意是while
                    int mid = st.peek();
                    st.pop();
                    int left = st.peek();
                    int right = i;
                    int w = right - left - 1;
                    int h = heights[mid];
                    result = Math.max(result, w * h);
                }
                st.push(i);
            }
        }
        return result;
    }
    /**
     * 单调栈
     * refer to Q42_Trapping_Rain_Water
     * 头尾都加0的目的:
     *  头部的0是为了不用判断栈是否为空, 因为题目中都是非负整数, 所以没有数会比0小, 即0一直会在栈底.
     *  尾部的0是为了压出最后已经形成的单调栈的
     */
    public int largestRectangleArea2a(int[] heights) {
        int [] newHeights = new int[heights.length + 2];
        newHeights[0] = 0; // 数组头部加入元素0
        newHeights[newHeights.length - 1] = 0; // 数组尾部加入元素0
        for (int index = 0; index < heights.length; index++){
            newHeights[index + 1] = heights[index];
        }
        heights = newHeights;

        Deque<Integer> stack = new ArrayDeque<>();
        int sum = 0;

        for(int i = 0; i < heights.length; i++){
            while(!stack.isEmpty() && heights[stack.peek()] > heights[i]){
                int cur = stack.pop();
                int left = stack.peek();
                int width = i - left - 1;
                int height = heights[cur];
                sum = Math.max(sum, width * height);
            }
            stack.push(i);
        }
        return sum;
    }
    /**
     * 单调栈（Monotone Stack）
     * refer to Q42_Trapping_Rain_Water
     * 需要考虑两种特殊的情况：
     *  弹栈的时候，栈为空；
     *  遍历完成以后，栈中还有元素；
     * 为此可以我们可以在输入数组的两端加上两个高度为 0
     *
     */
    public int largestRectangleArea2b(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int sum = 0;
        // 在确定一个柱形的面积的时候，除了右边要比当前严格小，其实还蕴含了一个条件，那就是左边也要比当前高度严格小
        // 那么每当遇到新加入的元素<栈顶便可以确定栈顶柱子右边界, 而栈顶柱子左边界就是栈顶柱子下面的柱子(<栈顶柱子), 左右边界确定以后就可以进行面积计算与维护最大面积
        for(int i = 0; i <= heights.length; i++){
            int currHeight = (i == heights.length) ? 0 : heights[i]; // 处理最后一个元素

            while(!stack.isEmpty() && heights[stack.peek()] > currHeight){  // 当前高度小于栈顶元素，弹出栈顶并计算面积
                int cur = stack.pop();
                int left ;

                if(stack.isEmpty()){
                    left = -1;
                }else {
                    left = stack.peek();
                }
                int width = i - left - 1;

                int height = heights[cur];
                sum = Math.max(sum, width * height);
            }
            stack.push(i); // 当前索引入栈
        }
        return sum;
    }
    /**
     * 单调栈
     * 本题与 Q42. 接雨水 解法相似，但由于计算面积需要找到当前柱子左右第一个比它小的柱子，
     * 因此栈内顺序与「接雨水」相反，即：从栈顶到栈底的高度右大到小，这样就可以得到当前高度的左右边界，遍历所有高度即可求出最大值。
     */
    public static int largestRectangleArea2(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int ans = 0;

        int[] newHeights = new int[heights.length + 1];
        newHeights = Arrays.copyOf(heights, heights.length + 1);
        newHeights[newHeights.length - 1] = -1; // clear the stack

        for (int i = 0; i < newHeights.length; i++) {
            while (!stack.isEmpty() && newHeights[i] < newHeights[stack.peek()]) {
                int idx = stack.pop();
                int left = stack.isEmpty() ? -1 : stack.peek();
                ans = Math.max(ans, (i - left - 1) * newHeights[idx]);
            }
            stack.push(i);
        }
        return ans;
    }
}
