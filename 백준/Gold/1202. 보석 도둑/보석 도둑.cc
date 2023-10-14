#include<iostream>
#include<vector>
#include<algorithm>
#include<set>
#include<queue>
using namespace std;

struct compare{
    bool operator()(pair<int,int> a, pair<int,int> b){
        return a.second < b.second;
    }
};

int n,k;
multiset<int> backpack;
long long result=0;
priority_queue<pair<int,int>, vector<pair<int,int>>, compare> pq;
int cnt=0;

void input(){
    cin>>n>>k;
    for(int i=0;i<n;i++){
        int m,v;
        cin>>m>>v;
        pq.push({m,v});
    }
    for(int i=0;i<k;i++){
        int c;
        cin>>c;
        backpack.insert(c);
    }
}

void output(){
    cout<<result<<'\n';
}

void cal(){
    while(!pq.empty()){
        auto [m,v] = pq.top();
        pq.pop();

        auto iter = backpack.lower_bound(m);

        if(iter == backpack.end()) continue;
        else{
            result+=v;
            backpack.erase(iter);
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