#include <bits/stdc++.h>
using namespace std;

typedef pair<int, int> pii;

bool flag;
char board[5][5];
bool visited[5][5];

int dy[] = {-1, 0, 1, 0};
int dx[] = {0, 1, 0, -1};

void dfs(int y, int x, int depth) {
    if(board[y][x] == 'P' && 0 < depth && depth < 3) {
        flag = true;
        return;
    }

    visited[y][x] = true;

    for(int next = 0; next < 4; next++) {
        int ny = y + dy[next];
        int nx = x + dx[next];
        if(ny>=0 && ny<5 && nx>=0 && nx<5 && board[ny][nx]!='X' && !visited[ny][nx])
            dfs(ny,nx,depth+1);
    }
}

vector<int> solution(vector<vector<string>> places) {
    vector<int> answer;
    
    for(auto v : places) {
        flag = false;
        vector<pii> p;
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++) {
                board[i][j] = v[i][j];
                if(board[i][j] == 'P')
                    p.push_back({i, j});
            }
                    
        for(pii a : p) {
            memset(visited, false, sizeof(visited));
            dfs(a.first, a.second, 0);
            if(flag) break;
        }
        
        answer.push_back(!flag);
    }
    
    return answer;
}