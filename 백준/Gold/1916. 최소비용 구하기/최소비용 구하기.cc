#include<iostream>
#include<vector>
#include<algorithm>
#include<queue>
using namespace std;

int n,m;
vector<pair<int,int>> bus[1001];
int check[1010];
int startIdx,endIdx;
int result=10e8;

void input(){
    cin>>n>>m;
    for(int i=0;i<m;i++){
        int from,to,cost;
        cin>>from>>to>>cost;
        bus[from].push_back({to,cost});
    }
    for(int i=0;i<=n;i++){
        check[i]=10e8;
    }
    cin>>startIdx>>endIdx;
}

void output(){
    cout<<result<<'\n';
}

void cal(){
    priority_queue<pair<int,int>> q;
    q.push({0,startIdx});
    while(!q.empty()){
        int tmpCost = -q.top().first;
        int tmpFrom = q.top().second;
        q.pop();

        if(tmpFrom == endIdx){
            result=tmpCost;
            return;
        }else{
            for(int i = 0;i<bus[tmpFrom].size();i++){
                if(check[bus[tmpFrom][i].first]>tmpCost+bus[tmpFrom][i].second){
                    q.push({-(tmpCost+bus[tmpFrom][i].second),bus[tmpFrom][i].first});
                    check[bus[tmpFrom][i].first]=tmpCost+bus[tmpFrom][i].second;
                }
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
    cout.tie(0);

    start();
}