#include <bits/stdc++.h>
using namespace std;

vector<int> solution(vector<int> p, vector<int> speeds) {
    vector<int> answer;
    
    int i = 0;
    while(i < p.size()) {
        
        for(int j = 0; j < p.size(); j++)
            p[j] += speeds[j];
        
        int temp = 0;
        while(i < p.size() && p[i] >= 100) {
            temp++; i++;
        }
            
        if(temp > 0) answer.push_back(temp);
    }
    
    return answer;
}