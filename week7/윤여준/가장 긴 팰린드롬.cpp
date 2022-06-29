#include <bits/stdc++.h>
using namespace std;

int isPalin(const string& str, int left, int right) {
    int ret = 1;
    
    while(left >= 0 && right < str.size()) {
        if(str[left] == str[right]) {
            ret += 2;
            left -= 1;
            right += 1;
        }
        else break;
    }
    
    return ret;
}

int solution(string s) {
    int answer = 1;
    
    for(int i = 1; i < s.size() - 1; i++)
        answer = max(isPalin(s, i, i + 1), isPalin(s, i - 1, i + 1));
    
    return answer;
}