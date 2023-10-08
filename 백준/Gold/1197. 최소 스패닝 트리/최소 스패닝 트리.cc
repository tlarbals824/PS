#include<iostream>
#include<vector>
#include<queue>

using namespace std;

int v,e;

vector<vector<pair<int,int>>> ary(10010);

int check[10010]={0};

long result=0;
int cnt=0;


void input(){
    cin>>v>>e;
    for(int i=0;i<e;i++){
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
    while(cnt<v){
        int cost = -pq.top().first;
        int from = pq.top().second;
        pq.pop();

        if(check[from]==1) continue;

        cnt++;
        check[from]=1;
        result+=cost;
        for(int i=0;i<ary[from].size();i++){
            pq.push({-ary[from][i].second, ary[from][i].first});
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