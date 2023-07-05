#include<iostream>
#include<algorithm>

using namespace std;

int n,m; // 세로 n, 가로 m
int b; // 인벤토리 블록 수

int totalHeight = 0;
int needBlockCnt = 0;

int field[600][600];

int resultTime = 10e8;
int resultHeight = 0;

int plusCnt = 0;
int minusCnt = 0;

void input(){
    cin>>n>>m>>b;
    for(int i = 0;i<n;i++){
        for(int j =0; j< m ;j++){
            cin >> field[i][j];
        }
    }
}

void output(){
    cout<< resultTime <<' '<<resultHeight <<'\n';
}

bool checkNeedBlockCnt(int height){
    int needBlockCnt = 0;
    plusCnt=minusCnt=0;
    for(int i = 0 ; i < n ; i++){
        for(int j = 0; j< m ; j++){
            int tmp = field[i][j]-height;
            needBlockCnt += tmp;
            if(tmp > 0){
                plusCnt+=tmp;
            }else{
                minusCnt-=tmp;
            }
        }
    }
    return (b + needBlockCnt) >= 0;
}

void timeCheck(int height){
    int tmpTime = 2*plusCnt+minusCnt;
    if(tmpTime <= resultTime){
        resultTime = tmpTime;
        resultHeight = height;
    }
}

void bruteForce(){
    for(int i = 0; i <= 256 ; i++){
        if(checkNeedBlockCnt(i)){
            timeCheck(i);
        }else{
            break;
        }
    }
}

void start(){
    input();
    bruteForce();
    output();
}

int main(){
    start();
}