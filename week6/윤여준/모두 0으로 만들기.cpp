// 또 long long 삽질함

#include <bits/stdc++.h>
using namespace std;

long long answer;
vector<long long> w;
map<int, vector<int>> adj;
bool visited[300000];

long long dfs(int n) {
    visited[n] = true;
    
    long long& ret = w[n];
    for(int i : adj[n]) if(!visited[i]) {
        long long res = dfs(i);
        ret += res;
        answer += abs(res);
    }
    
    return ret;
}

long long solution(vector<int> a, vector<vector<int>> edges) {    
    for(int i : a) w.push_back((long long)i);
    
    for(auto vi : edges) {
        adj[vi[0]].push_back(vi[1]);
        adj[vi[1]].push_back(vi[0]);
    }

    return dfs(0) != 0 ? -1 : answer;
}