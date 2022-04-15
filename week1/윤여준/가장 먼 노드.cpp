#include <bits/stdc++.h>
using namespace std;

int answer;
int dist[20001];
vector<int> adj[20001];

void bfs(int n) {
    dist[n] = 0;
    queue<int> q; q.push(n);
    
    while(!q.empty()) {
        int cur = q.front(); q.pop();
        for(int i : adj[cur]) 
            if(dist[i] == -1) {
                dist[i] = dist[cur] + 1;
                q.push(i);
            }
    }    
}

int solution(int n, vector<vector<int>> edge) {
    for(auto v : edge) {
        adj[v[0]].push_back(v[1]);
        adj[v[1]].push_back(v[0]);
    }
    
    memset(dist, -1, sizeof(dist));
    bfs(1);
    
    int temp = 0;
    for(int i = 1; i <= 20000; i++) {
        if(dist[i] > temp) { temp = dist[i]; answer = 1; }
        else if(dist[i] == temp) answer++;
    }
        
    return answer;
}