#include<iostream>
#include<queue>

using namespace std;

int n,k;
int check[100100]={0};
int result=10e8;
int cnt=0;

void input(){
    cin>>n>>k;
}

void output(){
    cout<<result<<'\n';
    cout<<cnt<<'\n';
}

void cal(){
    priority_queue<pair<int,int>> pq;
    fill_n(check,100010,10e8);
    pq.push({0,n});
    while(!pq.empty()){
        int time = -pq.top().first;
        int idx = pq.top().second;
        pq.pop();
        if(idx==k){
            if(result>time){
                result=time;
                cnt=1;
            }else if(result==time){
                cnt++;
            }
        }

        if(time+1>result) continue;

        if(idx<100000){
            if(check[idx+1]>=time+1){
                check[idx+1]=time+1;
                pq.push({-(time+1),idx+1});
            }
        }
        if(idx>0){
            if(check[idx-1]>=time+1){
                check[idx-1]=time+1;
                pq.push({-(time+1),idx-1});
            }
        }
        if(idx*2<=100000){
            if(check[idx*2]>=time+1){
                check[idx*2]=time+1;
                pq.push({-(time+1),idx*2});
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