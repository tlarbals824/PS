#include<iostream>
#include<set>
#include<vector>
#include<algorithm>

using namespace std;

int n,k;

vector<int> coins;
int dp[10010]={0};

void input(){
    cin>>n>>k;
    coins.push_back(0);
    for(int i=0;i<n;i++){
        int coin;
        cin>>coin;
        coins.push_back(coin);
    }
    sort(coins.begin(), coins.end());
}

void output(){
    cout<<dp[k]<<'\n';
}

void cal(){
    // dp[i][j]=dp[i-coins[j]][j]+dp[i][j-1]
    dp[0]=1;
    for(int i=1;i<=n;i++){
        for(int j=1;j<=k;j++){ // 
            dp[j]=dp[j]+( j >= coins[i] ? dp[j-coins[i]] : 0);
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