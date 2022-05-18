// 맞왜틀

#include <bits/stdc++.h>
using namespace std;

int board[101][101];

int solve(int y1, int x1, int y2, int x2) {
    int m = 1e9;
    
    int temp = board[y1][x1];
    
    for(int i = y1; i < y2; i++) {
        board[i][x1] = board[i+1][x2];
        m = min(m, board[i][x1]);
    }
    
    for(int i = x1; i < x2; i++) {
        board[y2][i] = board[y2][i+1];
        m = min(m, board[y2][i]);
    }
    
    for(int i = y2; i > y1; i--) {
        board[i][x2] = board[i-1][x2];
        m = min(m, board[i][x2]);
    }
    
    for(int i = x2; i > x1; i--) {
        board[y1][i] = board[y1][i-1];
        m = min(m, board[y1][i]);
    }
    
    board[y1][x1+1] = temp;
    m = min(m, board[y1][x1+1]);
        
    return m;
}

vector<int> solution(int rows, int columns, vector<vector<int>> queries) {
    vector<int> answer;
    
    for(int i = 0; i < rows; i++)
        for(int j = 0; j < columns; j++)
            board[i][j] = i * rows + j + 1;
    
    for(auto v : queries)
        answer.push_back(solve(v[0]-1, v[1]-1, v[2]-1, v[3]-1));
    
    
    return answer;
}