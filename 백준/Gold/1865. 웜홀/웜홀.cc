#include<iostream>
#include<vector>
using namespace std;

int tc;
int n,m,w;
int ary[600][600];

void cal(){
    for(int k=1;k<=n;k++){
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                ary[i][j]=min(ary[i][k]+ary[k][j],ary[i][j]);
            }
        }
    }
    for(int i=1;i<=n;i++){
        if(ary[i][i]<0){
            cout<<"YES\n";
            return;
        }
    }
    cout<<"NO\n";
}

void initialize(){
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            if(i==j) ary[i][j]=0;
            else ary[i][j]=10e8;
        }
    }
}

void input(){
    cin>>tc;
    for(int i=0;i<tc;i++){
        cin>>n>>m>>w;
        int from,to,cost;
        initialize();
        for(int j=0;j<m;j++){
            cin>>from>>to>>cost;
            ary[from][to]=min(cost,ary[from][to]);
            ary[to][from]=min(cost,ary[to][from]);
        }
        for(int j=0;j<w;j++){
            cin>>from>>to>>cost;
            ary[from][to]=min(-cost,ary[from][to]);
        }
        cal();
    }
}

void start(){
    input();
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    start();
}