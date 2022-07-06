#include <bits/stdc++.h>
using namespace std;

set<int> dp[9];

int getNum(int n, int cnt) {
    int ret = n;
    while(--cnt) {
        ret *= 10;
        ret += n;
    }
    return ret;
}

int solution(int N, int number) {
    for(int i = 1; i < 9; i++) {
        dp[i].insert(getNum(N,i));
        
        for(int j = 1; j < i; j++) {
            for(int a : dp[j])
                for(int b : dp[i-j]) {
                    dp[i].insert(a + b);
                    if(a != b) dp[i].insert(max(a - b, b - a));
                    dp[i].insert(a * b);
                    dp[i].insert(max(a / b, b / a));
                }
        }
        
        if(dp[i].find(number) != dp[i].end()) return i;
    }
    
    return -1;
}