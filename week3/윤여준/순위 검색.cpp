#include <bits/stdc++.h>
using namespace std;

vector<int> db[3][2][2][2];

vector<string> split(string& s) {
    vector<string> ret; string t;

    for(char c:s) {
      if(c == ' ') {
          ret.push_back(t);
          t = "";
      }
        else t += c;
    }
    ret.push_back(t);

    return ret;
}

vector<int> solution(vector<string> info, vector<string> query) {
    vector<int> answer;
    
    for(string str: info) {
        vector<string> vs = split(str);
        int lang, job, ior, food;

        if(vs[0] == "cpp") lang = 0;
        else if(vs[0] == "java") lang = 1;
        else lang = 2;
        
        if(vs[1] == "frontend") job = 0;
        else job = 1;
        
        if(vs[2] == "junior") ior = 0;
        else ior = 1;
        
        if(vs[3] == "chicken") food = 0;
        else food = 1;
        
        db[lang][job][ior][food].push_back(stoi(vs[4]));
    }
    
    // 이분 탐색을 위한 정렬
    for(int i = 0; i <= 2; i++)
        for(int j = 0; j <= 1; j++)
            for(int k = 0; k <= 1; k++)
                for(int l = 0; l <= 1; l++)
                    sort(db[i][j][k][l].begin(), db[i][j][k][l].end());
                        
    for(string str: query) {
        vector<string> vs = split(str);
        
        int a, aa, b, bb, c, cc, d, dd;
        if(vs[0] == "cpp") a = aa = 0;
        else if(vs[0] == "java") a = aa = 1;
        else if(vs[0] == "python") a = aa = 2;
        else { a = 0; aa = 2; }
        
        if(vs[2] == "frontend") b = bb = 0;
        else if(vs[2] == "backend") b = bb = 1;
        else { b = 0; bb = 1; }
        
        if(vs[4] == "junior") c = cc = 0;
        else if(vs[4] == "senior") c = cc = 1;
        else { c = 0; cc = 1; }
        
        if(vs[6] == "chicken") d = dd = 0;
        else if(vs[6] == "pizza") d = dd = 1;
        else { d = 0; dd = 1; }
        
        // 이분 탐색
        int sum = 0;
        for(int i = a; i <= aa; i++)
            for(int j = b; j <= bb; j++)
                for(int k = c; k <= cc; k++)
                    for(int l = d; l <= dd; l++) {
                        auto iter = lower_bound(db[i][j][k][l].begin(), db[i][j][k][l].end(), stoi(vs[7]));
                        if(iter == db[i][j][k][l].end()) continue;
                        else sum += (db[i][j][k][l].size() - (iter - db[i][j][k][l].begin()));                    
                    }
                
        answer.push_back(sum);
        }
    
    return answer;
}