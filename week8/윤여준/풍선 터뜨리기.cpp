#include <bits/stdc++.h>
using namespace std;

int solution(vector<int> a) {
    int answer = 0;
    vector<int> check(a.size(), 0);
    
    int min = 1e9;
    for(int i = 0; i < a.size(); i++) {
        if(a[i] < min) {
            min = a[i];
            check[i] = 1;
        }
    }
    min = 1e9;
    for(int i = a.size()-1; i >= 0; i--) {
        if(a[i] < min) {
            min = a[i];
            check[i] = 1;
        }
    }
    
    for(int i : check) if(i) answer++;
    
    return answer;
}