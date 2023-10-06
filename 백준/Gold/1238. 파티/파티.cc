#include<iostream>

using namespace std;

int n,m,x;
int ary[1010][1010];
int result=0;

void input(){
    cin>>n>>m>>x;
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            if(i==j) ary[i][j]=0;
            else ary[i][j]=10e8;
        }
    }
    for(int i=0;i<m;i++){
        int from,to,cost;
        cin>>from>>to>>cost;
        ary[from][to]=cost;
    }
}

void output(){
    cout<<result<<'\n';
}

void cal(){
    for(int k=1;k<=n;k++){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                ary[i][j]=min(ary[i][j],ary[i][k]+ary[k][j]);
            }
        }
    }
    for(int i=1;i<=n;i++){
        result=max(result, ary[i][x]+ary[x][i]);
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