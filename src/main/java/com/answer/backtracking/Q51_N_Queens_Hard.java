package com.answer.backtracking;

import java.util.*;

public class Q51_N_Queens_Hard {

    List<List<String>> res;
    public List<List<String>> solveNQueens(int n)
    {
        res = new ArrayList<>();
        char[][] board = new char[n][n];
        for(char[] chars:board)
        {
            Arrays.fill(chars,'.');
        }
        //创建棋盘board，dfs从 第一行开始遍历
        dfs(board,0,n);
        return res;
    }

    //dfs遍历每一行，其中的for循环遍历每一列
    public void dfs(char[][] board,int r,int n)
    {
        if(r==n)
        {
            res.add(toList(board));
            return;
        }
        for(int i=0;i<n;i++)
        {
            if(isValid(board,r,i,n))
            {
                board[r][i]='Q';
                dfs(board,r+1,n);
                board[r][i]='.';
            }
        }
    }
    //检查某一点 (r,c) 能否放皇后
    public boolean isValid(char[][] board,int r,int c,int n)
    {
        //检查该列
        for(int i=0;i<n;i++)
        {
            if(board[i][c]=='Q')
            {
                return false;
            }
        }
        //检查左上45
        for(int i=r-1,j=c-1;i>=0 && j>=0;i--,j--)
        {
            if(board[i][j]=='Q')
            {
                return false;
            }
        }
        //检查右上45
        for(int i=r-1,j=c+1;i>=0 && j<n;i--,j++)
        {
            if(board[i][j]=='Q')
            {
                return false;
            }
        }
        return true;
    }

    //将 char[][] board 转换为 List<String>
    public List<String> toList(char[][] board)
    {
        List<String> list = new ArrayList<>();
        for(char[] chars:board)
        {
            list.add(String.valueOf(chars));
        }
        return list;
    }
}
