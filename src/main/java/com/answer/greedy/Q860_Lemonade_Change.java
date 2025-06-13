package com.answer.greedy;

public class Q860_Lemonade_Change {
    /**
     * 柠檬水找零
     * 在柠檬水摊上，每一杯柠檬水的售价为 5 美元。顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
     * 每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
     * 注意，一开始你手头没有任何零钱。
     * 给你一个整数数组 bills ，其中 bills[i] 是第 i 位顾客付的账。如果你能给每位顾客正确找零，返回 true ，否则返回 false 。
     * 输入：bills = [5,5,5,10,20]
     * 输出：true
     * 解释：
     *  前 3 位顾客那里，我们按顺序收取 3 张 5 美元的钞票。
     *  第 4 位顾客那里，我们收取一张 10 美元的钞票，并返还 5 美元。
     *  第 5 位顾客那里，我们找还一张 10 美元的钞票和一张 5 美元的钞票。
     *  由于所有客户都得到了正确的找零，所以我们输出 true。
     */
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
