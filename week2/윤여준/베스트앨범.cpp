#include <bits/stdc++.h>
using namespace std;

struct Genre {
    string name;
    int plays;
    Genre(string name, int plays) : name(name), plays(plays) {}
};

bool operator < (const Genre& a, const Genre& b) {
    return a.plays < b.plays;    
}

struct Music {
    int n;
    int plays;
    Music(int n, int plays) : n(n), plays(plays) {}
};

bool operator < (const Music& a, const Music& b) {
    if(a.plays == b.plays) return a.n > b.n;
    else return a.plays < b.plays;
}

vector<int> solution(vector<string> genres, vector<int> plays) {
    vector<int> answer;
    
    map<string, int> genreCnt;
    for(int i = 0; i < genres.size(); i++)
        genreCnt[genres[i]] += plays[i];
    
    priority_queue<Genre> genreQ;
    for(auto g : genreCnt)
        genreQ.push(Genre(g.first, g.second));
    
    map<string, priority_queue<Music>> musics;
    for(int i = 0; i < genres.size(); i++)
        musics[genres[i]].push(Music(i, plays[i]));
    
    while(!genreQ.empty()) {
        string genre = genreQ.top().name; genreQ.pop();
        int i = 2;
        while(i-- && !musics[genre].empty()) {
            answer.push_back(musics[genre].top().n);
            musics[genre].pop();
        }
    }
    
    return answer;
}

/*
 * 2년 전 내 풀이
 * 개발 안하고 알고리즘만 할 때라 그런지 코드는 짧으나 가독성이 나쁘다...
 */

#include <bits/stdc++.h>
using namespace std;

bool pred(pair<int,string> a, pair<int,string> b){
    return a.first > b.first;
}
bool comp(pair<int,int> a, pair<int,int> b){
    if(a.first!=b.first)
        return a.first > b.first;
    return a.second < b.second;
}
vector<int> solution(vector<string> genres, vector<int> plays) {
    vector<int> answer;
    map<string,int> playSum;
    map<string,vector<pair<int,int>>> playList;
    for(int i=0;i<plays.size();i++){
        playSum[genres[i]]+=plays[i];
        playList[genres[i]].push_back(make_pair(plays[i],i));
    }
    vector<pair<int,string>> g;
    for(auto iter=playSum.begin();iter!=playSum.end();iter++)
        g.push_back(make_pair(iter->second,iter->first));
    sort(g.begin(),g.end(),pred);
    for(auto pis:g){
        sort(playList[pis.second].begin(),playList[pis.second].end(),comp);
        for(int i=0;i<playList[pis.second].size();i++){
            answer.push_back(playList[pis.second][i].second);
            if(i==1) break;
        }
    }
    return answer;
}