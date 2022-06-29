#include <bits/stdc++.h>
using namespace std;

int dy[] = {-1,0,1,0};
int dx[] = {0,1,0,-1};
bool visited[500][500][4];
vector<string> grid;
vector<int> answer;

void solve(int y, int x, int dir, int len) {
    if(visited[y][x][dir]) {
        answer.push_back(len);
        return;
    }
    
    visited[y][x][dir] = true;
    
    if(grid[y][x] == 'L') {
        dir--;
        if(dir < 0) dir = 3;
    } else if(grid[y][x] == 'R') {
        dir++;
        if(dir == 4) dir = 0;
    }
    
    y += dy[dir];
    if(y < 0) y = grid.size() - 1;
    else if(y == grid.size()) y = 0;
    
    x += dx[dir];
    if(x < 0) x = grid[y].size() - 1;
    else if(x == grid[y].size()) x = 0;
    
    solve(y, x, dir, len + 1);
}

vector<int> solution(vector<string> v) {
    grid = v;
    
    for(int i = 0; i < grid.size(); i++)
        for(int j = 0; j < grid[i].size(); j++)
            for(int k = 0; k < 4; k++)
                if(!visited[i][j][k])
                    solve(i, j, k, 0);
    
    sort(answer.begin(), answer.end());
    return answer;
}