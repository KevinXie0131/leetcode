package com.answer.greedy;

import java.util.Arrays;

public class Q135_Candy {
    /**
     * Greedy
     * Approach 3: Using one array
     */
    public int candy(int[] ratings) {
        int[] candy  = new int[ratings.length];
        Arrays.fill(candy, 1);

        for(int i = 1; i < ratings.length; i++){
            if(ratings[i] > ratings[i - 1]){
                candy[i] = candy[i - 1] + 1;
            }
        }
        for(int i = ratings.length - 2; i >= 0; i--) {
            if(ratings[i] > ratings[i + 1]) {
                candy[i] = Math.max(candy[i], candy[i + 1] + 1);
            }
        }
        int sum = 0;
        for(int n : candy){
            sum += n;
        }
        return sum;
    }
}
