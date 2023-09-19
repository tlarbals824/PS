#include<iostream>
#include<vector>
#include<string>
#include<set>

using namespace std;

int n =5;
char ary[10][10];
bool doubleCheck[10][10]={0};
int dirY[4]={-1,0,1,0};
int dirX[4]={0,-1,0,1};
int result=0;
set<vector<vector<int>>> uniqueSet;

void input(){
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            cin>>ary[i][j];
        }
    }
}

void output(){
    cout<<uniqueSet.size()<<'\n';
}

void recursive(int y,int x,int cnt, int sCnt,vector<vector<int>> check){
    if(cnt==7){
        if(sCnt>=4){
            uniqueSet.insert(check);
        }
    }else{
        for(int i=0;i<4;i++){
            int tmpY = y+dirY[i];
            int tmpX = x+dirX[i];
            if(tmpY>n||tmpY<1) continue;
            if(tmpX>n||tmpX<1) continue;

            if(check[tmpY][tmpX]){
                if(doubleCheck[tmpY][tmpX]) continue;
                doubleCheck[y][x]=true;
                recursive(tmpY,tmpX, cnt, sCnt, check);
                doubleCheck[y][x]=false;
            }else{
                check[tmpY][tmpX]=1;
                recursive(tmpY,tmpX, cnt+1, (ary[tmpY][tmpX]=='S'?sCnt+1:sCnt),check);
                check[tmpY][tmpX]=0;
            }
        }
    }
}

void initialDoubleCheck(){
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            doubleCheck[i][j]=false;
        }
    }
}

void cal(){
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            initialDoubleCheck();
            vector<vector<int>> tmp(6, vector<int>(6,0));
            tmp[i][j]=1;
            recursive(i,j,1,(ary[i][j]=='S'?1:0),tmp);
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