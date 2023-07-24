#include<iostream>
#include<vector>
#include<queue>

using namespace std;

/**
 * viaPos[0][0]=0
 * viaPos[1][0]=0
 */

int n,k;
int checkPos[100100];
queue<pair<int,int> > pq;
vector<int> resultPath;
int resultTime=10e8;

void input(){
    cin>>n>>k;
    for(int i=0;i<=100000;i++){
        checkPos[i]=-1;
    }
}

void output(){
    cout<<resultTime<<'\n';
    int start=k;
    while(true){
        resultPath.push_back(start);
        if(start==n) break;
        start=checkPos[start];
    }
    for(int i=resultPath.size()-1;i>=0;i--){
        cout<<resultPath[i]<<' ';
    }
    cout<<'\n';
}

void cal(){
    pq.push(make_pair(n,0));
    while(!pq.empty()){
        pair<int,int> popTmp = pq.front();
        pq.pop();

        // cout<<popTmp.first<<' '<<popTmp.second<<'\n';

        if(popTmp.first==k){
            resultTime=min(resultTime, popTmp.second);
            return;
        }

        if(popTmp.first+1<=100000){
            if(checkPos[popTmp.first+1]==-1){
                checkPos[popTmp.first+1]=popTmp.first;

                pq.push(make_pair(popTmp.first+1, popTmp.second+1));
            }
        }

        if(popTmp.first-1>=0){
            if(checkPos[popTmp.first-1]==-1){
                checkPos[popTmp.first-1]=popTmp.first;

                pq.push(make_pair(popTmp.first-1, popTmp.second+1));
            }
        }

        if(popTmp.first*2<=100000){
            if(checkPos[popTmp.first*2]==-1){
                checkPos[popTmp.first*2]=popTmp.first;

                pq.push(make_pair(popTmp.first*2, popTmp.second+1));
            }
        }

        

        
    }
}

void start(){
    input();
    cal();
    output();
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    start();
}