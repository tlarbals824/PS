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

void printCheck(){
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            cout<<check[i][j]<<' ';
        }
        cout<<'\n';
    }
}

void checkBoardBlack(int y,int x,int cnt){
    if(y==n+1){
        tmpCnt=max(tmpCnt,cnt);
    }else if(x>=n+1){
        checkBoardBlack(y+1,((y)%2+1),cnt);
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
                checkBoardBlack(y,x+2,cnt+1);
                check[y][x]=0;
            }
        }
        checkBoardBlack(y,x+2,cnt);
    }
}

void checkBoardWhite(int y,int x,int cnt){
    if(y==n+1){
        tmpCnt=max(tmpCnt,cnt);
    }else if(x>=n+1){
        checkBoardWhite(y+1,((y+1)%2+1),cnt);
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
                checkBoardWhite(y,x+2,cnt+1);
                check[y][x]=0;
            }
        }
        checkBoardWhite(y,x+2,cnt);
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
    checkBoardBlack(1,1,0);
    result+=tmpCnt;
    tmpCnt=0;
    initialize();
    checkBoardWhite(1,2,0);
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