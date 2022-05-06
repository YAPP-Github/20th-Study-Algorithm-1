#include <bits/stdc++.h>
using namespace std;

bool isOK(vector<char>& v, vector<string>& data) {
    for(string s : data) {
        char a = s[0], b = s[2];
        char op = s[3];
        int n = s[4] - '0';
        int dist = abs(find(v.begin(), v.end(), a) - find(v.begin(), v.end(), b)) - 1;
        switch(op) {
            case '=' : if(dist != n) return false; break;
            case '<' : if(dist >= n) return false; break;
            case '>' : if(dist <= n) return false; break;
        }
    }
    return true;
}

int solution(int n, vector<string> data) {
    int answer = 0;
    
    vector<char> v = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    sort(v.begin(), v.end());
    
    do {
        if(isOK(v, data)) answer++;
    }while(next_permutation(v.begin(), v.end()));
    
    return answer;
}