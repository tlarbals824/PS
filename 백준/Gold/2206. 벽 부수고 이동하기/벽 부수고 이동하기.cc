#include<iostream>
#include<queue>
using namespace std;

int n,m;
int ary[1010][1010]={0};
int cnt[1010][1010][2]={0};
int result=10e8;

int dirY[4]={-1,0,1,0};
int dirX[4]={0,-1,0,1};

void input(){
    cin>>n>>m;
    for(int i=1;i<=n;i++){
        for(int j=1;j<=m;j++){
            char a;
            cin>>a;
            ary[i][j]=a-'0';
            cnt[i][j][0]=10e8;
            cnt[i][j][1]=10e8;
        }
    }
}

void output(){
    if(result==10e8){
        cout<<-1<<'\n';
    }else{
        cout<<result<<'\n';
    }
}


void cal(){
    cnt[1][1][0]=cnt[1][1][1]=1;
    priority_queue<pair<int,pair<int,pair<int,int>>>> pq;
    pq.push({-1,{0,{1,1}}});
    while(!pq.empty()){
        int time = -pq.top().first;
        int isBreak = pq.top().second.first;
        int y = pq.top().second.second.first;
        int x = pq.top().second.second.second;
        pq.pop();

        if(y==n&&x==m){
            result=time;
            return;
        }
        for(int i=0;i<4;i++){
            int tmpY = y+dirY[i];
            int tmpX = x+dirX[i];
            if(tmpY>n||tmpY<1) continue;
            if(tmpX>m||tmpX<1) continue;
            if(ary[tmpY][tmpX]==1){
                if(isBreak==0&&cnt[tmpY][tmpX][1]>time+1){
                    cnt[tmpY][tmpX][1]=time+1;
                    pq.push({-(time+1),{1,{tmpY,tmpX}}});
                }
            }else{
                if(cnt[tmpY][tmpX][isBreak]>time+1){
                    cnt[tmpY][tmpX][isBreak]=time+1;
                    pq.push({-(time+1),{isBreak,{tmpY,tmpX}}});
                }
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