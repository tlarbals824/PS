// 메모리 : 2024KB, 시간 : 24ms
#include<iostream>
#include<algorithm>

using namespace std;

int boardSize;
char board[50][50];
int maxCandy = 0;
char beforeCandy;
int sequenceCandy;

void input(){
    cin>>boardSize;
    for(int i = 0 ; i< boardSize ; i++){
        for(int j = 0 ; j< boardSize ; j++){
            cin>>board[i][j];
        }
    }
}

void output(){
    cout<<maxCandy<<'\n';
}

void checkSequenceCandy(int i, int j, char* beforeCandy, int* sequenceCandy){
    if(*beforeCandy != board[i][j]){
        maxCandy = max(maxCandy, *sequenceCandy);
        *beforeCandy = board[i][j];
        *sequenceCandy = 1;
    }else{
        (*sequenceCandy)++;
        maxCandy = max(maxCandy, *sequenceCandy);
    }

}

void checkLongestCandy(int y,int x){
    for(int i =y; i<= min(y+1, boardSize-1) ;i++){
        beforeCandy = 0;
        sequenceCandy = 0;
        for(int j = 0;j<boardSize ; j++){
            checkSequenceCandy(i, j, &beforeCandy, &sequenceCandy);
        }
    }
    for(int i =x;i <= min(x+1, boardSize-1); i++){
        beforeCandy = 0;
        sequenceCandy = 0;
        for(int j = 0;j<boardSize ; j++){
            checkSequenceCandy(j, i, &beforeCandy, &sequenceCandy);
        }
    }
}

void swapBoardCandy(int i, int j){
    if(i != boardSize-1){
        swap(board[i][j],board[i+1][j]);
        checkLongestCandy(i,j);
        swap(board[i][j],board[i+1][j]);
    }
    if(j != boardSize-1){
        swap(board[i][j],board[i][j+1]);
        checkLongestCandy(i,j);
        swap(board[i][j],board[i][j+1]);
    }
}

void bruteForce(){
    for(int i = 0 ;i< boardSize ; i++){
        for(int j = 0; j< boardSize; j++){
            swapBoardCandy(i,j);
        }
    }
    
}

void start(){
    input();
    bruteForce();
    output();
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    start();
}