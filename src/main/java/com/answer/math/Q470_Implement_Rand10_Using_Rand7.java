package com.answer.math;

public class Q470_Implement_Rand10_Using_Rand7 {
    /**
     * Approach 2: Utilizing out-of-range samples
     * 已知 rand_N() 可以等概率的生成[1, N]范围的随机数
     * 那么：(rand_X() - 1) × Y + rand_Y() ==> 可以等概率的生成[1, X * Y]范围的随机数, 即实现了 rand_XY()
     */
    public int rand10() {
        while (true) {
            int num = (rand7() - 1) * 7 + rand7(); // rand 49
            // 如果在40以内，那就直接返回
            if (num <= 40) {
                return 1 + num % 10; // 拒绝采样
            }
            // 说明刚才生成的在41-49之间，利用随机数再操作一遍
            // num - 40 -> rand 9
            num = (num - 40 - 1) * 7 + rand7(); // rand 63
            if (num <= 60) {
                return 1 + num % 10;
            }
            // 说明刚才生成的在61-63之间，利用随机数再操作一遍
            // num - 60 -> rand 3
            num = (num - 60 - 1) * 7 + rand7(); // rand 21
            if (num <= 20) {
                return 1 + num % 10;
            }
        }
    }

    /**
     * Approach 1: Rejection Sampling
     * Accepted
     */
    public int rand10_1() {
        while (true){
            int row = rand7();
            int col = rand7();
            int index = (row - 1) * 7 + col;
            // If the number is out of the desired range, reject it and re-sample again. As each number in the desired range
            // has the same probability of being chosen, a uniform distribution is produced.
            if(index <= 40){
                return (index - 1) % 10 + 1;
            }
        }
    }

    public int rand7(){
        return 1;
    }
}
