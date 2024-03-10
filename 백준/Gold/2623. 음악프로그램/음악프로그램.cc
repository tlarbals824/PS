#include <iostream>
#include <queue>
#include<set>

using namespace std;

int n, m;

vector<vector<int>> singer;
int edgeCount[1010]={0};

vector<int> result;
bool flag=false;
set<int> check;

void input() {
    cin>>n>>m;
    singer.assign(n+1, vector<int>());
    for(int i=0;i<m;i++){
        int count;
        cin>>count;
        int prevNum=-1;
        int num;
        for(int j=0;j<count;j++){
            cin>>num;
            check.insert(num);

            if(prevNum==-1){
                prevNum=num;
            }else{
                singer[prevNum].push_back(num);
                edgeCount[num]++;
                prevNum=num;
            }
        }
    }
}

void output() {
    if(flag) return;
    for(int i=0;i<n;i++){
        cout<<result[i]<<'\n';
    }
}

void cal(){
    // if(check.size()!=n){
    //     flag=true;
    //     cout<<0<<'\n';
    //     return;   
    // }
    queue<int> q;
    for(int i=1;i<=n;i++){
        if(edgeCount[i]==0){
            q.push(i);
        }
    }

    int count=0;
    while(!q.empty()){
        int front = q.front();
        q.pop();
        result.push_back(front);
        count++;

        for(int i=0;i<singer[front].size();i++){
            if(--edgeCount[singer[front][i]]==0){
                q.push(singer[front][i]);
            }
        }
    }
    if(count!=n){
        cout<<0<<'\n';
        flag=true;
    }
}

void start() {
    input();
    cal();
    output();
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    start();
}