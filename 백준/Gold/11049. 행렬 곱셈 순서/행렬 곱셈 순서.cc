#include <iostream>
#include <vector>

using namespace std;

int n;
vector<pair<int, int>> matrix;

long long mul[1000][1000] = {0};

void input() {
    cin >> n;
    matrix.push_back({0, 0});
    for (int i = 0; i < n; i++) {
        int n1, n2;
        cin >> n1 >> n2;

        matrix.push_back({n1, n2});
    }
}

void output() {
    // for (int i = 1; i <= n; i++) {
    //     for (int j = 1; j <= n; j++) {
    //         cout << mul[i][j] << ' ';
    //     }
    //     cout << '\n';
    // }
    // cout << '\n';

    // if (matrix.size() == 2) {
    //     cout << 0 << '\n';
    // } else {
    //     cout << mul[1][n] << '\n';
    // }
    cout << mul[1][n] << '\n';
}

void cal() {
    for (int i = 1; i < n; i++) {
        for (int j = 1; j <= n - i; j++) {
            mul[j][j+i]=10e13;
            for(int k=0;k<i;k++){
                mul[j][j+i]=min(mul[j][j+i],mul[j][j+k]+mul[j+k+1][j+i]+(long long)matrix[j].first*matrix[j+k].second*matrix[j+i].second);
            }
        }
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