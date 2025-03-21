package com.answer.monotonic;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

public class Q84_Largest_Rectangle_in_Histogram {
    public static void main(String[] args) {
        int[] heights = {2,1,5,6,2,3};
        System.out.println(largestRectangleArea2(heights));
    }
    /**
     * 暴力解法 (超时，因为时间复杂度是 O(n^2))
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
     * 本题与 0042. 接雨水 解法相似，但由于计算面积需要找到当前柱子左右第一个比它小的柱子，
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
