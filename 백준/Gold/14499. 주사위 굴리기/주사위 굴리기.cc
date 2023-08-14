#include<iostream>

using namespace std;

int n,m,x,y,k;
int dp[30][30];
int dice[7]={0};

int dirY[4]={0,0,-1,1};
int dirX[4]={1,-1,0,0};

void input(){
    cin>>n>>m>>y>>x>>k;
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            cin>>dp[i][j];
        }
    }
}

void changeDiceStatus(int command){
    int tmp = dice[1];
    if(command == 0){
        dice[1]=dice[4];
        dice[4]=dice[6];
        dice[6]=dice[3];
        dice[3]=tmp;
    }
    if(command == 1){
        dice[1]=dice[3];
        dice[3]=dice[6];
        dice[6]=dice[4];
        dice[4]=tmp;
    }
    if(command == 2){
        dice[1]=dice[5];
        dice[5]=dice[6];
        dice[6]=dice[2];
        dice[2]=tmp;
    }
    if(command == 3){
        dice[1]=dice[2];
        dice[2]=dice[6];
        dice[6]=dice[5];
        dice[5]=tmp;
    }
}

void output(){
    for(int i=0;i<k;i++){
        int command;
        cin>>command;
        command-=1;
        int tmpY = y+dirY[command];
        int tmpX = x+dirX[command];
        if(tmpY<0||tmpY>=n) continue;
        if(tmpX<0||tmpX>=m) continue;

        changeDiceStatus(command);

        if(dp[tmpY][tmpX]==0){
            dp[tmpY][tmpX]=dice[6];
        }else{
            dice[6]=dp[tmpY][tmpX];
            dp[tmpY][tmpX]=0;
        }
        cout<<dice[1]<<'\n';

        y=tmpY;
        x=tmpX;
    }
}

void start(){
    input();
    output();
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    start();
}