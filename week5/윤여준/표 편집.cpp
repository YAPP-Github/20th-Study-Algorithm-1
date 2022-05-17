#include <bits/stdc++.h>
using namespace std;

struct Node {
    int n;
    Node* prev;
    Node* next;
    Node(int n, Node* prev, Node* next) : n(n), prev(prev), next(next) {}
};

string solution(int n, int k, vector<string> cmd) {
    string answer(n, 'X');
    
    Node* cursor = new Node(0, NULL, NULL);
    for(int i = 1; i < n; i++) {
        cursor->next = new Node(i, cursor, NULL);
        cursor = cursor->next;
    }
    
    for(int i = 0; i< n - k - 1; i++) cursor = cursor->prev;
    
    stack<Node*> del;
    for(string str : cmd) {
        if(str[0] == 'U' || str[0] == 'D') {
            int x = stoi(str.substr(2));
            if(str[0] == 'U') while(x--) cursor = cursor->prev;
            else while(x--) cursor = cursor->next;
        }
        else if(str[0] == 'C') {
            del.push(cursor);
            if(cursor->prev != NULL) cursor->prev->next = cursor->next;
            if(cursor->next != NULL) cursor->next->prev = cursor->prev;
            if(cursor->next == NULL) cursor = cursor->prev;
            else cursor = cursor->next;
        }
        else {
            Node* r = del.top(); del.pop();
            if(r->prev != NULL) r->prev->next = r;
            if(r->next != NULL) r->next->prev = r;
        }
    }
    
    answer[cursor->n] = 'O';
    while(cursor->prev != NULL) {
        answer[cursor->prev->n] = 'O';
        cursor = cursor->prev;
    }
    while(cursor->next != NULL) {
        answer[cursor->next->n] = 'O';
        cursor = cursor->next;
    }
    
    return answer;
}