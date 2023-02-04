package com.answer.greedy;

public class Q860_Lemonade_Change {
    /**
     * Greedy algorithm
     */
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for(int bill : bills){
            if(bill == 5){
                five++;
            }else if(bill == 10){
                if(five > 0){
                    five--;
                }else{
                    return false;
                }
                ten++;
            }else if(bill == 20){
                if(ten > 0){
                    ten--;
                    if(five > 0){
                        five--;
                    }else{
                        return false;
                    }
                }else{
                    if(five >= 3){
                        five -= 3;
                    }else{
                        return false;
                    }
                }
            }
        }
        return true;
    }
    /**
     * Approach 1: Simulation
     */
    public boolean lemonadeChange_1(int[] bills) {
        int five = 0, ten = 0;
        for (int bill: bills) {
            if (bill == 5)
                five++;
            else if (bill == 10) {
                if (five == 0) return false;
                five--;
                ten++;
            } else {
                if (five > 0 && ten > 0) {
                    five--;
                    ten--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }
}
