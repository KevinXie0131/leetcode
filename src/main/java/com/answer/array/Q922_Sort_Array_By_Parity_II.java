package com.answer.array;

import java.util.Arrays;

public class Q922_Sort_Array_By_Parity_II {
    /**
     * 按奇偶排序数组 II: 给定一个非负整数数组 nums，  nums 中一半整数是 奇数 ，一半整数是 偶数 。
     * 对数组进行排序，以便当 nums[i] 为奇数时，i 也是 奇数 ；当 nums[i] 为偶数时， i 也是 偶数 。
     * 你可以返回 任何满足上述条件的数组作为答案 。
     * 示例 1：
     *  输入：nums = [4,2,5,7]
     *  输出：[4,5,2,7]
     *  解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
     */
    /**
     * nums[i] is odd, i is odd, and whenever nums[i] is even, i is even.
     * Follow Up: Could you solve it in-place?
     * 当 nums[i] 为奇数时，i 也是 奇数 ；当 nums[i] 为偶数时， i 也是 偶数
     * 进阶：可以不使用额外空间解决问题吗？
     *
     * 注：题目保证数组中恰有一半是偶数，恰有一半是奇数。
     */
    public static void main(String[] args) {
       int[] nums = {4,2,5,7};
        int[]  result =  sortArrayByParityII_1(nums);
        System.out.println(Arrays.toString(result));
    }
    /**
     * 采用额外的数组空间 (优化一下就是不用这两个辅助数组)
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int[] sortArrayByParityII(int[] nums) {
        int[] result = new int [nums.length];  //定义结果数组 result
        int oddIndex = 1;   // 奇数下标
        int evenIndex = 0; // 偶数下标

        for(int i = 0; i < nums.length; i++){
            if(nums[i] % 2 == 0){   //如果为偶数
                result[evenIndex] = nums[i];
                evenIndex += 2;
            } else {
                result[oddIndex] = nums[i];
                oddIndex += 2;
            }
        }
        return result;
    }
    /**
     * 不采用额外的数组空间
     * 可以在原数组上修改，连result数组都不用了。
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    static public int[] sortArrayByParityII_1(int[] nums) {

        int oddIndex = 1;

        for(int i = 0; i < nums.length; i += 2){
            if(nums[i] % 2 == 1){  // 在偶数位遇到了奇数
                while(nums[oddIndex] % 2 == 1) {  // 在奇数位找一个偶数
                    oddIndex += 2;
                }
                int temp = nums[i]; // 替换
                nums[i] = nums[oddIndex];
                nums[oddIndex] = temp;
            }
        }

        return nums;
    }
    /**
     * 朴实的方法 两次遍历
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public int[] sortArrayByParityII_2(int[] nums) {
        // 分别存放 nums 中的奇数、偶数
        int len = nums.length;
        int evenIndex = 0;
        int oddIndex = 0;
        int[] even = new int[len / 2];  // 初始化就确定数组大小，节省开销
        int[] odd = new int[len / 2];
        for (int i = 0; i < len; i++) { // 把nums数组放进偶数数组，和奇数数组
            if (nums[i] % 2 == 0) {
                even[evenIndex++] = nums[i];
            } else {
                odd[oddIndex++] = nums[i];
            }
        }
        // 把奇偶数组重新存回 nums
        int index = 0;
        for (int i = 0; i < even.length; i++) {
            nums[index++] = even[i];
            nums[index++] = odd[i];
        }
        return nums;
    }
    /**
     * 双指针
     */
    public int[] sortArrayByParityII_3(int[] nums) {
        //定义双指针
        int oddPoint = 1, evenPoint = 0;
        //开始移动并交换，最后一层必然为相互交换后再移动或者相同直接移动
        while(oddPoint < nums.length && evenPoint < nums.length){
            //进行判断
            if(nums[oddPoint] % 2 == 0 && nums[evenPoint] % 2 == 1){    //如果均不满足
                int temp = nums[oddPoint];
                nums[oddPoint] = nums[evenPoint];
                nums[evenPoint] = temp;

                oddPoint += 2;
                evenPoint += 2;
            }else if(nums[oddPoint] % 2 == 0 && nums[evenPoint] % 2 == 0){  //偶数满足
                evenPoint += 2;
            }else if(nums[oddPoint] % 2 == 1 && nums[evenPoint] % 2 == 1){  //奇数满足
                oddPoint += 2;
            }else{
                oddPoint += 2;
                evenPoint += 2;
            }
        }
        return nums;
    }
    /**
     * 使用两个指针分别指向奇数下标和偶数下标。
     * 找到最左边的奇数 nums[i] 和最左边的偶数 nums[j]，交换这两个数。
     */
    public int[] sortArrayByParityII_4(int[] nums) {
        int i = 0;
        int j = 1;
        // 为什么“当偶数下标指针不越界”这一个条件就够了？因为偶数下标越界时，
        // 说明偶数下标的元素全部符合，也说明奇数下标的元素全部合规了。
        while (i < nums.length) {
            if (nums[i] % 2 == 0) { // 寻找偶数下标中最左边的奇数
                i += 2;
            } else if (nums[j] % 2 == 1) { // 寻找奇数下标中最左边的偶数
                j += 2;
            } else {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                i += 2;
                j += 2;
            }
        }
        return nums;
    }
}
