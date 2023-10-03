#include<iostream>

using namespace std;

int n,e;
int graph[1000][1000]={0};
int v1,v2;
int result = 10e8;

void input(){
    cin>>n>>e;
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            graph[i][j]=10e8;
            if(i==j) graph[i][j]=0;
        }
    }
    for(int i=0;i<e;i++){
        int from,to,cost;
        cin>>from>>to>>cost;
        graph[from][to]=cost;
        graph[to][from]=cost;
    }
    cin>>v1>>v2;
}

void output(){
    cout<<result<<'\n';
}

void cal(){
    for(int k=1;k<=n;k++){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(i==j) continue;
                // if(i==v1||i==v2) continue;
                // if(j==v1||j==v2) continue;
                // if(k==v1||k==v2) continue;
                if(graph[i][j]>graph[i][k]+graph[k][j]){
                    graph[i][j]=graph[i][k]+graph[k][j];
                }
            }
        }
    }
    // for(int i=1;i<=n;i++){
    //     for(int j=1;j<=n;j++){
    //         cout<<graph[i][j]<<' ';
    //     }
    //     cout<<'\n';
    // }
    // cout<<'\n';
    if(graph[1][v1]!=10e8&&graph[v2][n]!=10e8){
        result=min(graph[1][v1]+graph[v1][v2]+graph[v2][n],result);
    }
    if(graph[1][v2]!=10e8&&graph[v1][n]!=10e8){
        result=min(graph[1][v2]+graph[v2][v1]+graph[v1][n],result);
    }
    if(result==10e8) result=-1;
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