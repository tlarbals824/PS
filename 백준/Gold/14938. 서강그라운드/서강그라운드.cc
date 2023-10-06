#include<iostream>

using namespace std;

int n,m,r;
int item[120];
int ary[120][120];
int result=0;

void input(){
    cin>>n>>m>>r;
    for(int i=1;i<=n;i++){
        cin>>item[i];
    }
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            if(i==j) ary[i][j]=0;
            else ary[i][j]=10e8;
        }
    }
    for(int i=0;i<r;i++){
        int from,to,cost;
        cin>>from>>to>>cost;
        ary[from][to]=cost;
        ary[to][from]=cost;
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
    // for(int i=1;i<=n;i++){
    //     for(int j=1;j<=n;j++){
    //         cout<<ary[i][j]<<' ';
    //     }
    //     cout<<'\n';
    // }
    for(int i=1;i<=n;i++){
        int sum=0;
        for(int j=1;j<=n;j++){
            sum+=(ary[i][j]<=m?item[j]:0);
        }
        result=max(sum,result);
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