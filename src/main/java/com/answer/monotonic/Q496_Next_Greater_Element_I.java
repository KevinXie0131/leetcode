package com.answer.monotonic;

import java.util.*;

public class Q496_Next_Greater_Element_I {
    public static void main(String[] args) {
        int[] nums1 = {4,1,2};
        int[]nums2 = {1,3,4,2};
        int[] output = nextGreaterElement_0(nums1, nums2);
        System.out.println(Arrays.toString(output));
    }
    /**
     * 使用stack做brute force
     */
   static public int[] nextGreaterElement_0(int[] nums1, int[] nums2) {
        Deque<Integer> stack = new LinkedList<>();
        List<Integer> result = new ArrayList<>();

        for(int num : nums2){
            stack.push(num);
        }

        Deque<Integer> temp = new LinkedList<>(); ;
        for(int num : nums1) {
            int max = -1;
            boolean isFound = false;
            while(!stack.isEmpty() && !isFound){
                int top = stack.pop();
                if(top > num){
                    max = top;
                } else if(top == num){
                    isFound = true;
                }
                temp.push(top);
            }
            result.add(max);
            while(!temp.isEmpty()){
                stack.push(temp.pop());
            }
        }
        return result.stream().mapToInt(i -> i).toArray();
    }
    /**
     * 使用单调栈: 栈顶到栈底的顺序，要从小到大，也就是保持栈里的元素为递增顺序。只要保持递增，才能找到右边第一个比自己大的元素。
     *
     * 情况一：当前遍历的元素T[i]小于栈顶元素T[st.top()]的情况
     *    此时满足递增栈（栈顶到栈底的顺序），所以直接入栈。
     * 情况二：当前遍历的元素T[i]等于栈顶元素T[st.top()]的情况
     *    如果相等的话，依然直接入栈，因为我们要求的是右边第一个比自己大的元素，而不是大于等于！
     * 情况三：当前遍历的元素T[i]大于栈顶元素T[st.top()]的情况
     *    此时如果入栈就不满足递增栈了，这也是找到右边第一个比自己大的元素的时候。
     *    判断栈顶元素是否在nums1里出现过，（注意栈里的元素是nums2的元素），如果出现过，开始记录结果。
     *    记录结果这块逻辑有一点小绕，要清楚，此时栈顶元素在nums2数组中右面第一个大的元素是nums2[i]（即当前遍历元素）
     */
    /**
     * 版本2
     */
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums1.length; i++){
            map.put(nums1[i], i);
        }

        int[] result = new int[nums1.length];
        Arrays.fill(result, -1); // result数组如果某位置没有被赋值，那么就应该是是-1，所以就初始化为-1。
        Deque<Integer> stack = new LinkedList<>();

        for(int i = 0; i < nums2.length; i++){
            while(!stack.isEmpty() && nums2[stack.peek()] < nums2[i] ){
                int key = stack.peek();
                if(map.containsKey(nums2[key])){
                    int value = map.get(nums2[key]);
                    result[value] = nums2[i];
                }
                stack.pop();
            }
            stack.push(i);
        }
        return result;
    }
    /**
     * 版本1
     */
    public int[] nextGreaterElement1(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[nums1.length];
        Arrays.fill(res,-1);

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0 ; i< nums1.length ; i++){
            hashMap.put(nums1[i], i);
        }
        stack.push(0);

        for (int i = 1; i < nums2.length; i++) {
            if (nums2[i] <= nums2[stack.peek()]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                    if (hashMap.containsKey(nums2[stack.peek()])){
                        Integer index = hashMap.get(nums2[stack.peek()]);
                        res[index] = nums2[i];
                    }
                    stack.pop();
                }
                stack.push(i);
            }
        }
        return res;
    }
    /**
     *
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        Stack<Integer> stack = new Stack<>();
        Map<Integer,Integer> map = new HashMap<>();

        for (int i = 0; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[i] > nums2[stack.peek()]) {
                int prevIndex = stack.pop();
                map.put(nums2[prevIndex], nums2[i]);
            }
            stack.push(i);
        }

        for (int i = 0 ; i < nums1.length; i++) {
            result[i] = map.getOrDefault(nums1[i], -1);
        }
        return result;
    }
}
