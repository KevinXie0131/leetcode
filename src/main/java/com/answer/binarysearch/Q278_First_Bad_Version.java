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

    boolean isBadVersion(int version){return true;};
}
