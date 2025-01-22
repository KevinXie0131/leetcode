package com.answer.monotonic;

import java.util.*;

public class Q42_Trapping_Rain_Water {
    public static void main(String[] args) {
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap(height));
    }
    /**
     * 暴力解法(使用双指针) 时间复杂度为O(n^2)，空间复杂度为O(1), 超时
     * 首先，如果按照列来计算的话，宽度一定是1了，我们再把每一列的雨水的高度求出来就可以了。
     * 可以看出每一列雨水的高度，取决于，该列 左侧最高的柱子和右侧最高的柱子中最矮的那个柱子的高度。
     * 一样的方法，只要从头遍历一遍所有的列，然后求出每一列雨水的体积，相加之后就是总雨水的体积了。
     */
    static public int trap_0(int[] height) {
        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            if (i == 0 || i == height.length - 1) continue;// 第一个柱子和最后一个柱子不接雨水

            int rHeight = height[i]; // 记录右边柱子的最高高度
            int lHeight = height[i]; // 记录左边柱子的最高高度
            for (int r = i + 1; r < height.length; r++) {
                if (height[r] > rHeight) rHeight = height[r];
            }
            for (int l = i - 1; l >= 0; l--) {
                if (height[l] > lHeight) lHeight = height[l];
            }
            int h = Math.min(lHeight, rHeight) - height[i];
            if (h > 0) sum += h;  // 注意只有h大于零的时候，在统计到总和中
        }
        return sum;
    }
    /**
     * 双指针优化
     * 为了得到两边的最高高度，使用了双指针来遍历，每到一个柱子都向两边遍历一遍，这其实是有重复计算的。
     * 我们把每一个位置的左边最高高度记录在一个数组上（maxLeft），右边最高高度记录在一个数组上（maxRight），
     * 这样就避免了重复计算。
     * 当前位置，左边的最高高度是前一个位置的左边最高高度和本高度的最大值。
     * 即从左向右遍历：maxLeft[i] = max(height[i], maxLeft[i - 1]);
     * 从右向左遍历：maxRight[i] = max(height[i], maxRight[i + 1]);
     */
   static public int trap2(int[] height) {
        int[] maxLeft = new int[height.length];
        int[] maxRight = new int[height.length];
        int size = maxRight.length;

        // 记录每个柱子左边柱子最大高度 [0, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3]
        maxLeft[0] = height[0];
        for (int i = 1; i < size; i++) {
            maxLeft[i] = Math.max(height[i], maxLeft[i - 1]);
        }
        // 记录每个柱子右边柱子最大高度 [3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 1]
        maxRight[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            maxRight[i] = Math.max(height[i], maxRight[i + 1]);
        }
        // 求和
        int sum = 0;
        for (int i = 0; i < size; i++) {
            int count = Math.min(maxLeft[i], maxRight[i]) - height[i];
            // count: 0 0 1 0 1 2 1 0 0 1 0 0
            if (count > 0) sum += count;
        }
        return sum;
    }
    /**
     * 单调栈
     * 通常是一维数组，要寻找任一个元素的右边或者左边第一个比自己大或者小的元素的位置，此时我们就要想到可以用单调栈了。
     * 而接雨水这道题目，我们正需要寻找一个元素，右边最大元素以及左边最大元素，来计算雨水面积。
     */
    public int trap3(int[] height) {
        int sum = 0;
        Deque<Integer> stack = new LinkedList<>(); // 存着下标，计算的时候用下标对应的柱子高度
        stack.push(0); // if there is no this line of code, !stack.isEmpty() should be added in the following code.

        for(int i = 1; i < height.length; i++){
            if(height[i] < height[stack.peek()]){   // 情况一 (保持从小到大的顺序（从栈头到栈底）)
                stack.push(i);
            }else if(height[i] == height[stack.peek()]){   // 情况二(例如 5 5 1 7)(遇到相同的元素，更新栈内下标，就是将栈里元素（旧下标）弹出，将新元素（新下标）加入栈中)
                stack.pop(); // 其实这一句可以不加，效果是一样的，但处理相同的情况的思路却变了
                stack.push(i);
            }else {  // 情况三
                while(!stack.isEmpty() && height[i] > height[stack.peek()]){ // 注意这里是while
                    int mid = stack.pop();
                    if(!stack.isEmpty()){
                        int h = Math.min(height[stack.peek()], height[i]) - height[mid];
                        int w = i - stack.peek() - 1; // 注意减一，只求中间宽度
                        sum += h * w; // 栈顶和栈顶的下一个元素以及要入栈的元素，三个元素来接水
                    }
                }
                stack.push(i);
            }
        }
        return sum;
    }
    /**
     * Approach 1: Brute force (照行来计算)
     */
    public static int trap_2(int[] height) {
        int ans = 0;
        int size = height.length;
        for (int i = 1; i < size - 1; i++) {
            int left_max = 0, right_max = 0;
            for (int j = i; j >= 0; j--) { //Search the left part for max bar size
                left_max = Math.max(left_max, height[j]);
            }
            for (int j = i; j < size; j++) { //Search the right part for max bar size
                right_max = Math.max(right_max, height[j]);
            }
            ans += Math.max(left_max, right_max) - height[i];
        }
        return ans;
    }
    /**
     * Approach 3: Using stacks
     */
    public static int trap_1(int[] height) {
        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        int n = height.length;

        for(int i = 0; i < n; i++){
            while(!stack.isEmpty() && height[i] > height[stack.peek()]){
                int top = stack.pop();
                if(stack.isEmpty()){
                    break;
                }

                int left = stack.peek();
                int curWidth = i - left - 1;
                int curHeight = Math.min(height[left], height[i]) - height[top];
                ans += curWidth * curHeight;
            }

            stack.push(i);
        }

        return ans;
    }
    /**
     * Approach 4: Using 2 pointers 双指针优化
     */
    public static int trap(int[] height) {
        int ans = 0;
        // 从两边向中间寻找最值
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;

        while(left < right){
            // 不确定上一轮是左边移动还是右边移动，所以两边都需更新最值
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            // 最值较小的一边所能装的水量已定，所以移动较小的一边。
            if(height[left] <  height[right]){
                ans += leftMax - height[left];
                left++;
            }else{
                ans += rightMax - height[right];
                right--;
            }
        }
        return ans;
    }
}
