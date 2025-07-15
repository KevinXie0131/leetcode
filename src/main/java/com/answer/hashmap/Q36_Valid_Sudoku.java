package com.answer.hashmap;

import java.util.*;

public class Q36_Valid_Sudoku {
    /**
     * 有效的数独
     * 请你判断一个 9 x 9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
     *  数字 1-9 在每一行只能出现一次。
     *  数字 1-9 在每一列只能出现一次。
     *  数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
     *
     * 注意：
     *  一个有效的数独（部分已被填充）不一定是可解的。
     *  只需要根据以上规则，验证已经填入的数字是否有效即可。
     *  空白格用 '.' 表示。
     *
     * 输入：board =
     * [["5","3",".",".","7",".",".",".","."]
     * ,["6",".",".","1","9","5",".",".","."]
     * ,[".","9","8",".",".",".",".","6","."]
     * ,["8",".",".",".","6",".",".",".","3"]
     * ,["4",".",".","8",".","3",".",".","1"]
     * ,["7",".",".",".","2",".",".",".","6"]
     * ,[".","6",".",".",".",".","2","8","."]
     * ,[".",".",".","4","1","9",".",".","5"]
     * ,[".",".",".",".","8",".",".","7","9"]]
     * 输出：true
     */
    /**
     * 由于数独中的数字范围是 1 到 9，因此可以使用数组代替哈希表进行计数。
     */
    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][9]; //定义数字行内出现的次数
        int[][] columns = new int[9][9];  //定义数字列内出现的次数
        int[][][] subboxes = new int[3][3][9]; //定义数字九宫格内出现的次数最大为9次
        //遍历数组
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char c = board[i][j];
                if (c != '.') {   //只要存在数字
                    int index = c - '0' - 1; //把数字化成索引下标
                    rows[i][index]++;
                    columns[j][index]++;
                    subboxes[i / 3][j / 3][index]++; // 九宫格内次数也要+1,例如也是第1行第一列，i/3 j/3会自动定位到所在的小宫格
                    if (rows[i][index] > 1 || columns[j][index] > 1 || subboxes[i / 3][j / 3][index] > 1) {
                        return false; // 次数大于1就不成立一个数独
                    }
                }
            }
        }
        return true;
    }
    /**
     * another form 三维数组
     */
    public boolean isValidSudoku1(char[][] board) {
        int[][] row = new int[9][10];
        int[][] col = new int[9][10];
        int[][][] box = new int[3][3][10];
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[0].length; j++){
                if(board[i][j] == '.') {
                    continue;
                }
                int num = board[i][j] - '0';
                if(row[i][num] == 1 || col[j][num] == 1 || box[i / 3][j / 3][num] == 1){
                    return false;
                }
                row[i][num] = 1;
                col[j][num] = 1;
                box[i / 3][j / 3][num] = 1;
            }
        }
        return true;
    }
    /**
     * HashMap
     */
    public boolean isValidSudoku2(char[][] board) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            char[] chars = board[i];
            for (int j = 0; j < chars.length; j++) {
                char c = chars[j];
                if (c != '.') {
                    String rowKey = "row" + i + c;
                    String colKey = "col" + j + c;
                    String boxKey = "box" + i / 3 + j / 3 + c;
                    if (map.containsKey(rowKey) || map.containsKey(colKey) || map.containsKey(boxKey)) {
                        return false;
                    }
                    map.put(rowKey, 1);
                    map.put(colKey, 1);
                    map.put(boxKey, 1);
                }
            }
        }
        return true;
    }

}
