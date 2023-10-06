#include<iostream>
#include<set>
#include<vector>
#include<queue>
using namespace std;


int n,m;
long long ary[1010][1010];
int v1,v2;

struct node{
    int from;
    long long cost;
    vector<int> via;
};

struct compare {
    bool operator()(node& a, node& b){
        return a.cost > b.cost;
    }
};

node result;

void input(){
    cin>>n>>m;
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            ary[i][j]=10e11;
        }
    }
    for(int i=0;i<m;i++){
        int from,to;
        long long cost;
        cin>>from>>to>>cost;
        ary[from][to]=min(cost,ary[from][to]);
    }
    cin>>v1>>v2;
}

void output(){
    cout<<result.cost<<'\n';
    cout<<result.via.size()<<'\n';
    for(auto iter = result.via.begin();iter!=result.via.end();iter++){
        cout<<*iter<<' ';
    }
    cout<<'\n';
}

void cal(){
    priority_queue<node,vector<node>,compare> pq;
    long long check[1010]={0};
    fill_n(check,1010,10e11);
    check[v1]=0;
    vector<int> tmp;
    tmp.push_back(v1);
    pq.push(node{v1,0,tmp});
    while(!pq.empty()){
        int from = pq.top().from;
        long long cost = pq.top().cost;
        vector<int> via = pq.top().via;
        pq.pop();

        if(from==v2){
            result=node{from,cost,via};
            return;
        }
        for(int i=1;i<=n;i++){
            if(ary[from][i]==10e11) continue;

            for(int j=0;j<via.size();j++){
                if(via[j]==i) continue;
            }
            long long length = ary[from][i];
            if(check[i]>cost+length){
                check[i]=cost+length;
                via.push_back(i);
                pq.push(node{i,cost+length, via});
                via.pop_back();
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