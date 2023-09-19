#include<iostream>
#include<set>
#include<queue>
using namespace std;


int check[600]={0};
set<int> node[600];

int n,m;
int result=0;

void input(){
    cin>>n>>m;
}

void output(){
    cout<<result<<'\n';
}

void cal(){
    for(int i=0;i<m;i++){
        int from,to;
        cin>>from>>to;
        node[from].insert(to);
        // node[to].insert(from);
    }
    queue<int> q;
    int checkTmp[600]={0};
    for(int i=1;i<=n;i++){
        fill_n(checkTmp, n+1, 0);
        q.push(i);
        while(!q.empty()){
            int tmp = q.front();
            q.pop();
            if(checkTmp[tmp]) continue;
            checkTmp[tmp]=1;
            if(tmp!=i) {
                check[tmp]++;
                check[i]++;
            }
            for(auto iter = node[tmp].begin(); iter!=node[tmp].end();iter++){
                q.push(*iter);
            }
        }
    }
    for(int i=1;i<=n;i++){
        // cout<<check[i]<<'\n';
        if(check[i]==n-1) result++;
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