#include <algorithm>
#include <iostream>
#include <queue>
#include <vector>
using namespace std;

int n, m;
vector<int> memories;
vector<int> costs;

int dp[101][10010]={0};
long sum=0;
int totalCost=0;


void input() {
    cin >> n >> m;

    memories.push_back(0);
    costs.push_back(0);

    for (int i = 0; i < n; i++) {
        int memory;
        cin >> memory;
        memories.push_back(memory);
        sum+=memory;
    }

    for (int i = 0; i < n; i++) {
        int cost;
        cin >> cost;
        costs.push_back(cost);
        totalCost+=cost;
    }
}

void output() {
    int result = 0;

    for(int i=1;i<=n;i++){
        for(int j=0;j<=totalCost;j++){
            if(costs[i]>j) dp[i][j]=dp[i-1][j];
            else{
                dp[i][j]=max(dp[i-1][j],dp[i-1][j-costs[i]]+memories[i]);
            }
        }
    }

    for(int i=0;i<=totalCost;i++){
        if(dp[n][i]>=m){
            result=i;
            break;
        }
    }


    cout << result << '\n';
}

void start() {
    input();
    output();
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    start();
}