package com.answer.string;

public class Q657_Robot_Return_to_Origin {
    /**
     * 在二维平面上，有一个机器人从原点 (0, 0) 开始。给出它的移动顺序，判断这个机器人在完成移动后是否在 (0, 0) 处结束。
     * 移动顺序由字符串 moves 表示。字符 move[i] 表示其第 i 次移动。机器人的有效动作有 R（右），L（左），U（上）和 D（下）。
     * 如果机器人在完成所有动作后返回原点，则返回 true。否则，返回 false。
     * 注意：机器人“面朝”的方向无关紧要。 “R” 将始终使机器人向右移动一次，“L” 将始终向左移动等。此外，假设每次移动机器人的移动幅度相同。
     */
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
