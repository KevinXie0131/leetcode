package com.answer.binarysearch;

public class Q278_First_Bad_Version {
    /**
     * 第一个错误的版本
     * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
     * Since each version is developed based on the previous version, all the versions after a bad version are also bad.
     * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
     * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
     * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
     * You are given an API bool isBadVersion(version) which returns whether version is bad. Implement a function to find the first bad version.
     * You should minimize the number of calls to the API.
     *
     * 示例 1：
     *  输入：n = 5, bad = 4
     *  输出：4
     *  解释：调用 isBadVersion(3) -> false
     *       调用 isBadVersion(5) -> true
     *       调用 isBadVersion(4) -> true
     *       所以，4 是第一个错误的版本。
     */
    /**
     * 根据题目描述 “错误的版本之后的所有版本都是错的” ，说明给定的版本正确性列表是「有序的」，即以某个版本为分界点，左边（右边）都是正确（错误）版本。
     * 因此，考虑使用「二分查找」来查找首个错误版本。
     */
    public int firstBadVersion(int n) {
        int left = 1, right = n;

        while(left <= right){
            int mid = (left + right) >>> 1;
            boolean isBadVersion = isBadVersion(mid);
            if(isBadVersion){
                right = mid - 1;  // 若 mid 是错误版本，则最后一个正确版本一定在闭区间 [left, mid - 1]
            } else {
                left = mid + 1;   // 若 mid 是正确版本，则首个错误版本一定在闭区间 [mid + 1, right]
            }
        }
        /**
         * 出现序列false false false false true true true true true true true
         * 退出循环的时候，是left == right + 1
         * left 向右偏移一个单位，极端情况下，会让false发生质的变化，变为true
         * right 向左偏移一个单位，极端情况下，会让true发生质的变化，变为false
         * 所以说，我们这里只能return left;
         */
        // left 指向首个错误版本，right 指向最后一个正确版本
        return left;
        // return right + 1; // works too
    }
    /**
     * 关于return方面，不想纠结return哪一个，我们就引入第三个变量
     */
    public int firstBadVersion_0(int n) {
        int left = 1, right = n;
        int res = 1; // 这第三个变量始终接受我们需要的值
        while(left <= right){
            int mid = (left + right) >>> 1;
            boolean isBadVersion = isBadVersion(mid);
            if(isBadVersion){
                res = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }
    /**
     * Another form
     */
    public int firstBadVersion_1(int n) {
        int left = 1;
        int right = n;

        while(left < right) {
            int mid = (right-left)/2 + left;
            if(isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
    /**
     * Template II
     */
    public int firstBadVersion_2(int n) {
        int start = 0;
        int end = n;
        int mid = 0;
        while(start < end){
            mid = start + (end - start) / 2;
            if(isBadVersion(mid)){
                //这里是坏的版本
                end = mid;
            }else{
                //这里是好的版本
                start = mid + 1;
            }
        }
        if(start == end && isBadVersion(start)){
            return start;
        }
        return -1;
    }

    boolean isBadVersion(int version){return true;};
}
