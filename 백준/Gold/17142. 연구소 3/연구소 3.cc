#include<iostream>
#include<queue>
#include<vector>

using namespace std;

int n,m;
int MAP[100][100];
int tmpMap[100][100];
int dirY[4] = {-1,0,1,0};
int dirX[4] = {0,-1,0,1};
vector<pair<int,int> > virus;
vector<int> tmp;
int minSum = 10e8;

void input(){
    cin>>n>>m;
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            cin>>MAP[i][j];
            if(MAP[i][j]==2){
                virus.push_back(make_pair(i,j));
            }
        }
    }
}

void output(){
    if(minSum==10e8)
        cout<<-1;
    else
        cout<<minSum;

    cout<<'\n';
}

void checkMap(){
    int sum=1;
    for(int i =0;i<n;i++){
        for(int j=0;j<n;j++){
            if(tmpMap[i][j]==0&&MAP[i][j]==0)
                return;
            if(tmpMap[i][j]!=0&&MAP[i][j]==0)
                sum=max(sum, tmpMap[i][j]);
        }
    }
    minSum = min(minSum, sum-1);
}

void recursive(int idx, int size){
    if(idx>virus.size()) return;
    if(size==m){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                tmpMap[i][j]=0;
            }
        }
        queue<pair<int,int> > q;
        for(int i=0;i<m;i++){
            pair<int,int> tmpPair = virus[tmp[i]];
            q.push(tmpPair);
            tmpMap[tmpPair.first][tmpPair.second]=1;
        }
        while(!q.empty()){
            int y = q.front().first;
            int x = q.front().second;
            q.pop();
            for(int i=0;i<4;i++){
                int tmpY = y + dirY[i];
                int tmpX = x + dirX[i];
                if(tmpY <0 || tmpY>=n) continue;
                if(tmpX <0 || tmpX>=n) continue;
                if(MAP[tmpY][tmpX]==1) continue;
                if(tmpMap[tmpY][tmpX]>0) continue;

                tmpMap[tmpY][tmpX]=tmpMap[y][x]+1;
                q.push(make_pair(tmpY, tmpX));
            }
        }

        checkMap();
        
    }else{
        tmp.push_back(idx);
        recursive(idx+1, size+1);
        tmp.pop_back();
        recursive(idx+1, size);
    }
}

void cal(){
    recursive(0,0);
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