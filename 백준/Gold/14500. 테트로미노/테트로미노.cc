#include <algorithm>
#include <iostream>

using namespace std;

int n, m;
int paper[1000][1000][2] = {0};
int result = 0;

int dirX[3] = {0,1, 0};
int dirY[3] = {-1,0, 1};

void input() {
  cin >> n >> m;
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      cin >> paper[i][j][0];
    }
  }
}

void output() { cout << result << '\n'; }

void dfs(int cnt, int y, int x, int previousY, int previousX, int sum) { 
  if (cnt == 3) {
    result = max(result, sum);
  } else {
    for (int i = 0; i < 3; i++) {
      int tmpY = y + dirY[i];
      int tmpX = x + dirX[i];
      if (tmpY < 0 || tmpY >= n) continue;
      if (tmpX < 0 || tmpX >= m) continue;
      if (paper[tmpY][tmpX][1]) continue;
      paper[tmpY][tmpX][1] = 1;
      dfs(cnt+1, tmpY, tmpX, y, x, sum + paper[tmpY][tmpX][0]);
      paper[tmpY][tmpX][1] = 0;
    }
    for(int i=0;i<3;i++){
      int tmpY = previousY + dirY[i];
      int tmpX = previousX + dirX[i];
      if (tmpY < 0 || tmpY >= n) continue;
      if (tmpX < 0 || tmpX >= m) continue;
      if (paper[tmpY][tmpX][1]) continue;
      paper[tmpY][tmpX][1] = 1;
      dfs(cnt+1, tmpY, tmpX, previousY, previousX, sum + paper[tmpY][tmpX][0]);
      paper[tmpY][tmpX][1] = 0;
    }
  }
}

void bruteForce() {
  for (int i = 0; i < n; i++) {
    for (int j = 0; j < m; j++) {
      paper[i][j][1] = 1;
      dfs(0, i, j, i, j, paper[i][j][0]);
      paper[i][j][1] = 0;
    }
  }
}

void start() {
  input();
  bruteForce();
  output();
}

int main() {
  ios_base::sync_with_stdio(0);
  cin.tie(0);

  start();
}