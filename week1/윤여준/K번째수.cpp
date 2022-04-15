#include <bits/stdc++.h>
using namespace std;

vector<int> solution(vector<int> array, vector<vector<int>> commands) {
    vector<int> answer;

    for(auto v : commands) {
        int i = v[0], j = v[1], k = v[2];

        vector<int> temp;
        for(int l = i - 1; l < j; l++) temp.push_back(array[l]);

        sort(temp.begin(), temp.end());

        answer.push_back(temp[k-1]);
    }

    return answer;
}