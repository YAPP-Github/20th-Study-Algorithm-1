#include <bits/stdc++.h>
#define MAX 10000000
using namespace std;

set<int> s;
bool isPrime[MAX];
bool used[10];

void solve(string made, const string& str) {
    if(made != "") s.insert(stoi(made));
    
    for(int i = 0; i < str.size(); i++) {
        if(used[i]) continue;
        made.push_back(str[i]);
        used[i] = true;
        solve(made, str);
        made.pop_back();
        used[i] = false;
    }
}

void eratos() {
    memset(isPrime, true, sizeof(isPrime));
    isPrime[0] = isPrime[1] = false;
    for(int i=2; i<=sqrt(MAX); i++)
        if(isPrime[i])
            for(int j=i*i; j<MAX; j+=i)
                isPrime[j] = false;
}

int solution(string numbers) {
    int answer = 0;
    
    eratos();
    
    solve("", numbers);
    
    for(int i : s) if(isPrime[i]) answer++;
    
    return answer;
}