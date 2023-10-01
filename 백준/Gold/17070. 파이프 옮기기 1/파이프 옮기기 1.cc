#include<iostream>

using namespace std;

int n;
int house[20][20];
int result=0;
int dirY[3]={0,1,1};
int dirX[3]={1,1,0};

void input(){
    cin>>n;
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            cin>>house[i][j];
        }
    }
}

void output(){
    cout<<result<<'\n';
}

void recursive(pair<int,int> tail, pair<int,int> head, int dir);

void checkMove(pair<int,int> start, int dir){
    int moveY = start.first+dirY[dir];
    int moveX = start.second+dirX[dir];
    if(moveY>n||moveX>n) return;
    bool flag = true;
    for(int i=start.first;i<=moveY;i++){
        for(int j=start.second;j<=moveX;j++){
            if(house[i][j]==1){
                flag=false;
                break;
            }
        }
    }
    if(flag){
        recursive(start, {moveY,moveX},dir);
    }
}

void recursive(pair<int,int> tail, pair<int,int> head, int dir){ // 0 가로, 1 대각선, 2세로
    if(head.first==n&&head.second==n){
        result++;
    }else{
        checkMove(head, dir);

        if(dir == 0){
            checkMove(head, 1);
        }
        if(dir == 1){
            checkMove(head,0);
            checkMove(head,2);
        }
        if(dir ==2){
            checkMove(head,1);
        }
    }
}

void cal(){
    recursive({1,1},{1,2}, 0);
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