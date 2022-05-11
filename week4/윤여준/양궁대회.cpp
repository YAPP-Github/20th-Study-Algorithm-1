#include <bits/stdc++.h>
using namespace std;

int maxScore;
vector<int> answer;

// 더 낮은 과녁에 맞혔는지
bool isBetter(vector<int>& v) {
    for(int i = 10; i >= 0; i--) {
        if(v[i] > answer[i]) return true;
        else if(v[i] < answer[i]) return false;
    }
}

// 점수차 계산
int calcScore(vector<int>& ryan, vector<int>& apeach) {
    int ryanScore = 0, apeachScore = 0;

    for(int i = 0; i <= 10; i++) {
        if(ryan[i] + apeach[i] == 0) continue;
        if(ryan[i] > apeach[i]) ryanScore += (10 - i);
        else apeachScore += (10 - i);
    }
    
    return ryanScore - apeachScore;
}

// 재귀 완탐
void solve(int idx, int arrowCnt, vector<int>& ryan, vector<int>& apeach) {
    if(idx == 11) {
        int cand = calcScore(ryan, apeach); if(cand <= 0) return;
        
        ryan[10] += arrowCnt; // 남은 화살 0점 과녁에 몰아주기
        if(cand > maxScore || cand == maxScore && isBetter(ryan)) {
            maxScore = cand;
            answer = ryan;
        }
        
        return;
    }
    
    // 점수 얻기로 결정
    if(arrowCnt > apeach[idx]) {
        ryan.push_back(apeach[idx]+1);
        solve(idx+1, arrowCnt-apeach[idx]-1, ryan, apeach);
        ryan.pop_back();
    }
    
    // 패스
    ryan.push_back(0);
    solve(idx+1, arrowCnt, ryan, apeach);
    ryan.pop_back();
}

vector<int> solution(int n, vector<int> info) {
    vector<int> ryan;
    solve(0, n, ryan, info);
    
    return maxScore == 0 ? vector<int>(1,-1) : answer; // 못 이기면 [-1]
}