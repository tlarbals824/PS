#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

int n,k;
vector<pair<int,int>> dp;
int bag[100020]={0};
int maxValue = 0;


void input(){
    cin>>n>>k;
    for(int i=0;i<n;i++){
        int w,v;
        cin>>w>>v;
        dp.push_back({w,v});
    }
    // sort(dp.begin(), dp.end());
}

void output(){
    cout<<maxValue<<'\n';
}

void cal(){
    for(int i=0;i<n;i++){
        for(int j=k;j>=0;j--){
            if(j-dp[i].first>=0){
                bag[j]=max(bag[j],bag[j-dp[i].first]+dp[i].second);
                maxValue=max(maxValue, bag[j]);
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