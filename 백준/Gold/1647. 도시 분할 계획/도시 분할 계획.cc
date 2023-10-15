#include<iostream>
#include<queue>
#include<vector>
#include<set>
using namespace std;


int n,m;
vector<vector<pair<int,int>>> ary(100010);
set<int> costSet;
int check[100010]={0};
int result=0;

void input(){
    cin>>n>>m;
    for(int i=0;i<m;i++){
        int from,to,cost;
        cin>>from>>to>>cost;
        ary[from].push_back({to,cost});
        ary[to].push_back({from,cost});
    }
}

void output(){
    cout<<result<<'\n';
}

void cal(){
    priority_queue<pair<int,int>> pq;
    pq.push({0,1});
    while(!pq.empty()){
        int v = pq.top().second;
        int cost = -pq.top().first;
        pq.pop();
        if(check[v]==1) continue;
        check[v]=1;
        costSet.insert(cost);
        result+=cost;
        for(int i=0;i<ary[v].size();i++){
            pq.push({-ary[v][i].second,ary[v][i].first});
        }
    }
    auto iter = costSet.end();
    result=result-*(--iter);
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