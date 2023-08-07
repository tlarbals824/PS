#include<iostream>

using namespace std;

int s;
int result=10e8;
int checkTime[10000];

void input(){
    cin>>s;
}

void output(){
    cout<<result<<'\n';
}

void recursive(int cnt, int copy, int time, bool copyFlag){
    if(time>s) return;
    // cout<<"cnt: "<<cnt<<" copy: "<<copy<<" time: "<<time<<'\n';
    if(cnt<0||cnt>1000) return;
    if(time>=result) return;
    if(cnt==s){
        result=min(result, time);
    }else{
        if(checkTime[cnt]+1<time) return;
        if(checkTime[cnt]>time) checkTime[cnt]=time;
        
        recursive(cnt+copy, copy, time+1, false);
        recursive(cnt-1, copy,time+1, false);
        if(!copyFlag){
            recursive(cnt, cnt, time+1, true);
        }
    }
}

void cal(){
    for(int i=0;i<10000;i++){
        checkTime[i]=10e8;
    }
    recursive(1,1,1, true);
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