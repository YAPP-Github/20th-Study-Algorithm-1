#include <bits/stdc++.h>
//#define int long long
using namespace std;

int ad[360000];

// 문자열 → 초 변환
int strToSec(string s){
    int ret = 0;

    string h = s.substr(0,2);
    string m = s.substr(3,2);
    string c = s.substr(6,2);

    ret += stoi(h) * 60 * 60;
    ret += stoi(m) * 60;
    ret += stoi(c);

    return ret;
}

// 초 → 문자열 변환
string secToStr(int n){
    string ret = "";

    int s = n % 60; n /= 60;
    int m = n % 60; n /= 60;
    int h = n;

    if(h < 10) ret += "0";
    ret += to_string(h);
    ret += ":";

    if(m < 10) ret += "0";
    ret += to_string(m);
    ret += ":";

    if(s < 10) ret += "0";
    ret += to_string(s);

    return ret;
}

string solution(string play_time, string adv_time, vector<string> logs) {
    for(string s : logs){
        int start = strToSec(s.substr(0,8));
        int finish = strToSec(s.substr(9,8));
        for(int i = start; i < finish; i++) ad[i]++; // 시청자 수 누적
    }

    int N = strToSec(play_time); // 전체 구간 길이
    int len = strToSec(adv_time); // 광고 구간 길이

    int idx=0;
    long long sum=0; 
    long long maxSum=0; // 최댓값 : 360,000 * 300,000 > INT_MAX

    queue<int> q;

    for(int i = 0; i < len; i++){
        sum += ad[i];
        q.push(ad[i]);
    }
    maxSum=sum;

    // 투포인터를 이용한 고정된 길이의 구간 합 계산 : O(N)
    for(int i = len; i < N; i++){
        sum += ad[i];
        q.push(ad[i]);
        sum -= q.front();
        q.pop();
        if(sum > maxSum){
            idx = i - len + 1;
            maxSum = sum;
        }
    }

    return secToStr(idx);
}