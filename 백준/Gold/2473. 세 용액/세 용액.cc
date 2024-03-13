#include <algorithm>
#include <iostream>
#include <set>
#include <vector>
using namespace std;

int n;
vector<int> solution;

set<int> result;

long long minimum = 5000000000;

void input() {
    cin >> n;
    for (int i = 0; i < n; i++) {
        int num;
        cin >> num;
        solution.push_back(num);
    }
    sort(solution.begin(), solution.end());
}

void output() {
    for (auto iter = result.begin(); iter != result.end(); iter++) {
        cout << *iter << ' ';
    }
    cout << '\n';
}

void comp(int i, int j, int target, long long sum) {
    if (target < 0 || target >= n)
        return;
    if (i != target && j != target) {
        // cout << i << ' ' << j << ' ' << target << '\n';
        if (abs((long long)solution[target] + sum) < minimum) {
            minimum = abs(solution[target] + sum);
            result.clear();
            result.insert(solution[i]);
            result.insert(solution[j]);
            result.insert(solution[target]);
        }
    }
}

void cal() {
    for (int i = 0; i < n - 1; i++) {
        for (int j = i + 1; j < n; j++) {
            long long sum = (long long)solution[i] + (long long)solution[j];

            int targetIdx = lower_bound(solution.begin(), solution.end(), -sum) - solution.begin();
            comp(i, j, targetIdx, sum);
            comp(i, j, targetIdx - 1, sum);
            comp(i, j, targetIdx + 1, sum);
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