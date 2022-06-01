#include <bits/stdc++.h>
using namespace std;

int solution(vector<int> priorities, int location) {
    int answer = 0;
    
    deque<pair<int,int>> dq;
    
    for(int i = 0; i < priorities.size(); i++)
        dq.push_back({i, priorities[i]});
    
    while(true) {
        pair<int,int> cur = dq.front();
        dq.pop_front();
        
        bool flag = false;
        for(auto pii : dq) {
            if(pii.second > cur.second) {
                dq.push_back(cur);
                flag = true;
                break;
            }
        }
        if(flag) continue;
        
        answer++;
        
        if(location == cur.first) break;
    }
    
    return answer;
}