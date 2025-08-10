package com.answer.array;

import java.util.Arrays;

public class Q1089_Duplicate_Zeros {
    /**
     * 复写零
     * 给你一个长度固定的整数数组 arr ，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。
     * 注意：请不要在超过该数组长度的位置写入元素。请对输入的数组 就地 进行上述修改，不要从函数返回任何东西。
     * 示例 1：
     *  输入：arr = [1,0,2,3,0,4,5,0]
     *  输出：[1,0,0,2,3,0,0,4]
     *  解释：调用函数后，输入的数组将被修改为：[1,0,0,2,3,0,0,4]
     */
    /**
     * Do the above modifications to the input array in place and do not return anything.
     * 对输入的数组 就地 进行上述修改，不要从函数返回任何东西。
     */
    public static void main(String[] args) {
       int[] arr = {1,0,2,3,0,4,5,0};
      //  int[] arr = {0,0,0,0,0,0,0};
     //   int[] arr = {8,4,5,0,0,0,0,7};
        duplicateZeros_1(arr);
        System.out.println(Arrays.toString(arr));
    }
    /**
     * 模拟
     * 最简单的思路就是另外开一个等大的数组，按要求将复写0后内容存入，最后复制回原数组。
     * 时间复杂度: O(n)
     * 空间复杂度: O(n)
     */
    public void duplicateZeros(int[] arr) {
        int len = arr.length;
        int[] temp = new int[len];

        int j = 0;
        for(int i = 0; i < len & j < len; i++){
            temp[j] = arr[i];
            j++;
            if(arr[i] == 0 && j < len){
                temp[j] = 0;
                j++;
            }
        }
        for(int i = 0; i < len; i++){
            arr[i] = temp[i];
        }
    }
    /**
     * 双指针
     * 首先如果没有原地修改的限制，那么我们可以另开辟一个栈来进行模拟放置
     * 而实际上我们可以不需要开辟栈空间来模拟放置元素，我们只需要用两个指针来进行标记栈顶位置和现在需要放置的元素位置即可。
     * 我们用 top 来标记栈顶位置，用 i 来标记现在需要放置的元素位置，那么我们找到原数组中对应放置在最后位置的元素位置，
     * 然后在数组最后从该位置元素往前来进行模拟放置即可。
     */
    static public void duplicateZeros_1(int[] arr) {
        int len = arr.length;
        //两个指针
        int top = 0;
        int i = -1;
        while(top < len){
            i++;
            if(arr[i] != 0){
                top++;
            }else {
                top += 2; //遇到0则top多向右移动一步 模拟添加一个0
            }
        }
        int j = len - 1;
        if (top == len + 1) { //有可能最后也是一个0，会多一个元素
            arr[j] = 0;
            j--;
            i--;
        }
        while(j >= 0){
            arr[j] = arr[i];  //从右向左填充数据
            j--;
            if(arr[i] == 0){ //是0 则多向左移动一步 多添加一个0
                arr[j] = arr[i];
                j--;
            }
            i--;
        }
    }
    /**
     * 通过维护两个指针来模拟栈。
     * 需要注意的是，右端点为 0 时这个 0 可能不需要复写（因为超出了数组范围），简单的例子是：
     * [1, 0]
     * 这种情况需要跳过右端点元素。
     */
    public void duplicateZeros3(int[] arr) {
        int n = arr.length;
        //两个指针
        int top = 0;
        int i = -1;
        while (top < n) {
            i++;
            //遇到0则top多向右移动一步 模拟添加一个0
            if (arr[i] == 0) {
                top++;
            }
            top++;
        }
        //有可能最后的arr[i]是一个0 会多移动两步，再移动回来
        if (top > n) {
            top = top - 2;
            arr[top] = 0;
            top--;
            i--;
        } else {
            //不是0则移动出去一步
            top--;
        }
        while (top >= 0) {
            //从右向左填充数据
            arr[top] = arr[i];
            top--;
            //是0 则多向左移动一步 多添加一个0
            if (arr[i] == 0) {
                arr[top] = 0;
                top--;
            }
            i--;
        }
    }
}
