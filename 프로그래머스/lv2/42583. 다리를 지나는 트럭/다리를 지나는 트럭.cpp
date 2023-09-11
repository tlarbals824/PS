#include <string>
#include <vector>
#include<queue>
#include<iostream>

using namespace std;

//  - -
//  - 7
//  7 -
// 7 - 4
// 7 4 5
int solution(int bridge_length, int weight, vector<int> truck_weights) {
    queue<int> q;
    for(int i=0;i<bridge_length;i++){
        q.push(-1);
    }
    int time=0;
    int curWeight=0;
    int curIdx=0;
    while(!q.empty()){
        time++;
        int outTmp = q.front();
        q.pop();
        
        if(outTmp!=-1) {
            curWeight -= truck_weights[outTmp];
            // cout<<"out:"<<time<<' '<<truck_weights[outTmp]<<"\n";
        }
        
        if(curIdx==truck_weights.size()) continue;
        int tmpWeight = truck_weights[curIdx];
        if(curWeight+tmpWeight<=weight){
            curWeight+= tmpWeight;
            q.push(curIdx);
            curIdx++;   
            // cout<<"in:"<<time<<' '<<tmpWeight<<"\n";
        }else{
            q.push(-1);
        }
    }
    
    
    return time;
}