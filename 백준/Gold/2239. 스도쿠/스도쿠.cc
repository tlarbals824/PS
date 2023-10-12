#include<iostream>
#include<vector>

using namespace std;

int n=9;
char board[10][10];
char tmpBoard[10][10];
vector<pair<int,int>> blank;
bool flag=false;

void input(){
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            cin>>board[i][j];
            if(board[i][j]=='0'){
                blank.push_back({i,j});
            }
        }
    }
}

void printBoard(){
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            cout<<tmpBoard[i][j];
        }
        cout<<'\n';
    }
}

void output(){
    printBoard();
}

void copyBoard(){
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            tmpBoard[i][j]=board[i][j];
        }
    }
}

bool check(int y,int x, char num){
    for(int i=1;i<=n;i++){
        if(board[y][i]==num)
            return true;
        if(board[i][x]==num)
            return true;
    }
    int idxY = (y-1)/3;
    int idxX = (x-1)/3;

    for(int i=1;i<4;i++){
        for(int j=1;j<4;j++){
            if(board[idxY*3+i][idxX*3+j]==num)
                return true;
        }
    }
    return false;
}

void recursive(string num){
    if(flag) return;
    if(num.size()==blank.size()){
        copyBoard();
        flag=true;
        return;
    }
    for(int i=1;i<=n;i++){
        if(!check(blank[num.size()].first, blank[num.size()].second, '0'+i)){
            board[blank[num.size()].first][blank[num.size()].second]=(char)('0'+i);
            recursive(num+to_string(i));
            board[blank[num.size()].first][blank[num.size()].second]='0';
        }

    }
}

void cal(){
    recursive("");
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