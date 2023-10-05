#include<iostream>

using namespace std;

int n,m;
int city[120][120]={0};

void input(){
    cin>>n>>m;
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            if(i==j) city[i][j]=0;
            else city[i][j]=10e8;
        }
    }
    for(int i=0;i<m;i++){
        int from,to,cost;
        cin>>from>>to>>cost;
        city[from][to]=min(city[from][to],cost);
    }
}

void output(){
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            if(city[i][j]==10e8) cout<<0<<' ';
            else cout<<city[i][j]<<' ';
        }
        cout<<'\n';
    }
    cout<<'\n';
}

void cal(){
    for(int k=1;k<=n;k++){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                city[i][j]=min(city[i][j],city[i][k]+city[k][j]);
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