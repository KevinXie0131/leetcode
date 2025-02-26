package com.answer.dynamic_programming;

import java.util.Arrays;

public class Q152_Maximum_Product_Subarray {
    /**
     * 动态规划
     * 遍历数组时计算当前最大值，不断更新
     * 令imax为当前最大值，则当前最大值为 imax = max(imax * nums[i], nums[i])
     * 由于存在负数，那么会导致最大的变最小的，最小的变最大的。因此还需要维护当前最小值imin，imin = min(imin * nums[i], nums[i])
     * 当负数出现时则imax与imin进行交换再进行下一步计算
     * 时间复杂度：O(n)
     */
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE;
        int imax = 1;
        int imin = 1;

        for(int i = 0; i < nums.length; i++){
            if(nums[i] < 0){
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax * nums[i], nums[i]);
            imin = Math.min(imin * nums[i], nums[i]);

            max = Math.max(max, imax);
        }
        return max;
    }
    /**
     * 写法一
     */
    public int maxProduct_1(int[] nums) {
        int n = nums.length;
        int[] fMax = new int[n]; // 右端点下标为 i 的子数组的最大乘积
        int[] fMin = new int[n]; // 右端点下标为 i 的子数组的最小乘积
        fMax[0] = fMin[0] = nums[0];

        for (int i = 1; i < n; i++) {
            int x = nums[i];
            // 把 x 加到右端点为 i-1 的（乘积最大/最小）子数组后面，
            // 或者单独组成一个子数组，只有 x 一个元素
            fMax[i] = Math.max(Math.max(fMax[i - 1] * x, fMin[i - 1] * x), x); // 把这两种都算一下，这样我们就无需判断 x 到底是正还是负了。
            fMin[i] = Math.min(Math.min(fMax[i - 1] * x, fMin[i - 1] * x), x);
        }

        return Arrays.stream(fMax).max().getAsInt();
    }
    /**
     * 写法二（空间优化）
     * 可以用两个变量滚动计算
     */
    public int maxProduct_2(int[] nums) {
        int ans = Integer.MIN_VALUE; // 注意答案可能是负数
        int fMax = 1;
        int fMin = 1;
        for (int x : nums) {
            int mx = fMax;
            fMax = Math.max(Math.max(fMax * x, fMin * x), x);
            fMin = Math.min(Math.min(mx * x, fMin * x), x);
            ans = Math.max(ans, fMax);
        }
        return ans;
    }
}
