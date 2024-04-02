#include <iostream>
#include <queue>
using namespace std;

struct Current {
    int y;
    int x;
    int num;

    bool operator<(Current other) const {
        return num > other.num;
    }
};

int n, m;

int dp[510][510] = {0};
int check[510][510] = {0};

int dirY[4] = {0, -1, 0, 1};
int dirX[4] = {-1, 0, 1, 0};

int result = 0;

void input() {
    cin >> m >> n;
    for (int i = 1; i <= m; i++) {
        for (int j = 1; j <= n; j++) {
            cin >> dp[i][j];
        }
    }
}

void output() {
    cout << (result ==-1 ? 0 : result) << '\n';
}

int recursive(int y,int x){
    if(y==m&&x==n){
        return 1;
    }else{
        int forwardCount=0;
        for(int i=0;i<4;i++){
            int nextY = y+dirY[i];
            int nextX = x+dirX[i];

            if(nextY<1||nextY>m||nextX<1||nextX>n) continue;

            if(dp[nextY][nextX] >= dp[y][x]) continue;
            if(check[nextY][nextX]==-1) continue;
            int forward;
            if(check[nextY][nextX]!=0){
                forward = check[nextY][nextX];
            }else{
                forward = recursive(nextY,nextX);
                check[nextY][nextX]=forward;
            }
            if(forward!=-1) forwardCount+=forward;
        }

        if(forwardCount==0){
            return -1;
        }
        return forwardCount;
    }
}

void cal() {
    result=recursive(1,1);
}

void start() {
    input();
    cal();
    output();
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    start();
}