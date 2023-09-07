#include<iostream>
#include<queue>

using namespace std;

int n;
char ary[101][101];
int check[101][101]={0};
int normalCnt=0;
int abnormalCnt=0;

int dirY[4]={-1,0,1,0};
int dirX[4]={0,-1,0,1};

void input(){
    cin>>n;
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            cin>>ary[i][j];
        }
    }
}

void normal(int y,int x){
    queue<pair<int,int>> q;
    q.push({y,x});
    check[y][x]=1;
    while(!q.empty()){
        auto [tmpY, tmpX] = q.front();
        q.pop();
        for(int i=0;i<4;i++){
            int moveY = tmpY+dirY[i];
            int moveX = tmpX+dirX[i];
            if(moveY<0||moveY>=n) continue;
            if(moveX<0||moveX>=n) continue;
            if(ary[tmpY][tmpX]!=ary[moveY][moveX]) continue;
            if(check[moveY][moveX]!=0) continue;
            check[moveY][moveX]=1;
            q.push({moveY, moveX});
        }
    }
}

void abnormal(int y,int x){
    queue<pair<int,int>> q;
    q.push({y,x});
    check[y][x]=1;
    while(!q.empty()){
        auto [tmpY, tmpX] = q.front();
        char tmpColor = ary[tmpY][tmpX];
        q.pop();
        for(int i=0;i<4;i++){
            int moveY = tmpY+dirY[i];
            int moveX = tmpX+dirX[i];
            if(moveY<0||moveY>=n) continue;
            if(moveX<0||moveX>=n) continue;
            if(check[moveY][moveX]!=0) continue;

            char moveColor = ary[moveY][moveX];

            if((moveColor=='R'||moveColor=='G')&&(tmpColor=='B')) continue;
            if((tmpColor=='R'||tmpColor=='G')&&(moveColor=='B')) continue;

            check[moveY][moveX]=1;

            q.push({moveY, moveX});
        }
    }
}

void cal(){
    int cnt=0;
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            if(check[i][j]==0){
                normal(i,j);
                cnt++;
            }
        }
    }
    cout<<cnt<<'\n';
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            check[i][j]=0;
        }
    }
    cnt=0;
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            if(check[i][j]==0){
                abnormal(i,j);
                cnt++;
            }
        }
    }
    cout<<cnt<<'\n';
}

void start(){
    input();
    cal();
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    start();
}