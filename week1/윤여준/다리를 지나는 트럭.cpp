#include <bits/stdc++.h>
using namespace std;

int solution(int len, int weight, vector<int> weights) {
    int answer = 0;
    int weightSum = 0;
    deque<pair<int, int>> dq;
    
    for(int i = 0; i < weights.size(); i++) {
        answer++;

        if(!dq.empty() && (answer - dq.front().second) == len) {
            weightSum -= dq.front().first;
            dq.pop_front();
        }

        if(dq.size() + 1 <= len && weightSum + weights[i] <= weight) {
            dq.push_back({weights[i], answer});
            weightSum += weights[i];
        }
        else i--;
    }

    answer += len - (answer - dq.back().second);

    return answer;
}