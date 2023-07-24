#include<iostream>
#include<queue>

using namespace std;

int n,m;
int maze[150][150];
int checkPos[150][150];
priority_queue<pair<int,pair<int,int> > > q;

int dirY[4]={-1,0,1,0};
int dirX[4]={0,1,0,-1};

int destCnt=0;

void input(){
    cin>>n>>m;
    for(int i=1;i<=m;i++){
        for(int j=1;j<=n;j++){
            char check;
            cin>>check;
            maze[i][j]=check-'0';
            checkPos[i][j]=10e8;
        }
    }
}

void output(){
    cout<<destCnt<<'\n';
}

void cal(){
    q.push(make_pair(0,make_pair(1,1)));

    while(!q.empty()){
        int time = -(q.top().first);
        pair<int,int> pos = q.top().second;

        q.pop();

        if(pos.first==m&&pos.second==n){
            destCnt=time;
            return;
        }


        if(checkPos[pos.first][pos.second]>time){
            checkPos[pos.first][pos.second]=time;
            for(int i=0;i<4;i++){
                int tmpY = pos.first+dirY[i];
                int tmpX = pos.second+dirX[i];
                if(tmpY<=0||tmpY>m) continue;
                if(tmpX<=0||tmpX>n) continue;
                int tmpTime=time;
                if(maze[tmpY][tmpX]==1){
                    tmpTime++;
                }
                q.push(make_pair(-tmpTime, make_pair(tmpY,tmpX)));
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