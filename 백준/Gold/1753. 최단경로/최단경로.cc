#include<iostream>
#include<vector>
#include<queue>

using namespace std;

int v,e,K;
vector<vector<pair<int,int>>> ary(20000);
int check[200010];


void input(){
    cin>>v>>e>>K;
    for(int i=0;i<e;i++){
        int from,to;
        short cost;
        cin>>from>>to>>cost;
        ary[from].push_back({to,cost});
    }
    fill_n(check,v+1,10e8);
}

void output(){
    for(int i=1;i<=v;i++){
        if(check[i]==10e8){
            cout<<"INF\n";
        }else{
            cout<<check[i]<<'\n';
        }
    }
}

void cal(){
    priority_queue<pair<int,int>> q;
    q.push({0,K});
    while(!q.empty()){
        int cost = -q.top().first;
        int idx = q.top().second;
        q.pop();

        if(check[idx]>cost){
            check[idx]=cost;
        }else{
            continue;
        }

        for(int i=0;i<ary[idx].size();i++){
            if(check[ary[idx][i].first]>cost+ary[idx][i].second){
                q.push({-cost-ary[idx][i].second, ary[idx][i].first});
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