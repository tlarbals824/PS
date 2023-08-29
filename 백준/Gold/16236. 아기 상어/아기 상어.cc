#include<iostream>
#include<vector>
#include<queue>
using namespace std;

int n;
int sea[50][50];

int dirY[4] = {-1,0,0,1};
int dirX[4] = {0,-1,1,0};

pair<int,int> currentPos;
int moveCnt = 0;
int tmpMoveCnt = 0;
int currentSize = 2;
int eatCount=0;
int check[50][50];



void input(){
    cin>>n;
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            cin>>sea[i][j];
            if(sea[i][j]==9){
                currentPos = make_pair(i,j);
                sea[i][j]=0;
            }
        }
    }
}

void output(){
    cout<<moveCnt<<'\n';
}

void clearCheck(){
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            check[i][j]=0;
        }
    }
}

void cal(){
    priority_queue<pair<int, pair<int, int> > > pq;
    pq.push(make_pair(0, make_pair(-currentPos.first, -currentPos.second)));
    check[currentPos.first][currentPos.second]=1;
    while(!pq.empty()){
        pair<int,int> tmpPQ =  pq.top().second;
        int moveTmp = -pq.top().first;
        pq.pop();
        int y = -tmpPQ.first;
        int x = -tmpPQ.second;

        // cout<<"y: "<<y<<" x: "<<x<<" MoveTmp: "<<moveTmp<<'\n';

        if(sea[y][x]<currentSize&&sea[y][x]>0){
            
            sea[y][x]=0;
            eatCount++;
            if(eatCount==currentSize){
                eatCount=0;
                currentSize++;
            }
            clearCheck();
            check[y][x]=1;
            while(!pq.empty()){
                pq.pop();
            }
            moveCnt=moveTmp;
        }

        for(int i=0;i<4;i++){
            int tmpY = y + dirY[i];
            int tmpX = x + dirX[i];
            if(tmpY>=n||tmpY<0) continue;
            if(tmpX>=n||tmpX<0) continue;
            if(sea[tmpY][tmpX]>currentSize) continue;
            if(check[tmpY][tmpX]!=0) continue;
            check[tmpY][tmpX]=1;
            pq.push(make_pair(-(moveTmp+1), make_pair(-tmpY, -tmpX)));
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