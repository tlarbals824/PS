#include<iostream>
#include<queue>

using namespace std;

int n,m;
int box[1100][1100];
int checkBox[1100][1100];
bool finishFlag = false;
priority_queue<pair<int, pair<int,int> > > pq;

int dirY[4]={-1,0,1,0};
int dirX[4]={0,1,0,-1};

void input(){
    cin>>n>>m;
    for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
            cin>>box[i][j];
            if(box[i][j]==1){
                pq.push(make_pair(0,make_pair(i,j)));
                checkBox[i][j]=0;
            }else{
                checkBox[i][j]=-1;
            }
        }
    }
}

void output(){
    int result=0;
    bool flag=false;
    for(int i=0;i<m;i++){
        for(int j=0;j<n;j++){
            result=max(result,checkBox[i][j]);
            if(checkBox[i][j]==-1&&box[i][j]!=-1){
                result=-1;
                flag=true;
                break;
            }
        }
        if(flag) break;
    }
    cout<<result<<'\n';
}

void cal(){
    while(!pq.empty()){
        int time = -pq.top().first;
        pair<int,int> pos = pq.top().second;

        pq.pop();

        for(int i=0;i<4;i++){
            int tmpY = pos.first+dirY[i];
            int tmpX = pos.second+dirX[i];
            if(tmpY<0||tmpY>m) continue;
            if(tmpX<0||tmpX>n) continue;
            if(box[tmpY][tmpX]==-1 || box[tmpY][tmpX]==1) continue;

            if(checkBox[tmpY][tmpX]==-1){
                checkBox[tmpY][tmpX]=time+1;
                pq.push(make_pair(-(time+1),make_pair(tmpY,tmpX)));
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