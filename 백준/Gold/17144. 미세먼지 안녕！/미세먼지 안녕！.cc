#include<iostream>
#include<queue>
#include<vector>
using namespace std;

int r,c,t;
int ary[1010][1010];
vector<pair<int,int>> dirt;
int airIdx = 0;
pair<int,int> air[2]; // idx : 0 위, idx : 1 아래

int dirY[4]={-1,0,1,0}; // 위,왼,아래,오
int dirX[4]={0,-1,0,1};

int result=0;

void input(){
    cin>>r>>c>>t;
    for(int i=1;i<=r;i++){
        for(int j=1;j<=c;j++){
            cin>>ary[i][j];
            if(ary[i][j]==-1){
                air[airIdx++]={i,j};
            }
            if(ary[i][j]>0){
                dirt.push_back({i,j});
            }
        }
    }
}

void output(){  
    cout<<result<<'\n';
}

void printAry(){
    cout<<'\n';
    for(int i=1;i<=r;i++){
        for(int j=1;j<=c;j++){
            cout<<ary[i][j]<<' ';
        }
        cout<<'\n';
    }
}

void sumAry(){
    int sum=0;
    for(int i=1;i<=r;i++){
        for(int j=1;j<=c;j++){
            if(ary[i][j]!=-1) sum+=ary[i][j];
        }
    }
    result=sum;
}

void spreadDirt(){  //r*c
    queue<pair<int,int>> q;
    for(int i=0;i<dirt.size();i++){
        q.push(dirt[i]);
    }
    int tmp[1010][1010]={0};
    while(!q.empty()){
        int y=q.front().first;
        int x=q.front().second;
        int cost = ary[y][x]/5;
        q.pop();
        for(int i=0;i<4;i++){
            int tmpY = y+dirY[i];
            int tmpX = x+dirX[i];
            if(tmpY<1||tmpY>r) continue;
            if(tmpX<1||tmpX>c) continue;
            if(ary[tmpY][tmpX]==-1) continue;

            tmp[tmpY][tmpX]+=cost;
            ary[y][x]-=cost;
        }
    }
    dirt.clear();
    for(int i=1;i<=r;i++){
        for(int j=1;j<=c;j++){
            ary[i][j]+=tmp[i][j];
        }
    }
}




void rotateDirt(){
    pair<int,int> up = air[0]; // 3 -> 0 -> 1 -> 2
    pair<int,int> down = air[1]; // 3 -> 2 -> 1 -> 0

    // 위쪽
    int change = 0;
    int startY = up.first;
    int startX = up.second;
    int previousDirt=0;
    while(change<4){
        int dir = (change+3)%4; // 0 1 2 3
        int tmpY = startY+dirY[dir];
        int tmpX = startX+dirX[dir];
        if(tmpY<1||tmpY>up.first||tmpX>c||tmpX<1){
            change++;
            continue;
        }
        if(tmpY!=up.first||tmpX!=up.second){
            swap(previousDirt, ary[tmpY][tmpX]);
        }
        startY=tmpY;
        startX=tmpX;  
    }

    // 아랫쪽
    change = 0;
    startY = down.first;
    startX = down.second;
    previousDirt=0;
    while(change<4){
        int dir = (3-change)%4; // 0 1 2 3
        int tmpY = startY+dirY[dir];
        int tmpX = startX+dirX[dir];
        if(tmpY>r||tmpY<down.first||tmpX>c||tmpX<1){
            change++;
            continue;
        }
        if(tmpY!=down.first||tmpX!=down.second){
            swap(previousDirt, ary[tmpY][tmpX]);
        }
        startY=tmpY;
        startX=tmpX;  
    }

    for(int i=1;i<=r;i++){
        for(int j=1;j<=c;j++){
            if(ary[i][j]>0) dirt.push_back({i,j});
        }
    }
}


void cal(){
    for(int i=0;i<t;i++){
        spreadDirt();
        rotateDirt();
    }
    sumAry();
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