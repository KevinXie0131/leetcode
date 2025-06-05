package com.answer.two_pointers;

public class Q27_Remove_Element {
    /**
     * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place. The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.
     * Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do the following things:
     *  Change the array nums such that the first k elements of nums contain the elements which are not equal to val. The remaining elements of nums are not important as well as the size of nums.
     *  Return k.
     * 移除元素
     * 一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素。元素的顺序可能发生改变。然后返回 nums 中与 val 不同的元素的数量。
     * 假设 nums 中不等于 val 的元素数量为 k，要通过此题，您需要执行以下操作：
     *  更改 nums 数组，使 nums 的前 k 个元素包含不等于 val 的元素。nums 的其余元素和 nums 的大小并不重要。
     *  返回 k。
     */
    public static void main(String[] args) {
      /**
       * 示例 2：
       *  输入：nums = [0,1,2,2,3,0,4,2], val = 2
       *  输出：5, nums = [0,1,4,0,3,_,_,_]
       *  解释：你的函数应该返回 k = 5，并且 nums 中的前五个元素为 0,0,1,3,4。
       *  注意这五个元素可以任意顺序返回。你在返回的 k 个元素之外留下了什么并不重要（因此它们并不计入评测）。
       */
       int[]  nums = {2,1,2,2,3,0,4,2};
       int val = 2;
       int result =  removeElement6(nums, val);
       System.out.println(result);
    }
    /**
     * 辅助下标
     * 本题与 Q26. 删除有序数组中的重复项 解法相似，只是循环中的赋值条件发生了改变。
     * 本题不需要和去重元素相比，只要和给定的 val 相比即可。
     */
    public int removeElement0(int[] nums, int val) {
        int index = 0;
        for(int i = 0; i < nums.length; i++){
            if(nums[i] != val){
                nums[index] = nums[i];
                index++;
            }
        }
        return index;
    }
    /**
     * 双指针
     * 时间复杂度：O(n)，其中 n 为序列的长度。我们只需要遍历该序列至多两次。
     *
     * 如果当前元素 x 与移除元素 val 相同，那么跳过该元素。
     * 如果当前元素 x 与移除元素 val 不同，那么我们将其放到下标 slow 的位置，并让 slow 自增右移。
     * 最终得到的 slow 即是答案。
     */
    public int removeElement(int[] nums, int val) {
        int slow = 0;

        for(int fast = 0; fast < nums.length; fast++){
            if(nums[fast] != val){
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }
    /**
     * 把 nums 视作一个栈，把不等于 val 的元素入栈，最后返回栈的大小。
     */
    public int removeElement8(int[] nums, int val) {
        int stackSize = 0;
        for (int x : nums) {
            if (x != val) {
                nums[stackSize++] = x; // 把 x 入栈
            }
        }
        return stackSize;
    }
    /**
     * 双指针解法
     * 根据题意，我们可以将数组分成「前后」两段：
     *  前半段是有效部分，存储的是不等于 val 的元素。
     *  后半段是无效部分，存储的是等于 val 的元素。
     * 最终答案返回有效部分的结尾下标。
     */
    static public int removeElement6(int[] nums, int val) {
        int j = nums.length - 1;
        for (int i = 0; i <= j; i++) {
            if (nums[i] == val) {
                swap(nums, i, j);
                i--;
                j--;
            }
        }
        return j + 1;
    }
    /**
     * another form
     */
    public int removeElement6a(int[] nums, int val) {
        int i = 0, j = nums.length - 1;
        while(i <= j) {
            if (nums[i] == val) {
                swap(nums, i, j);
                i--;
                j--;
            }
            i++;
        }
        return j + 1;
    }

    static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 相向双指针法
     */
    public static int removeElement_0(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;

        while(right >= 0 && nums[right] == val) {
            right--; //将right移到从右数第一个值不为val的位置
        }
        while(left <= right) {
            if(nums[left] == val) { //left位置的元素需要移除
                nums[left] = nums[right];//将right位置的元素移到left（覆盖），right位置移除
                right--;
            }
            left++;
            while(right >= 0 && nums[right] == val) {
                right--;
            }
        }
        return left;
    }
    /**
     * 相向双指针法（版本二）
     * 注意到题目中说：「元素的顺序可以改变」
     * 使用双指针，两个指针初始时分别位于数组的首尾，向中间移动遍历该序列。
     * 这样的方法两个指针在最坏的情况下合起来只遍历了数组一次。与fast/slow pointer方法不同的是，避免了需要保留的元素的重复赋值操作。
     * 时间复杂度：O(n)，其中 n 为序列的长度。我们只需要遍历该序列至多一次。
     */
    public static int removeElement_1(int[] nums, int val) {
        int left = 0;
        int right = nums.length - 1;

        while(left <= right){
            if(nums[left] == val){
                nums[left] = nums[right];
                right--;
            } else {
                left++; // 这里兼容了right指针指向的值与val相等的情况
            }
        }
        return left;
    }
    /**
     * 另一种形式
     */
    public int removeElement3(int[] nums, int val) {
        if(nums.length == 0) return 0;

        int left = 0;
        int right = nums.length - 1;
        while(left < right) {
            while(left < right && nums[left] != val) {
                left++;
            }
            while(right > left && nums[right] == val) {
                right--;
            }
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
        }
        return nums[left] == val ? left : left + 1; // nums = {2} val = 3
    }
}
