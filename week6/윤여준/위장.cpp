#include <bits/stdc++.h>
using namespace std;

map<string, int> m;

int solution(vector<vector<string>> clothes) {
    int answer = 1;
    
    for(auto vs : clothes) m[vs[1]]++;
    
    for(auto psi : m) answer *= psi.second + 1;
    
    return answer - 1;
}