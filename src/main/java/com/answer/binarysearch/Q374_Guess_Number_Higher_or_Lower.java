package com.answer.binarysearch;

public class Q374_Guess_Number_Higher_or_Lower {

    /**
     * Approach 2: Using Binary Search
     */
    public int guessNumber(int n) {
        int left = 1;
        int right = n;
        while(left <= right){
            int mid = (left + right) >>> 1;
            int value = guess(mid);

            if(value == 0){
                return mid;
            }else if(value == -1){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        return -1;
    }

    int guess(int num){
        return 0;
    }
    /**
     * Approach 1: Brute Force
     */
    public int guessNumber_1(int n) {
        for (int i = 1; i < n; i++)
            if (guess(i) == 0)
                return i;
        return n;
    }
    /**
     * Approach 3: Ternary Search
     * Comparisons between Binary Search and Ternary Search:
     * Ternary Search is worse than Binary Search, since Ternary Search does more comparisons than Binary Search in the worst case.
     */
    public int guessNumber_2(int n) {
        int low = 1;
        int high = n;
        while (low <= high) {
            int mid1 = low + (high - low) / 3;
            int mid2 = high - (high - low) / 3;
            int res1 = guess(mid1);
            int res2 = guess(mid2);
            if (res1 == 0)
                return mid1;
            if (res2 == 0)
                return mid2;
            else if (res1 < 0)
                high = mid1 - 1;
            else if (res2 > 0)
                low = mid2 + 1;
            else {
                low = mid1 + 1;
                high = mid2 - 1;
            }
        }
        return -1;
    }
}
