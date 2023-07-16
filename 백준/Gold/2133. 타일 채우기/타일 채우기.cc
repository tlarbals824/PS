#include<iostream>

using namespace std;

int n;
int result;
int dp[50];

void input(){
    cin>>n;
}

void output(){
    cout<<dp[n]<<'\n';
}

void cal(){
    dp[0]=1;
    for(int i=2;i<=n;){
        dp[i]=dp[i-2]*3;
        for(int j=i-4;j>=0;){
            dp[i]+=dp[j]*2;
            j-=2;
        }
        i+=2;
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