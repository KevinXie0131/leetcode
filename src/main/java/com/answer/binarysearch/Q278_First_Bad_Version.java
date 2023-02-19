package com.answer.binarysearch;

public class Q278_First_Bad_Version {

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
