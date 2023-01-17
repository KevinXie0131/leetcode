package com.answer.binarysearch;

public class Q875_Koko_Eating_Bananas {

    public int minEatingSpeed(int[] piles, int h) {
        int left = 1;
        int right = 1;
        for(int pile : piles){
            right = Math.max(right, pile);
        }

        while(left < right) {
            int mid = (left + right) >>> 1;
            int hour = 0;

            for(int pile : piles){
                hour += Math.ceil((double)pile / mid);
            }

            if(hour <= h){
                right = mid;
            }else{
                left = mid + 1;
            }
        }

        return left;
    }
}
