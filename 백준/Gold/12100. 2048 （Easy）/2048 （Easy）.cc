#include<iostream>
#include<algorithm>
#include<vector>
#include<queue>

using namespace std;

vector<vector<int>> originalBoard;
int result=0;
int n;

void printBoard(vector<vector<int>> board){
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            cout<<board[i][j]<<' ';
        }
        cout<<'\n';
    }
    cout<<'\n';
}

void input(){
    cin>>n;
    originalBoard.assign(n+1, vector<int>(n+1));
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            cin>>originalBoard[i][j];
        }
    }
}

void output(){
    cout<<result<<'\n';
}

deque<int> moveNum(queue<int>& q){
    int prevNum = -1;
    deque<int> afterNum;
    while(!q.empty()){
        int front = q.front();
        q.pop();
        if(prevNum==-1){
            prevNum=front;
        }else if(front!=prevNum){
            afterNum.push_back(prevNum);
            prevNum=front;   
        }else{
            afterNum.push_back(prevNum*2);
            prevNum=-1;
        }
        if(q.empty()){
            if(prevNum!=-1)
                afterNum.push_back(prevNum);
        }
    }
    return afterNum;
}


void left(vector<vector<int>>& board){
    for(int i=0;i<n;i++){
        queue<int> q;
        for(int j=0;j<n;j++){
            if(board[i][j]!=0){
                q.push(board[i][j]);
                board[i][j]=0;
            }
        }
        deque<int> afterNum = moveNum(q);

        int afterNumSize = afterNum.size();
        for(int j=0;j<afterNumSize;j++){
            board[i][j]=afterNum.front();
            afterNum.pop_front();
        }
    }
}

void right(vector<vector<int>>& board){
    for(int i=0;i<n;i++){
        queue<int> q;
        for(int j=n-1;j>=0;j--){
            if(board[i][j]!=0){
                q.push(board[i][j]);
                board[i][j]=0;
            }
        }
        deque<int> afterNum = moveNum(q);

        int afterNumSize = afterNum.size();
        for(int j=0;j<afterNumSize;j++){
            board[i][n-j-1]=afterNum.front();
            afterNum.pop_front();
        }
    }
}

void up(vector<vector<int>>& board){
    for(int i=0;i<n;i++){
        queue<int> q;
        for(int j=0;j<n;j++){
            if(board[j][i]!=0){
                q.push(board[j][i]);
                board[j][i]=0;
            }
        }
        deque<int> afterNum = moveNum(q);

        int afterNumSize = afterNum.size();
        for(int j=0;j<afterNumSize;j++){
            board[j][i]=afterNum.front();
            afterNum.pop_front();
        }
    }
}

void down(vector<vector<int>>& board){
    for(int i=0;i<n;i++){
        queue<int> q;
        for(int j=n-1;j>=0;j--){
            if(board[j][i]!=0){
                q.push(board[j][i]);
                board[j][i]=0;
            }
        }
        deque<int> afterNum = moveNum(q);

        int afterNumSize = afterNum.size();
        for(int j=0;j<afterNumSize;j++){
            board[n-j-1][i]=afterNum.front();
            afterNum.pop_front();
        }
    }
}

void calMaxValue(vector<vector<int>> &board){
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            result=max(result,board[i][j]);
        }
    }
}

vector<vector<int>> generateBoard(vector<vector<int>> board){
    vector<vector<int>> copyBoard(n+1, vector<int>(n+1));
    copy(board.begin(), board.end(), copyBoard.begin());
    return copyBoard;
}

void recursive(vector<vector<int>> board, int count){
    if(count==5){
        // printBoard(board);
        calMaxValue(board);
    }else{
        auto copyBoard = generateBoard(board);
        up(copyBoard);
        recursive(copyBoard, count+1);
        
        copyBoard = generateBoard(board);
        down(copyBoard);
        recursive(copyBoard, count+1);

        copyBoard = generateBoard(board);
        left(copyBoard);
        recursive(copyBoard, count+1);

        copyBoard = generateBoard(board);
        right(copyBoard);
        recursive(copyBoard, count+1);
    }
}


void cal(){
    recursive(originalBoard, 0);
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