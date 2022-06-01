#include <bits/stdc++.h>
using namespace std;

bool comp(const string& a, const string& b) {
    return a + b > b + a;
}

string solution(vector<int> numbers) {
    string answer = "";

    vector<string> nums;
    for(int i : numbers) nums.push_back(to_string(i));

    sort(nums.begin(), nums.end(), comp);

    for(string str : nums) answer += str;

    return answer[0] == '0' ? "0" : answer;
}