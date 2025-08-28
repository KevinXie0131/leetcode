package com.answer.monotonic;

import java.util.*;

public class Q42_Trapping_Rain_Water { // Hard 困难
    /**
     * Given n non-negative integers representing an elevation map where the width of each bar is 1,
     * compute how much water it can trap after raining.
     * 接雨水
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     */
    public static void main(String[] args) {
        /**
         * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
         * 输出：6
         * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
         */
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap6(height)); // 输出：6
    }
    /**
     * 暴力解法(使用双指针) 时间复杂度为O(n^2)，空间复杂度为O(1), 超时
     * 首先，如果按照列来计算的话，宽度一定是1了，我们再把每一列的雨水的高度求出来就可以了。
     * 可以看出每一列雨水的高度，取决于，该列 左侧最高的柱子和右侧最高的柱子中最矮的那个柱子的高度。
     * 一样的方法，只要从头遍历一遍所有的列，然后求出每一列雨水的体积，相加之后就是总雨水的体积了。
     */
    static public int trap_0(int[] height) { // 按列求: 求每一列的水，我们只需要关注当前列，以及左边最高的墙，右边最高的墙就够了
        int sum = 0;
        for (int i = 0; i < height.length; i++) {
            if (i == 0 || i == height.length - 1) { // 第一个柱子和最后一个柱子不接雨水
                continue;
            }
            int rHeight = height[i]; // 记录右边柱子的最高高度
            int lHeight = height[i]; // 记录左边柱子的最高高度
            for (int r = i + 1; r < height.length; r++) {
                if (height[r] > rHeight) {
                    rHeight = height[r];
                }
            }
            for (int l = i - 1; l >= 0; l--) {
                if (height[l] > lHeight){
                    lHeight = height[l];
                }
            }
            int h = Math.min(lHeight, rHeight) - height[i];
            if (h > 0) {
                sum += h;  // 注意只有h大于零的时候，在统计到总和中
            }
        }
        return sum;
    }
    /**
     * 双指针优化 (时间复杂度为O(n)， 空间复杂度为O(n))
     * 为了得到两边的最高高度，使用了双指针来遍历，每到一个柱子都向两边遍历一遍，这其实是有重复计算的。
     * 我们把每一个位置的左边最高高度记录在一个数组上（maxLeft），右边最高高度记录在一个数组上（maxRight），这样就避免了重复计算。
     * 当前位置，左边的最高高度是前一个位置的左边最高高度和本高度的最大值。
     * 即从左向右遍历：maxLeft[i] = max(height[i], maxLeft[i - 1]);
     * 从右向左遍历：maxRight[i] = max(height[i], maxRight[i + 1]);
     */
   static public int trap2(int[] height) {
        int[] maxLeft = new int[height.length]; // {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}
        int[] maxRight = new int[height.length];
        int size = height.length;
        // 记录每个柱子左边柱子最大高度               [0, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3]
        maxLeft[0] = height[0];
        for (int i = 1; i < size; i++) {
            maxLeft[i] = Math.max(height[i], maxLeft[i - 1]);
        }
        // 记录每个柱子右边柱子最大高度               [3, 3, 3, 3, 3, 3, 3, 3, 2, 2, 2, 1]
        maxRight[size - 1] = height[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            maxRight[i] = Math.max(height[i], maxRight[i + 1]);
        }
        // 求和
        int sum = 0;
        for (int i = 0; i < size; i++) {
            int count = Math.min(maxLeft[i], maxRight[i]) - height[i];
            // count: 0 0 1 0 1 2 1 0 0 1 0 0
            if (count > 0) {
                sum += count;
            }
        }
        return sum;
    }
    /**
     * 双指针优化（不需要数组记录最大值）
     * 时间复杂度为O(n)， 空间复杂度为O(1)
     */
    static public int trap2a(int[] height) {
        int maxLeft = 0;
        int maxRight = 0;
        int size = height.length;
        int sum = 0;
        int left = 0;
        int right = size - 1;

        while(left < right){
            if(height[left] < height[right]){ //积水是由低点决定的
                maxLeft = Math.max(maxLeft, height[left]);
                sum += maxLeft - height[left];
                left++;
            }else{
                maxRight = Math.max(maxRight, height[right]);
                sum += maxRight - height[right];
                right--;
            }
        }
        return sum;
    }
    /**
     * 单调栈 存储的是下标，满足从栈底到栈顶的下标对应的数组 height 中的元素递减
     * 时间复杂度：虽然 while 循环里套了一个 while 循环，但是考虑到每个元素最多访问两次，入栈一次和出栈一次，所以时间复杂度是 O(n)。
     * 空间复杂度：O(n)。栈的空间。
     */
    public static int trap_1a(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int sum = 0;
        // 单调栈的做法相当于「横着」计算面积. 这个方法可以总结成 16 个字：找上一个更大元素，在找的过程中填坑
        for(int i = 0; i < heights.length; i++){
        //  while(!stack.isEmpty() && heights[stack.peek()] <= heights[i]){ // works too 注意 while 中加了等号，这可以让栈中没有重复元素，从而在有很多重复元素的情况下，使用更少的空间。
            while(!stack.isEmpty() && heights[stack.peek()] < heights[i]){ //如果栈不空并且当前指向的高度大于栈顶高度就一直循环
                int cur = stack.pop(); // 低洼处弹出，尝试结算此低洼处能积攒的雨水
                if(stack.isEmpty()){ // need to check if stack is empty  看看栈里还有没有东西（左墙是否存在）
                    break;           // 没有左墙+有低洼+有右墙=白搭
                }

                int left = stack.peek();
                int width = i - left - 1; // -1  两堵墙之前的距离。
                int height = Math.min(heights[i], heights[left]) - heights[cur];
                if(height > 0){ // can be commented
                    sum += width * height;
                }
            }
            stack.push(i);
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
        stack.push(0); // 如果没有这行 !stack.isEmpty()要加在情况一和情况二中

        for(int i = 1; i < height.length; i++){
            if(height[i] < height[stack.peek()]){   // 情况一 (保持从小到大的顺序（从栈顶到栈底）)
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
            int leftMax = 0, rightMax = 0;
            for (int j = i; j >= 0; j--) { //Search the left part for max bar size
                leftMax = Math.max(leftMax, height[j]);
            }
            for (int j = i; j < size; j++) { //Search the right part for max bar size
                rightMax = Math.max(rightMax, height[j]);
            }
            ans += Math.max(leftMax, rightMax) - height[i];
        }
        return ans;
    }
    /**
     * Approach 3: Using stacks 单调栈 同上
     * From 睡不醒的鲤鱼
     * 使用单调栈存储高度下标，按照行方向来计算雨水容量。
     *
     * 具体维护的顺序为从栈顶到栈底的高度有小到大，因为一旦发现添加的柱子高度大于栈头元素了，此时就出现凹槽了，
     * 栈顶元素就是凹槽底部的柱子，栈顶第二个元素就是凹槽左边的柱子，而添加的元素就是凹槽右边的柱子。
     * 这样通过左右柱子就可以计算长和宽得到雨水的容量了。
     */
    public static int trap_1(int[] heights) {
        int ans = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        int n = heights.length;

        for(int i = 0; i < n; i++){
            while(!stack.isEmpty() && heights[i] > heights[stack.peek()]){
                int idx = stack.pop();
                if(stack.isEmpty()){
                    break;
                }

                int left = stack.peek();
                int width = i - left - 1;
                int height = Math.min(heights[left], heights[i]) - heights[idx];
                ans += width * height;
            }
            stack.push(i);
        }
        return ans;
    }
    /**
     * From 睡不醒的鲤鱼
     * Approach 4: Using 2 pointers 双指针优化
     * 木桶原理，从当前节点往左找最高的高度，往右找最高的高度，这两个高度我们可以看做是木桶的两个木板，
     * 能接的雨水由最短的那块决定，累加每个位置能存的雨水量即可。
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
    /**
     * 双指针，时间O(n)，空间O(1)
     */
    static public int trap6(int[] height) {
        int n = height.length;
        int res = 0;
        int left = 0, right = n - 1; // 左右指针：分别指向左右两边界的列
        int leftMax = height[left], rightMax = height[right];   // 左指针的左边最大高度、右指针的右边最大高度

        left++; // 最两边的列存不了水
        right--;

        while(left <= right){  // 向中间靠拢
            leftMax = Math.max(leftMax, height[left]); //更新左边最大值
            rightMax = Math.max(rightMax, height[right]);  //更新右边最大值
            if(leftMax < rightMax){
                // 左指针的leftMax比右指针的rightMax矮
                // 说明：左指针的右边至少有一个板子 > 左指针左边所有板子
                // 根据水桶效应，保证了左指针当前列的水量决定权在左边
                // 那么可以计算左指针当前列的水量：左边最大高度-当前列高度
                res += leftMax - height[left]; //左边高度比右边小 计算左边最大高度与当前高度差
                left++;
            }else{
                // 右边同理
                res += rightMax - height[right];  //计算右边最大高度与当前高度差
                right--;
            }
        }
        return res;
    }
}
