#include <iostream>
#include <map>

using namespace std;

long long a, b;

long long result = 0;

map<long long, long long> twoCounter;

void input() {
    cin >> a >> b;
}

long long moveBit(int idx) {
    return (long long)1 << idx;
}

void setUp() {
    int idx = 2;
    twoCounter.insert({0,0});
    twoCounter.insert({1,1});
    twoCounter.insert({2,2});
    while (true) {
        if (idx == 50)
            break;

        twoCounter.insert({moveBit(idx)-1, moveBit(idx)-moveBit(idx-1) + 2 * twoCounter.find(moveBit(idx-1)-1)->second});
        idx++;
    }
}

int calculateDivNum(long long num) {
    int count = 1;
    while ((num / moveBit(count)) != 0) {
        count++;
    }
    return count - 1;
}

long long divide(long long num) {
    if(num <= 1) return num;
    int count = calculateDivNum(num);
    auto iter = twoCounter.find(num);
    if(iter!=twoCounter.end()){
        return iter->second;
    }else{
        long long tmp = num - moveBit(count) + 1 + divide(num - moveBit(count)) + divide(moveBit(count) - 1);
        twoCounter.insert({num, tmp});
        return tmp;
    }
}

void cal() {
    result=divide(b)-divide(a-1);
}

void output() {
    cout << result << '\n';
}

void start() {
    input();
    setUp();
    cal();
    output();
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    start();
}