#include <bits/stdc++.h>
using namespace std;

bool isOK(string& s) {
    string op = "([{", cl = ")]}";

    stack<char> st;
    for(char c:s) {
        if(op.find(c) != string::npos)
            st.push(c);
        else {
            if(st.empty() || op.find(st.top()) != cl.find(c))
                return false;
            st.pop();
        }
    }
    
    return st.empty();
}

int solution(string s) {
    int answer = 0;
    
    for(int i = 0; i < s.size(); i++) {
        s.push_back(s.front());
        s = s.substr(1);
        if(isOK(s)) answer++;
    }
    
    return answer;
}