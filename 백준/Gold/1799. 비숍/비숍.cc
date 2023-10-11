#include<iostream>
#include<algorithm>
#include<vector>
using namespace std;

int n;
int board[11][11];
int check[11][11];
int tmpCnt=0;
int result=0;

void input(){
    cin>>n;
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            cin>>board[i][j];
        }
    }
    
}

void output(){
    cout<<result<<'\n';
}

void checkBoard(int y,int x,int cnt, int addNum){
    if(y==n+1){
        tmpCnt=max(tmpCnt,cnt);
    }else if(x>=n+1){
        checkBoard(y+1,((y+addNum)%2+1),cnt,addNum);
    }else{
        if(board[y][x]==1){
            bool flag=false;
            for(int i=x-1;i>0;i--){
                if(y+i-x<1) break;
                if(check[y+i-x][i]==1){
                    flag=true;
                    break;
                }
            }
            for(int i=x+1;i<=n;i++){
                if(y-i+x<1) break;
                if(check[y-i+x][i]==1){
                    flag=true;
                    break;
                }
            }
            if(!flag){
                check[y][x]=1;
                checkBoard(y,x+2,cnt+1,addNum);
                check[y][x]=0;
            }
        }
        checkBoard(y,x+2,cnt,addNum);
    }
}

void initialize(){
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            check[i][j]=0;
        }
    }
}

void cal(){
    initialize();
    checkBoard(1,1,0,0);
    result+=tmpCnt;
    tmpCnt=0;
    initialize();
    checkBoard(1,2,0,1);
    result+=tmpCnt;

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