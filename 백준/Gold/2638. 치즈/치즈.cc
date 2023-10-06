#include<iostream>
#include<queue>
using namespace std;

int n,m;
int ary[120][120]={0};
int dirY[4]={-1,0,1,0};
int dirX[4]={0,-1,0,1};
int cnt=0;



void input(){
    cin>>n>>m;
    for(int i=1;i<=n;i++){
        for(int j=1;j<=m;j++){
            cin>>ary[i][j];
        }
    }
}

void output(){
    cout<<cnt<<'\n';
}

vector<pair<int,int>> checkCheese(){
    queue<pair<int,int>> q;
    vector<pair<int,int>> result;
    int check[120][120]={0};
    check[1][1]=1;
    q.push({1,1});
    while(!q.empty()){
        int y = q.front().first;
        int x = q.front().second;
        q.pop();

        for(int i=0;i<4;i++){
            int tmpY = y+dirY[i];
            int tmpX = x+dirX[i];
            if(tmpY<1||tmpY>n) continue;
            if(tmpX<1||tmpX>m) continue;
            if(check[tmpY][tmpX]==1&&ary[tmpY][tmpX]==0) continue;
            if(ary[tmpY][tmpX]==1){
                check[tmpY][tmpX]++;
                if(check[tmpY][tmpX]==1){
                    result.push_back({tmpY,tmpX});
                }
            }else{
                check[tmpY][tmpX]=1;
                q.push({tmpY,tmpX});
            }
        }
    }
    vector<pair<int,int>> tmp;
    for(int i=0;i<result.size();i++){
        if(check[result[i].first][result[i].second]>=2){
            tmp.push_back({result[i].first,result[i].second});
        }
    }
    return tmp;
}

void cal(){
    while(true){
        vector<pair<int,int>> cheese = checkCheese();
        if(cheese.size()==0) return;
        for(int i=0;i<cheese.size();i++){
            int y =cheese[i].first;
            int x= cheese[i].second;
            ary[y][x]=0;
        }
        cnt++;
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