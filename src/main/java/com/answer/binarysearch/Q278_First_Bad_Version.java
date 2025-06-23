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
    public int firstBadVersion(int n) {
        int left = 1, right = n;

        while(left <= right){
            int mid = (left + right) >>> 1;
            boolean isBad = isBadVersion(mid);
            if(isBad){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
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
