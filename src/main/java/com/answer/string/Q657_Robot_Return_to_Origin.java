package com.answer.string;

public class Q657_Robot_Return_to_Origin {
    /**
     * x，y坐标，初始为0，然后：
     * if (moves[i] == 'U') y++;
     * if (moves[i] == 'D') y--;
     * if (moves[i] == 'L') x--;
     * if (moves[i] == 'R') x++;
     * 最后判断一下x，y是否回到了(0, 0)位置就可以了。
     *
     * 时间复杂度：O(n)
     * 空间复杂度：如果采用 toCharArray，则是 O（n）;如果使用 charAt，则是 O(1)
     */
    public boolean judgeCircle(String moves) {
        int x = 0;
        int y = 0;

        for(int i = 0; i < moves.length(); i++){
            char ch = moves.charAt(i);
            if(ch == 'L') x--;
            else if(ch == 'R') x++;
            else if(ch == 'U') y--;
            else if(ch == 'D') y++;
        }
        return x == 0 && y == 0;

    }
}
