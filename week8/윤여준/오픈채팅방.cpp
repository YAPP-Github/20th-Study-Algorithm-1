#include<bits/stdc++.h>
using namespace std;

map<string, string> m;

vector<string> split(string str) {
    vector<string> ret;
    string temp = "";
    for(char c: str) {
        if(c == ' ') {
            ret.push_back(temp);
            temp = "";
        }
        else temp += c;
    }
    ret.push_back(temp);
    
    return ret;
}

vector<string> solution(vector<string> record) {
    vector<string> answer;
    
    for(auto str: record) {
        vector<string> cmd = split(str);
        if(cmd[0] != "Leave")
            m[cmd[1]] = cmd[2];
    }
    
    for(auto str: record) {
        vector<string> cmd = split(str);
        if(cmd[0] == "Enter")
            answer.push_back(m[cmd[1]] + "님이 들어왔습니다.");
        else if(cmd[0] == "Leave")
            answer.push_back(m[cmd[1]] + "님이 나갔습니다.");
    }
    
    return answer;
}