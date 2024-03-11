#include<iostream>
#include<queue>

using namespace std;

struct compare{
    bool operator()(pair<int,int> p1, pair<int,int> p2){
        if(p1.second==p2.second){
            return p1.first > p2.first;
        }
        return p1.second > p2.second;
    }
};

int n;
int result=0;
priority_queue<pair<int,int>, vector<pair<int,int>>, compare> pq;
void input(){
    cin>>n;
    for(int i=0;i<n;i++){
        int start,end;
        cin>>start>>end;
        pq.push({start,end});
    }

}

void output(){
    cout<<result<<'\n';
}

void cal(){
    int last=-1;
    while(!pq.empty()){
        pair<int,int> top = pq.top();
        pq.pop();

        if(top.first >=last){
            result++;
            last=top.second;
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