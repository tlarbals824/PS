#include <cmath>
#include <iostream>
#include <vector>

using namespace std;

int r, c, m;
vector<vector<int>> shark;
int check[100100] = {0};
vector<vector<int>> ocean;

int dirY[5] = {0, -1, 1, 0, 0};
int dirX[5] = {0, 0, 0, 1, -1};

int result = 0;

void printOcean() {
    for (int i = 1; i <= r; i++) {
        for (int j = 1; j <= c; j++) {
            cout << ocean[i][j] << ' ';
        }
        cout << '\n';
    }
    cout << '\n';
}

void printShark(){
    for(int i=1;i<=m;i++){
        cout<<i<<": "<<(char)('A'+i-1)<<": ";
        cout<<shark[i][0]<<shark[i][1]<<shark[i][2]<<shark[i][3]<<shark[i][4];
        cout<<'\n';
    }
}

void input() {
    cin >> r >> c >> m;
    shark.assign(m + 1, vector<int>(5));
    ocean.assign(r + 10, vector<int>(c + 10));

    for (int i = 1; i <= m; i++) {
        cin >> shark[i][0] >> shark[i][1] >> shark[i][2] >> shark[i][3] >> shark[i][4];
        ocean[shark[i][0]][shark[i][1]] = i;
    }

}

void output() {
    cout << result << '\n';
}

void moveSingle(int k, int &idx, int size) {
    if (k % 2 == 0) {
        idx = idx - k*(size-1);
    } else {
        idx = -idx + (k+1)*(size-1) + 2;
    }
}

void move() {
    vector<vector<int>> tmpOcean(r + 10, vector<int>(c + 10));

    for (int i = 1; i <= m; i++) {
        if (check[i] == 1)
            continue;

        int dir = shark[i][3];
        int moveY = shark[i][2] * dirY[dir] + shark[i][0];
        int moveX = shark[i][2] * dirX[dir] + shark[i][1];

        int kY = floor((double)(moveY - 1) / (r - 1));
        int kX = floor((double)(moveX - 1) / (c - 1));

        int newDir = ((kY % 2 != 0 && moveY!=shark[i][0] )|| (kX%2 !=0 && moveX!=shark[i][1])) ? (dir / 3 == 0 ? (dir == 1 ? 2 : 1) : (dir == 3 ? 4 : 3)) : dir;

        moveSingle(kY, moveY, r);
        moveSingle(kX, moveX, c);


        

        if (tmpOcean[moveY][moveX] != 0) {
            int tmpSharkIdx = tmpOcean[moveY][moveX];
            if (shark[i][4] < shark[tmpSharkIdx][4]) {
                check[i] = 1;
            } else {
                check[tmpSharkIdx] = 1;
                tmpOcean[moveY][moveX] = i;
            }
        } else {
            tmpOcean[moveY][moveX] = i;
        }

        shark[i][0] = moveY;
        shark[i][1] = moveX;
        shark[i][3] = newDir;

    }
    copy(tmpOcean.begin(), tmpOcean.end(), ocean.begin());
    // printShark();
    // printOcean();
}

void calFising(int col) {
    for (int i = 1; i <= r; i++) {
        if (ocean[i][col] != 0) {
            int num = ocean[i][col];
            ocean[i][col] = 0;
            result += (shark[num][4]);
            check[num] = 1;
            break;
        }
    }
    move();
}

void cal() {
    for (int i = 1; i <= c; i++) {
        calFising(i);
    }
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