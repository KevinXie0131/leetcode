package com.answer.array;

public class Q189_Rotate_Array {
    /**
     * 方法一：整体翻转 + 局部翻转
     * 本题是右旋转，其实就是反转的顺序改动一下，优先反转整个字符串，步骤如下：
     *     反转整个字符串
     *     反转区间为前k的子串
     *     反转区间为k到末尾的子串
     *  时间复杂度：O(n)  空间复杂度：O(1)
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        if(k > n){
            k = k % n; // 如果k大于nums.size, 其实就是右移 k % nums.size() 次
        }

        reverse(nums, 0 , n-1);
        reverse(nums, 0 , k-1);
        reverse(nums, k , n-1);
    }

    public void reverse (int[] nums, int left, int right){
        for(int i = left, j = right; i < j ; i++, j--){
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
    /**
     * 方法一：使用额外的数组: 将原数组下标为 i 的元素放至新数组下标为 (i+k)mod n 的位置
     * 时间复杂度： O(n)
     * 空间复杂度： O(n)
     */
    public void rotate1(int[] nums, int k) {
        int n = nums.length;
        int[] newArr = new int[n];

        for(int i = 0; i < n; i++){
            newArr[(i + k) % n] = nums[i]; // 利用余数
        }
        // 备注：这个方法会导致空间复杂度变成 O(n) 因为我们要创建一个 copy 数组。但是不失为一种思路。
        System.arraycopy(newArr, 0, nums, 0, n);
    }

}
