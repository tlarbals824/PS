#include<iostream>
#include<queue>

using namespace std;

int t,n,k,w;

int edgeCount[1010]={0};
int cost[1010]={0};
int sumCost[1010]={0};
vector<vector<int>> vertex;

void cal(){
    cin>>n>>k;
    fill_n(edgeCount, 1001, 0);
    fill_n(cost, 1001, 0);
    fill_n(sumCost, 1001, 0);
    vertex.clear();
    vertex.assign(n+1,vector<int>());
    for(int i=1;i<=n;i++){
        cin>>cost[i];
    }

    for(int i=0;i<k;i++){
        int from,to;
        cin>>from>>to;
        vertex[from].push_back(to);
        edgeCount[to]++;
    }
    cin>>w;

    queue<int> q;

    for(int i=1;i<=n;i++){
        if(edgeCount[i]==0){
            q.push(i);
            sumCost[i]=cost[i];
        }
    }

    for(int i=0;i<n;i++){
        int idx = q.front();
        q.pop();

        for(int j=0;j<vertex[idx].size();j++){
            sumCost[vertex[idx][j]]=max(sumCost[idx], sumCost[vertex[idx][j]]);
            if(--edgeCount[vertex[idx][j]]==0){
                q.push(vertex[idx][j]);
                sumCost[vertex[idx][j]]+=cost[vertex[idx][j]];
                if(vertex[idx][j]==w){
                    while(!q.empty()){
                        q.pop();
                    }
                    i=n;
                    break;
                }
            }
        }
    }
    cout<<sumCost[w]<<'\n';
}

void input(){
    cin>>t;
    for(int i=0;i<t;i++){
        cal();
    }
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    input();
}