#include<iostream>
#include<queue>
using namespace std;

int n,m;
int edge[40000]={0};
vector<vector<int>> vertex;

void input(){
    cin>>n>>m;
    vertex.assign(n+1, vector<int>());
    for(int i=0;i<m;i++){
        int from,to;
        cin>>from>>to;
        vertex[from].push_back(to);
        edge[to]++;
    }
}

void cal(){
    priority_queue<int, vector<int>, greater<int>> pq;
    for(int i=1;i<=n;i++){
        if(edge[i]==0){
            pq.push(i);
        }
    }

    for(int i=1;i<=n;i++){
        int top = pq.top();
        pq.pop();

        cout<<top<<' ';

        for(int j=0;j<vertex[top].size();j++){
            if(--edge[vertex[top][j]]==0){
                pq.push(vertex[top][j]);
            }
        }
    }
    cout<<'\n';
}

void start(){
    input();
    cal();
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    start();
}