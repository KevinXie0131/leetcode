package com.answer.monotonic;

import java.util.*;

public class Q739_Daily_Temperatures {
    /**
     * Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i]
     * is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for
     * which this is possible, keep answer[i] == 0 instead.
     * 每日温度
     * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。
     * 如果气温在这之后都不会升高，请在该位置用 0 来代替。
     */
    public static void main(String[] args) {
        /**
         * 输入: temperatures = [73,74,75,71,69,72,76,73]
         * 输出: [1,1,4,2,1,1,0,0]
         */
    }
    /**
     * 通常是一维数组，要寻找任一个元素的右边或者左边第一个比自己大或者小的元素的位置，此时我们就要想到可以用单调栈了。时间复杂度为O(n)。
     * 单调栈的本质是空间换时间，因为在遍历的过程中需要用一个栈来记录右边第一个比当前元素高的元素，优点是整个数组只需要遍历一次。
     * 更直白来说，就是用一个栈来记录我们遍历过的元素. 所以我们需要用一个容器（这里用单调栈）来记录我们遍历过的元素(单调栈里只需要存放元素的下标i就可以)
     * 如果求一个元素右边第一个更大元素，单调栈就是递增的(从栈顶到栈底的顺序，即栈顶的元素比栈底的元素小)，
     * 如果求一个元素右边第一个更小元素，单调栈就是递减的(从栈顶到栈底的顺序，即栈顶的元素比栈底的元素大)。
     */
    public int[] dailyTemperatures0(int[] temperatures) {  // 版本 1 时间复杂度：O(n)   空间复杂度：O(n)
        int[] result = new int[temperatures.length];
        Deque<Integer> stack = new LinkedList<>();
        stack.push(0); //  注意，单调栈里 加入的元素是 下标。

        for(int i = 1; i < temperatures.length; i++){
            if(!stack.isEmpty()){
                if( temperatures[stack.peek()] >= temperatures[i]){
                    stack.push(i);
                } else {
                    // 如果当前遍历的元素 大于栈顶元素，表示 栈顶元素的 右边的最大的元素就是 当前遍历的元素，所以弹出 栈顶元素，并记录
                    // 如果栈不空的话，还要考虑新的栈顶与当前元素的大小关系 否则的话，可以直接入栈。
                    while(!stack.isEmpty() &&  temperatures[stack.peek()] < temperatures[i]){
                        result[stack.peek()] = i- stack.peek();
                        stack.pop();
                    }
                    stack.push(i);
                }
            }else {
                stack.push(i);
            }
        }
        return result;
    }
    /**
     * 版本 2
     */
    public int[] dailyTemperatures1(int[] temperatures) {
        int[] result = new int[temperatures.length];
        Deque<Integer> stack = new LinkedList<>();

        for(int i = 0; i < temperatures.length; i++){
            while(!stack.isEmpty() &&  temperatures[stack.peek()] < temperatures[i]){
                result[stack.peek()] = i- stack.peek();
                stack.pop();
            }
            stack.push(i);
        }
        return result;
    }
    /**
     * 单调栈
     * 在栈的基本操作的基础上，通过对栈中元素进行若干次入栈和出栈后，栈中的元素「自栈顶向栈底」，元素值单调增加 / 单调减少，
     * 此时的栈称为「单调栈」，前者称为「单调增加栈」，而后者称为「单调减少栈」。
     *
     * 单调栈被广泛应用在求解当前元素左 / 右侧「第一个」「小于（小于等于）/ 大于（大于等于）」的元素所在位置 / 元素值
     *
     * 需要特别注意的是：由于当前元素的出现，会破坏单调栈的单调性，因此此方法仅适用于求解「第一个」破坏单调性的元素，而不适用于求解这样的元素的总数等问题
     */
    /**
     * 单调队列 (滑动窗口)
     * 类似单调栈，若队列中的元素「自队头向队尾」，元素值单调增加 / 单调减少，此时的队列称为「单调队列」，前者称为「单调增加队列」，
     * 而后者称为「单调减少队列」。
     *
     * 单调队列被广泛应用在求解给定长度为 k 的「窗口」内的元素最小 / 最大值。由于队列中元素的单调性成立，此时的队头元素即为所求。
     *
     * 使用单调队列时，需要注意维护队列的以下两种「特性」：
     * 1. 单调性：自队头向队尾，元素值单调增加 / 单调减少。因此，若访问一个新的元素，应先将当前队列中比这个新元素更大 / 更小的元素及时从「队尾」出队，再将这个新元素从「队尾」入队；
     * 2. 元素有效性：若随着窗口的滑动，在窗口左边界左侧的元素，对于求解问题已经失效（过期），需及时从「队头」出队。
     */
    public int[] dailyTemperatures(int[] temperatures) { // 同版本2
        int n = temperatures.length;
        int[] result = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for(int i = 0; i< n; i++){
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()] ){
                int preIndex = stack.pop();
                result[preIndex] = i - preIndex;
            }
            stack.push(i);
        }
        return result;
    }
}
