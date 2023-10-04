#include<iostream>
#include<string>
#include<queue>
#include<set>

using namespace std;

int r,c;
char board[30][30];
int result=-1;
int dirY[4]={1,0,-1,0};
int dirX[4]={0,1,0,-1};
set<string> check;
bool alphaCheck[27];
string alpha = "";

void input(){
    cin>>r>>c;
    for(int i=1;i<=r;i++){
        for(int j=1;j<=c;j++){
            cin>>board[i][j];
        }
    }
}

void output(){
    cout<<result<<'\n';
}

bool checkAlpha(string alpha, char difChar){
    for(int i=0;i<alpha.size();i++){
        if(alpha[i]==difChar) return true;
    }
    return false;
}

void recursive(int y,int x){
    int alphaLength = alpha.size();
    result=max(result,alphaLength);
    for(int i=0;i<4;i++){
        int tmpY = y+dirY[i];
        int tmpX = x+dirX[i];
        if(tmpY<1||tmpY>r) continue;
        if(tmpX<1||tmpX>c) continue;
        if(alphaCheck[board[tmpY][tmpX]-'A']) continue;
        // if(check.find(alpha+board[tmpY][tmpX])!=check.end()) continue;
        alpha+=board[tmpY][tmpX];
        alphaCheck[board[tmpY][tmpX]-'A']=true;
        // check.insert(alpha);
        recursive(tmpY,tmpX);
        alphaCheck[board[tmpY][tmpX]-'A']=false;
        // check.erase(alpha);
        alpha.resize(alpha.size()-1);  
    }
}

void cal(){
    fill_n(alphaCheck,26,false);
    alphaCheck[board[1][1]-'A']=true;
    alpha+=board[1][1];
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