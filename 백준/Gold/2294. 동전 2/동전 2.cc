#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

int n,k;

vector<int> coins;
int dp[10100]={0};

void input(){
    cin>>n>>k;
    for(int i=0;i<n;i++){
        int coin;
        cin>>coin;
        coins.push_back(coin);
    }
    sort(coins.begin(), coins.end());
}

void output(){
    cout<<(dp[k]==10e8 ? -1 : dp[k])<<'\n';
}

void cal(){
    fill_n(dp, k+1, 10e8);
    dp[0]=0;
    for(int i=0;i<n;i++){
        for(int j=1;j<=k;j++){
            if(j >= coins[i]){
                dp[j]=min(dp[j-coins[i]]+1, dp[j]);
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