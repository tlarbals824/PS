#include <cmath>
#include <iostream>
#include<vector>
using namespace std;

int n;
vector<pair<double, double>> ary;
double result = 0;
void input() {
    cin >> n;
    for (int i = 0; i < n; i++) {
        double x, y;
        cin >> x >> y;
        ary.push_back({x, y});
    }
}

void output() {
    cout.precision(1);
    cout<<fixed<<result<<'\n';
}

void cal() {
    for (int i = 0; i < n; i++) {
        result += (ary[i % n].first * ary[(i + 1) % n].second - ary[i % n].second * ary[(i + 1) % n].first);
    }
    result = abs(result / 2)*10;
    result = round(result)/10;
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