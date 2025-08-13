package com.answer.two_pointers;

public class TwoPointTemplate {
    /**
     * General solution 通用解法
     * 为了让解法更具有一般性，我们将原问题的「最多保留 1 位」修改为「最多保留 k 位」。
     * 对于此类问题，我们应该进行如下考虑：
     *     由于是保留 k 个相同数字，对于前 k 个数字，我们可以直接保留。
     *     对于后面的任意数字，能够保留的前提是：与当前写入的位置前面的第 k 个元素进行比较，不相同则保留。
     */
    public int removeDuplicates_1(int[] nums) {
        int index = 0;
        int k = 1;
        for (int i = 0; i < nums.length; i++) {
            if (index < k || nums[index - k] != nums[i]) {
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }
}
