#include<iostream>
#include<queue>

using namespace std;

int n,m;
int box[1100][1100];
bool finishFlag = false;
int result=0;
queue<pair<int, pair<int,int> > > q;

int dirY[4]={-1,0,1,0};
int dirX[4]={0,1,0,-1};

void input(){
    cin>>n>>m;
    for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
            cin>>box[i][j];
            if(box[i][j]==1){
                q.push(make_pair(-1,make_pair(i,j)));
            }
        }
    }
}

void output(){
    bool flag=false;
    for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
            result=max(result,box[i][j]);
            if(box[i][j]==0){
                flag=true;
                break;
            }
        }
        if(flag) break;
    }
    if(flag){
        cout<<-1<<'\n';
    }else{
        cout<<result-1<<'\n';
    }
}

void cal(){
    while(!q.empty()){
        int time = -q.front().first;
        pair<int,int> pos = q.front().second;

        q.pop();

        for(int i=0;i<4;i++){
            int tmpY = pos.first+dirY[i];
            int tmpX = pos.second+dirX[i];
            if(tmpY<0||tmpY>=m) continue;
            if(tmpX<0||tmpX>=n) continue;
            if(box[tmpY][tmpX]!=0) continue;
            box[tmpY][tmpX]=time+1;
            q.push(make_pair(-(time+1),make_pair(tmpY,tmpX)));
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