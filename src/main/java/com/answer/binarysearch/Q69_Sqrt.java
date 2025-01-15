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
     * 同上
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
     * 同上
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
    /**
     * 方法三：牛顿迭代 (牛顿迭代法的核心思想是通过不断逼近的方式，快速找到方程的根)
     */
    public int mySqrt4(int x) {
        if (x == 0) {
            return 0;
        }

        double C = x, x0 = x;
        while (true) {
            double xi = 0.5 * (x0 + C / x0);
            if (Math.abs(x0 - xi) < 1e-7) {
                break;
            }
            x0 = xi;
        }
        return (int) x0;
    }
    /**
     * 牛顿迭代 递归实现
     * 特殊情况处理：如果输入的 x 为 0，直接返回 0。
     * 初始猜测值：调用辅助函数 f，初始猜测值设为 1。
     * 牛顿迭代：
     *     计算新的猜测值 res。
     *     如果新的猜测值 res 与旧的猜测值 y 相等，说明已经收敛，返回 res。
     *     否则，继续递归调用 f 函数，传入新的猜测值 res。
     *
     * 时间复杂度：牛顿迭代法的收敛速度非常快，通常在几次迭代后就能得到精确的结果。因此，时间复杂度接近于 O(loglogx)。
     * 空间复杂度：由于使用了递归，递归调用栈的深度取决于迭代次数，通常情况下深度较小，因此空间复杂度为 O(loglogx)。
     */
    public int mySqrt5(int x) {
        if(x == 0) return 0; // 特殊情况处理：如果 x 为 0，直接返回 0
        return (int)f(x, 1); // 调用辅助函数 f，初始猜测值为 1
    }

    double f(int x, double y) {
        double res = (y + x / y) / 2; // 使用牛顿迭代法计算新的猜测值
        if (res == y) { // 如果新的猜测值与旧的猜测值相同，说明已经收敛
            return res; // 返回结果
        } else {
            return f(x, res); // 否则，继续迭代
        }
    }
    /**
     *  牛顿迭代 另一种形式
     */
    public int mySqrt6(int x) {
        int r = x;
        while(r * r > x){
            r = (r + x / r) / 2;
        }
        return r;
    }
}
