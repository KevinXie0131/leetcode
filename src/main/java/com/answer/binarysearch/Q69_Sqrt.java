package com.answer.binarysearch;

public class Q69_Sqrt {
    public static void main(String[] args) {
        int re = mySqrt0(2147395599);
        System.out.println(re);
    }
    /**
     * 二分查找
     * 由于 x 平方根的整数部分 ans 是满足 k*k ≤x 的最大 k 值，因此我们可以对 k 进行二分查找，从而得到答案。
     *
     * 二分查找的下界为 0，上界可以粗略地设定为 x。在二分查找的每一步中，我们只需要比较中间元素 mid 的平方与 x 的大小关系，
     * 并通过比较的结果调整上下界的范围。由于我们所有的运算都是整数运算，不会存在误差，因此在得到最终的答案 ans 后，
     * 也就不需要再去尝试 ans+1 了。
     *
     */
    public static int mySqrt0(int x) {
        int left = 0;
        int right = x;
        int result = -1 ;

        while (left <= right) {
            long mid = left + ((right - left) >> 1) ; // use long instead for too big input like 2147395599
            long value = mid * mid;
            if (value > x) {
                right = (int)mid - 1;
            } else if (value <= x) {
                result = (int)mid;
                left = (int)mid + 1;

            }
        }
        return result;
    }
    /**
     *
     */
    public int mySqrt(int x) {
        int left = 0, right = x;
        while(left <= right){
            int mid = (left + right) >>> 1;
            if((long)mid * mid <= x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left - 1;
    }
    /**
     *
     */
    public int mySqrt_1(int x) {
        if (x < 2) return x;

        long num;
        int pivot, left = 2, right = x / 2;
        while (left <= right) {
            pivot = left + (right - left) / 2;
            num = (long)pivot * pivot;
            if (num > x) right = pivot - 1;
            else if (num < x) left = pivot + 1;
            else return pivot;
        }

        return right;
    }
}
