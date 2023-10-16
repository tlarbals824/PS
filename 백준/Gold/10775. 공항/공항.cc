#include <iostream>
#include <set>
using namespace std;

int g, p;
set<int> check;
int ary[100010];

void input() {
    cin >> g >> p;
    for (int i = 1; i <= g; i++) {
        check.insert(-i);
    }
    for (int i = 0; i < p; i++) {
        cin >> ary[i];
    }
}

void start() {
    input();
    int i;
    for (i = 0; i < p; i++) {
        auto iter = check.lower_bound(-ary[i]);
        if (iter == check.end())
            break;
        else {
            check.erase(iter);
        }
    }
    cout << i << '\n';
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    start();
}