package com.answer.sliding_window;

public class Q1052_Grumpy_Bookstore_Owner {
    public static void main(String[] args) {
        int[] customers = {1,0,1,2,1,1,7,5};
        int[] grumpy = {0,1,0,1,0,1,0,1};
        int minutes = 3;
        System.out.println(maxSatisfied(customers, grumpy, minutes));
    }
    /**
     * Sliding window
     */
    public static int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int total = 0;
        int len = customers.length;
        int increase = 0, max = 0;

        for(int i = 0; i < len; i++){
            if(grumpy[i] == 0){
                total += customers[i];
            }
        }

        for(int i = 0; i < len; i++){
            if(i < minutes){
                increase += customers[i] * grumpy[i];
            }else{
                increase += customers[i] * grumpy[i];
                increase -= customers[i - minutes] * grumpy[i - minutes];
            }
            max = Math.max(max, increase);
        }
        return total + max;
    }

}
