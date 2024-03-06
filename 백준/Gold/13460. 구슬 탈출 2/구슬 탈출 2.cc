#include <algorithm>
#include <iostream>
#include <queue>
#include <vector>

using namespace std;

struct Board {
    pair<int, int> red;
    pair<int, int> blue;
    int count;
};

struct compare {
    bool operator()(Board &a, Board &b) {
        return a.count > b.count;
    }
};

int n, m;
vector<vector<char>> board;

pair<int, int> redStart, blueStart;

int dirY[4] = {-1, 0, 1, 0}; // 위, 왼, 아, 오
int dirX[4] = {0, -1, 0, 1};

int minCount = 10e8;

void printBoard() {
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cout << board[i][j] << ' ';
        }
        cout << '\n';
    }
    cout << '\n';
}

void input() {
    cin >> n >> m;
    board.assign(n, vector<char>(m));
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            cin >> board[i][j];
            if (board[i][j] == 'R') {
                redStart = {i, j};
                board[i][j] = '.';
            }
            if (board[i][j] == 'B') {
                blueStart = {i, j};
                board[i][j] = '.';
            }
        }
    }
}

void output() {
    cout << (minCount == 10e8 ? -1 : minCount) << '\n';
}

pair<int, int> move(pair<int, int> start, int dir, bool &isGoal, pair<int,int> another) {
    int y = start.first, x = start.second;
    while (true) {
        y += dirY[dir];
        x += dirX[dir];

        char currentChar = board[y][x];
        if (currentChar == '#' || (y==another.first&&x==another.second)) {
            isGoal = false;
            return {y - dirY[dir], x - dirX[dir]};
        }
        if (currentChar == 'O') {
            isGoal = true;
            return {0, 0};
        }
    }
}

bool compareSeq(pair<int, int> ball1, pair<int, int> ball2, int dir) {
    switch (dir) {
    case 0:
        return ball1.first < ball2.first;
    case 1:
        return ball1.second < ball2.second;
    case 2:
        return ball1.first > ball2.first;
    default:
        return ball1.second > ball2.second;
    }
}

void bfs() {
    priority_queue<Board, vector<Board>, compare> pq;
    pq.push(Board{redStart, blueStart, 0});
    while (!pq.empty()) {
        auto topBoard = pq.top();
        pq.pop();
        int count = topBoard.count + 1;

        for (int i = 0; i < 4; i++) {
            bool red = false;
            bool blue = false;
            pair<int, int> redResult, blueResult;

            if (compareSeq(topBoard.red, topBoard.blue, i)) { // 빨강이 앞섬
                redResult = move(topBoard.red, i, red, {0,0});
                blueResult = move(topBoard.blue, i, blue, redResult);
            } else {
                blueResult = move(topBoard.blue, i, blue, {0,0});
                redResult = move(topBoard.red, i, red, blueResult);
            }

            // cout<<"dir: "<<i<<'\n';
            // cout<<redResult.first<<' '<<redResult.second<<'\n';
            // cout<<blueResult.first<<' '<<blueResult.second<<'\n';

            if (red && !blue) {
                minCount = count;
                return;
            }
            if (blue) {
                continue;
            }
            if (count < 10)
                pq.push(Board{redResult, blueResult, count});
        }
    }
}

void start() {
    input();
    bfs();
    output();
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    start();
}