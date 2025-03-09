package com.answer.greedy;

public class Q860_Lemonade_Change {
    /**
     * Greedy algorithm
     * 三种情况
     *  情况一：账单是5，直接收下。
     *  情况二：账单是10，消耗一个5，增加一个10
     *  情况三：账单是20，优先消耗一个10和一个5，如果不够，再消耗三个5 / 有贪心的
     *
     * 因为美元10只能给账单20找零，而美元5可以给账单10和账单20找零，美元5更万能！
     * 所以局部最优：遇到账单20，优先消耗美元10，完成本次找零。全局最优：完成全部账单的找零。
     */
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for(int bill : bills){
            if(bill == 5){ // 情况一
                five++;
            }else if(bill == 10){   // 情况二
                if(five > 0){
                    five--;
                }else{
                    return false;
                }
                ten++;
            }else if(bill == 20){  // 情况三
                if(ten > 0){ // 优先消耗10美元，因为5美元的找零用处更大，能多留着就多留着
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
