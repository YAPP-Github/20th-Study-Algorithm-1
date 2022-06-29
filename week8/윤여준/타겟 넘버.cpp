#include <bits/stdc++.h>
using namespace std;

int solve(int idx, int sum, vector<int>& nums, int target) {
    if(idx == nums.size()) {
        if(sum == target) return 1;
        return 0;
    }
    
    return solve(idx+1, sum + nums[idx], nums, target) + solve(idx+1, sum - nums[idx], nums, target);
}

int solution(vector<int> numbers, int target) {
    return solve(0, 0, numbers, target);
}