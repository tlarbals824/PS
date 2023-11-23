#include <string>
#include <vector>
#include<algorithm>
#include<iostream>
#include<queue>
#include<stack>

using namespace std;

struct node{
    int pre;
    int post;
    bool disable;
};

int totalSize=0;
int cursor = 0;
vector<node> nodeList;
stack<int> deletedIdx;


void execute_u(string& cmd){
    int currentMoveCnt = 0;
    int moveCnt = stoi(cmd.substr(2));
    while(currentMoveCnt<moveCnt){
        if(nodeList[cursor].pre==-1) break;
        cursor = nodeList[cursor].pre;
        currentMoveCnt++;
    }
}
void execute_d(string& cmd){
    int currentMoveCnt = 0;
    int moveCnt = stoi(cmd.substr(2));
    while(currentMoveCnt<moveCnt){
        if(nodeList[cursor].post==totalSize) break;
        cursor = nodeList[cursor].post;
        currentMoveCnt++;
    }
}
void execute_c(){
    deletedIdx.push(cursor);
    nodeList[cursor].disable=1;
    if(nodeList[cursor].pre!=-1){
        nodeList[nodeList[cursor].pre].post=nodeList[cursor].post;    
    }
    if(nodeList[cursor].post!=totalSize){
        nodeList[nodeList[cursor].post].pre=nodeList[cursor].pre;
        cursor = nodeList[cursor].post;
    }else{
        cursor = nodeList[cursor].pre;
    }
}
void execute_z(){
    if(deletedIdx.empty()) return;
    int undoIdx = deletedIdx.top();
    deletedIdx.pop();
    nodeList[undoIdx].disable=0;
    if(nodeList[undoIdx].pre!=-1)
        nodeList[nodeList[undoIdx].pre].post=undoIdx;
    if(nodeList[undoIdx].post!=totalSize)
        nodeList[nodeList[undoIdx].post].pre=undoIdx;
}

void execute(string& cmd){
    char cmdChar = cmd[0];
    if(cmdChar=='U'){
        execute_u(cmd);
    }
    if(cmdChar=='D'){
        execute_d(cmd);
    }
    if(cmdChar=='C'){
        execute_c();
    }
    if(cmdChar=='Z'){
        execute_z();
    }
}


string solution(int n, int k, vector<string> cmd) {
    string answer="";
    totalSize=n;
    cursor = k;
    for(int i=0;i<n;i++){
        nodeList.push_back(node{i-1,i+1,false});
    }
    
    for(int i=0;i<cmd.size();i++){
        execute(cmd[i]);
    }

    for(int i=0;i<n;i++){
        answer.push_back(nodeList[i].disable ? 'X' : 'O');
    }
    
    return answer;
}