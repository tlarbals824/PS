#include<iostream>
#include<queue>

using namespace std;

int n;
vector<vector<pair<int,int>>> ary(10010);
int node[10010]={0};
int check[10010]={0};
int result=0;

void input(){
    cin>>n;
    for(int i=0;i<n-1;i++){
        int from,to,cost;
        cin>>from>>to>>cost;
        ary[from].push_back({to,cost});
        node[from]=1;
        ary[to].push_back({from,cost});
    }
}

void output(){
    cout<<result<<'\n';
}

void recursive(int idx, int cost){
    result=max(result,cost);
    for(int i=0;i<ary[idx].size();i++){
        if(check[ary[idx][i].first]!=0) continue;
        check[ary[idx][i].first]=1;
        recursive(ary[idx][i].first,cost+ary[idx][i].second);
    }
}

void cal(){
    for(int i=1;i<=n;i++){
        if(node[i]==1) continue;
        fill_n(check,n+1,0);
        check[i]=1;
        recursive(i,0);
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