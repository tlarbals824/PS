#include<iostream>
#include<queue>
#include<set>

using namespace std;

struct node{
    int to;
    long cost;
    set<int> via;
};


int v;
vector<vector<pair<int,int>>> tree(100100);
long result=0;


void input(){
    cin>>v;
    int tmp=0;
    for(int i=1;i<=v;i++){
        int from;
        cin>>from;
        while(true){
            int to;
            cin>>to;
            if(to==-1) break;
            else{
                int cost;
                cin>>cost;
                tree[from].push_back({to,cost});
            }
        }
    }
}

void output(){
    cout<<result<<'\n';
}

bool checkVia(set<int> via, int idx){
    for(auto iter = via.begin(); iter!=via.end();iter++){
        if(*iter==idx) return true;
    }
    return false;
}

void cal(){
    queue<node> q;
    set<int> tmp;
    tmp.insert(1);
    q.push({1,0,tmp});
    long check[100100]={0};
    fill_n(check, v+1, 10e10);
    check[1]=0;
    while(!q.empty()){
        int to = q.front().to;
        int cost = q.front().cost;
        set<int> via = q.front().via;
        q.pop();

        for(int i=0;i<tree[to].size();i++){
            if(checkVia(via, tree[to][i].first)) continue;
            int tmpTo = tree[to][i].first;
            long tmpCost = tree[to][i].second;
            if(check[tmpTo]>cost+tmpCost){
                via.insert(tmpTo);
                check[tmpTo]=cost+tmpCost;
                q.push({tmpTo, check[tmpTo], via});
                via.erase(tmpTo);
            }
        }
    }
    int toIdx=1;
    for(int i=1;i<=v;i++){
        if(check[i]==10e10) continue;
        if(check[toIdx]<check[i]){
            toIdx=i;
        }
    }

    set<int> tmp2;
    tmp2.insert(toIdx);
    q.push({toIdx, 0, tmp2});
    fill_n(check, v+1, 10e10);
    check[toIdx]=0;
    while(!q.empty()){
        int to = q.front().to;
        int cost = q.front().cost;
        set<int> via = q.front().via;
        q.pop();

        for(int i=0;i<tree[to].size();i++){
            if(checkVia(via, tree[to][i].first)) continue;
            int tmpTo = tree[to][i].first;
            long tmpCost = tree[to][i].second;
            if(check[tmpTo]>cost+tmpCost){
                via.insert(tmpTo);
                check[tmpTo]=cost+tmpCost;
                q.push({tmpTo, check[tmpTo], via});
                via.erase(tmpTo);
            }
        }
    }
    for(int i=1;i<=v;i++){
        if(check[i]==10e10) continue;
        result=max(result,check[i]);
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