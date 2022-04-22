#include <bits/stdc++.h>
using namespace std;

vector<int> solution(vector<int> lottos, vector<int> win_nums) {
    vector<int> answer(2);
    
    set<int> nums;
    for(int i : win_nums) nums.insert(i);
    
    int cnt = 0, bonus = 0;
    for(int i : lottos) {
        if(i == 0) {
            bonus++;
            continue;
        }
        if(nums.find(i) != nums.end()) {
            cnt++;
            nums.erase(i);
        }
    }
    
    answer[0] = min(7 - cnt - bonus, 6);
    answer[1] = min(7 - cnt, 6);
    
    return answer;
}