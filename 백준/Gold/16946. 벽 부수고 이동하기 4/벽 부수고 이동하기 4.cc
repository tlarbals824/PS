#include <iostream>
#include <queue>
#include <set>
#include <vector>
#include <map>
using namespace std;

int n, m;
int ary[1001][1001];
int check[1001][1001] = {0};
map<int,int> cntMap;

int dirY[4] = {-1, 0, 1, 0};
int dirX[4] = {0, -1, 0, 1};

vector<pair<int, int>> emptySpace;
vector<pair<int,int>> wall;


void input() {
    cin >> n >> m;
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            char num;
            cin >> num;
            ary[i][j] = num - '0';
            if (ary[i][j] == 0) {
                emptySpace.push_back({i, j});
            }else{
                wall.push_back({i,j});
            }
        }
    }
}

void output() {
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            cout << ary[i][j];
        }
        cout << '\n';
    }
}

void cal() {
    queue<pair<int, int>> q;
    for (int i = 0; i < emptySpace.size(); i++) {
        int cnt=0;
        if(check[emptySpace[i].first][emptySpace[i].second]!=0) continue;
        check[emptySpace[i].first][emptySpace[i].second]=i+1;
        q.push({emptySpace[i].first, emptySpace[i].second});
        while (!q.empty()) {
            int y = q.front().first;
            int x = q.front().second;
            q.pop();
            cnt++;

            for(int j=0;j<4;j++){
                int tmpY = y+dirY[j];
                int tmpX = x+dirX[j];
                if(tmpY<1||tmpY>n) continue;
                if(tmpX<1||tmpX>m) continue;
                if(check[tmpY][tmpX]!=0) continue;
                if(ary[tmpY][tmpX]!=0) continue;
                check[tmpY][tmpX]=i+1;
                q.push({tmpY,tmpX});
            }
        }
        cntMap.insert({i+1,cnt%10});
    }
}


void calWall(){
    for(int i=0;i<wall.size();i++){
        int y = wall[i].first;
        int x = wall[i].second;

        set<int> via;
        for(int j=0;j<4;j++){
            int tmpY=y+dirY[j];
            int tmpX=x+dirX[j];

            if(tmpY<1||tmpY>n) continue;
            if(tmpX<1||tmpX>m) continue;
            if(ary[tmpY][tmpX]!=0) continue;
            if(check[tmpY][tmpX]==0) continue;
            if(via.find(check[tmpY][tmpX])!=via.end()) continue;

            via.insert(check[tmpY][tmpX]);
            ary[y][x]=(ary[y][x]+cntMap.at(check[tmpY][tmpX]))%10;
        }
    }
}

void start() {
    input();
    cal();
    calWall();
    output();
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    start();
}