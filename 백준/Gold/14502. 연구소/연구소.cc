#include<iostream>
#include<vector>
#include<queue>
using namespace std;

int n,m;
int ary[10][10];
vector<pair<int,int>> virus;

int dirY[4] = {-1,0,1,0};
int dirX[4] = {0,-1,0,1};

int result=0;

void input(){
    cin>>n>>m;
    for(int i=1;i<=n;i++){
        for(int j=1;j<=m;j++){
            cin>>ary[i][j];
            if(ary[i][j]==2){
                virus.push_back({i,j});
            }
        }
    }
}

void output(){
    cout<<result<<'\n';
}

void check(){
    queue<pair<int,int>> q;
    for(int i=0;i<virus.size();i++){
        q.push(virus[i]);
    }
    int tmp[20][20];
    for(int i=1;i<=n;i++){
        for(int j=1;j<=m;j++){
            tmp[i][j]=ary[i][j];
        }
    }
    while(!q.empty()){
        int y = q.front().first;
        int x= q.front().second;
        q.pop();

        for(int i=0;i<4;i++){
            int tmpY = y+dirY[i];
            int tmpX = x+dirX[i];
            if(tmpY<1||tmpY>n) continue;
            if(tmpX<1||tmpX>m) continue;
            if(tmp[tmpY][tmpX]!=0) continue;

            tmp[tmpY][tmpX]=2;
            q.push({tmpY,tmpX});
        }
    }
    int cnt=0;
    for(int i=1;i<=n;i++){
        for(int j=1;j<=m;j++){
            if(tmp[i][j]==0) cnt++;
        }
    }
    result=max(result,cnt);
}

void recursive(int y,int x, int cnt){
    if(cnt == 3){
        check();
        return;
    }
    
    if(x>m) {
        recursive(y+1,1,cnt);
    }else if(y>n){
        return;
    }else{
        if(ary[y][x]==0){
            ary[y][x]=1;
            recursive(y,x+1,cnt+1);
            ary[y][x]=0;
        }
        recursive(y,x+1,cnt);
    }
}

void cal(){
    recursive(1,1,0);
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