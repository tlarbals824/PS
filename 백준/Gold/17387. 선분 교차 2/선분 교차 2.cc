#include <algorithm>
#include <cmath>
#include <iostream>
using namespace std;

int ccw(pair<long long, long long> p1, pair<long long, long long> p2, pair<long long, long long> p3) {
    long long ret = (p2.first - p1.first) * (p3.second - p1.second) - (p2.second - p1.second) * (p3.first - p1.first);

    if (ret < 0)
        return -1;
    else if (ret > 0)
        return 1;
    return 0;
}

bool isLine(long long a, long long b, long long c, long long d) {
    if (a > b) {
        swap(a, b);
    }
    if (c > d) {
        swap(c, d);
    }

    return (a <= d && b >= c) || (c <= b && d >= a);
}

void start() {
    long long x1, y1, x2, y2;
    cin >> x1 >> y1 >> x2 >> y2;

    long long x3, y3, x4, y4;
    cin >> x3 >> y3 >> x4 >> y4;

    int z1 = ccw({x1, y1}, {x2, y2}, {x3, y3});
    int z2 = ccw({x1, y1}, {x2, y2}, {x4, y4});
    int z3 = ccw({x3, y3}, {x4, y4}, {x1, y1});
    int z4 = ccw({x3, y3}, {x4, y4}, {x2, y2});

    if (z1 * z2 == 0 && z3 * z4 == 0) {
        cout << (isLine(x1, x2, x3, x4) && isLine(y1, y2, y3, y4)) << '\n';
    } else {
        cout << (z1 * z2 <= 0 && z3 * z4 <= 0) << '\n';
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    start();
}