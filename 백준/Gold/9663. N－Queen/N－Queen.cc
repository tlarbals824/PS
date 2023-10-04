#include<iostream>

using namespace std;

int n;
bool board[100][100]={0};
int result=0;

void input(){
    cin>>n;
}

void output(){
    cout<<result<<'\n';
}

void recursive(int y,int x){
    if(y==n+1){
        result++;
        return;
    }
    if(x==n+1) return;

    recursive(y,x+1);

    if(x+1<=n){
        for(int i=x+1;i<=n;i++){
            if(y-i+x<1) break;
            if(board[y-i+x][i]==1) return;
        }
    }
    if(x-1>=1){
        for(int i=x-1;i>=1;i--){
            if(y+i-x<1) break;
            if(board[y+i-x][i]==1) return;
        }
    }
    if(y-1>=1){
        for(int i=y-1;i>=1;i--){
            if(board[i][x]==1) return;
        }
    }
    board[y][x]=1;
    recursive(y+1,1);
    board[y][x]=0;
}

void cal(){
    recursive(1,1);
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