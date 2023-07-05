#include<iostream>
#include<algorithm>

using namespace std;

int n,m; // 세로 n, 가로 m
int b; // 인벤토리 블록 수

int totalHeight = 0;
int avgHeight = 0;
int needBlockCnt = 0;

int field[1000][1000];

int resultTime = 10e8;
int resultHeight = 0;

void input(){
    cin>>n>>m>>b;
    for(int i = 0;i<n;i++){
        for(int j =0; j< m ;j++){
            cin >> field[i][j];
            totalHeight += field[i][j];
        }
    }
    avgHeight = totalHeight / (n*m);
}

void output(){
    cout<< resultTime <<' '<<resultHeight <<'\n';
}

bool checkNeedBlockCnt(int height){
    int needBlockCnt = 0;
    for(int i = 0 ; i < n ; i++){
        for(int j = 0; j< m ; j++){
            needBlockCnt += (field[i][j]-height);
        }
    }
    return (b + needBlockCnt) >= 0;
}

void timeCheck(int height){
    int tmpTime = 0;
    for(int i = 0; i < n ; i++){
        for(int j = 0 ; j < m ; j++){
            int fieldHeight = field[i][j];
            if(fieldHeight > height){
                tmpTime+=(2*(fieldHeight-height));
            }else if(fieldHeight < height){
                tmpTime+=(height-fieldHeight);
            }
        }
    }
    if(tmpTime < resultTime){
        resultTime = tmpTime;
        resultHeight = height;
    }
}

void bruteForce(){
    for(int i = 256; i >= 0 ; i--){
        if(checkNeedBlockCnt(i)){
            timeCheck(i);
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