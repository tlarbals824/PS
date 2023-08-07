#include<iostream>
#include<queue>

using namespace std;

int n,k;
int result=10e8;
int check[100100];

void input(){
    cin>>n>>k;
}

void output(){
    cout<<result<<'\n';
}

void cal(){
    for(int i=0;i<=100000;i++)
        check[i]=10e8;
    priority_queue<pair<int, int> > pq;
    pq.push(make_pair(0, n));
    while(!pq.empty()){
        int time = -pq.top().first;
        int idx = pq.top().second;
        pq.pop();

        if(idx>100000||idx<0) continue;

        if(idx==k) {
            result=time;
            return;
        }

        if(check[idx]<=time) continue;
        check[idx]=time;
        
        // cout<<"time: "<<time<<" idx: "<<idx<<"\n";

        pq.push(make_pair(-(time+1), idx+1));
        pq.push(make_pair(-(time+1), idx-1));
        pq.push(make_pair(-(time), idx*2));
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