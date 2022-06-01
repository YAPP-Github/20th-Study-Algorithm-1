#include <bits/stdc++.h>
using namespace std;

int even(const string& str, int idx) {
    int ret = 2;
    int left = idx - 1, right = idx + 2;
    
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

int odd(const string& str, int idx) {
    int ret = 1;
    int left = idx - 1, right = idx + 1;
    
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
    
    for(int i = 0; i < s.size() - 1; i++) {
        answer = max(answer, odd(s, i));
        if(s[i] == s[i + 1]) answer = max(answer, even(s, i));
    }
        
    
    return answer;
}