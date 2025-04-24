package com.answer.array;

public class Q1299_Replace_Elements_with_Greatest_Element_on_Right_Side {
    /**
     * 将每个元素用它右边最大的元素替换，如果是最后一个元素，用 -1 替换。
     *  replace every element in that array with the greatest element among the elements to its right,
     *  and replace the last element with -1
     */
    public static void main(String[] args) {
        int [] arr = {17,18,5,4,6,1};
        replaceElements(arr);
    }
    /**
     * 逆序遍历: 逆序地遍历整个数组，同时维护从数组右端到当前位置所有元素的最大值。
     * 设 ans[i] = max(arr[i + 1], arr[i + 2], ..., arr[n - 1])，那么在进行逆序遍历时，
     * 我们可以直接通过 ans[i] = max(ans[i + 1], arr[i + 1]) 来递推地得到答案。
     */
   static public int[] replaceElements(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        ans[n - 1] = -1;
        for (int i = n - 2; i >= 0; --i) {
            ans[i] = Math.max(ans[i + 1], arr[i + 1]);
        }
        return ans;
    }
    /**
     * 逆序遍历
     * 我们用一个变量 mx 记录当前位置右侧的最大值，初始时 mx=−1。
     * 然后我们从右向左遍历数组，对于每个位置 i，我们记当前位置的值为 x，将当前位置的值更新为 mx，然后更新 mx=max(mx,x)。
     */
    public int[] replaceElements_1(int[] arr) { // 维护右侧最大值 + 原地修改数组
        int mx = -1;// 维护右侧最大值，初始为-1
        for (int i = arr.length - 1; i >= 0; --i) {   // 逆序遍历
            int val  = arr[i];    // 获取当前元素值
            arr[i] = mx; // 更新arr[i]
            mx = Math.max(mx, val );     // 更新右侧最大值
        }
        return arr;
    }
}
