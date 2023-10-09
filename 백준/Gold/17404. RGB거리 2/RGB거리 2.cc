#include<iostream>

using namespace std;

int n;

int ary[1010][3]={0};
int result=10e7;

void input(){
    cin>>n;
    for(int i=1;i<=n;i++){
        for(int j=0;j<3;j++){
            cin>>ary[i][j];
        }
    }
}

void output(){
    cout<<result<<'\n';
}

void cal(){
    for(int i=0;i<3;i++){
        int dp[1010][3];
        for(int j=0;j<3;j++){
            if(i==j) dp[1][i]=ary[1][i];
            else dp[1][j]=10e7;
        }
        for(int j=2;j<=n;j++){
            dp[j][0]=min(dp[j-1][1],dp[j-1][2])+ary[j][0];
            dp[j][1]=min(dp[j-1][0],dp[j-1][2])+ary[j][1];
            dp[j][2]=min(dp[j-1][0],dp[j-1][1])+ary[j][2];
        }
        for(int j=0;j<3;j++){
            if(i==j) continue;
            result=min(dp[n][j],result);
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