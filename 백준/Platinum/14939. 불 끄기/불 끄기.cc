#include <algorithm>
#include <iostream>
#include <queue>
#include <set>
#include <vector>
using namespace std;

vector<short> switchs(11);

short dirY[4] = {0, 0, -1, 1};
short dirX[4] = {-1, 1, 0, 0};

short result = 2000;

struct Line {
    short line;
    int count;

    bool operator<(Line other) const {
        return count > other.count;
    }
};

void switchBit(short &num, short idx) {
    short tmp = 1 << idx;
    if (num & tmp) {
        num = num ^ tmp;
    } else {
        num = num | tmp;
    }
}

bool isMarkBit(short num, short idx) {
    short tmp = 1 << idx;
    return num & tmp;
}

void printSwitch() {
    for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 10; j++) {
            cout << (isMarkBit(switchs[i], j) ? 'O' : '#');
        }
        cout << '\n';
    }
    cout << '\n';
}

void input() {
    for (int i = 0; i < 10; i++) {
        short line = 0;
        for (int j = 0; j < 10; j++) {
            char target;
            cin >> target;
            if (target == 'O') {
                switchBit(line, j);
            }
        }
        switchs[i] = line;
    }
}

void output() {
    cout << (result == 2000 ? -1 : result) << '\n';
}

void process(vector<short> tmp, short count) {
    for (int i = 1; i < 10; i++) {
        for (int j = 0; j < 10; j++) {
            if (isMarkBit(tmp[i - 1], j)) {
                count++;
                switchBit(tmp[i], j);
                for (int k = 0; k < 4; k++) {
                    short nextY = i + dirX[k];
                    short nextX = j + dirY[k];

                    if (nextX < 0 || nextX >= 10 || nextY < 0 || nextY >= 10)
                        continue;

                    switchBit(tmp[nextY], nextX);
                }
            }
        }
    }

    for (int i = 0; i < 10; i++) {
        if (isMarkBit(tmp[9], i)) {
            return;
        }
    }

    result = min(result, count);
}

void recursive(vector<short> switchs, short count, int idx) {
    if (idx == 10) {
        process(switchs, count);
    } else {
        recursive(switchs, count, idx + 1);

        count++;
        switchBit(switchs[0], idx);
        for (int k = 0; k < 4; k++) {
            short nextY = dirX[k];
            short nextX = idx + dirY[k];

            if (nextX < 0 || nextX >= 10 || nextY < 0 || nextY >= 10)
                continue;

            switchBit(switchs[nextY], nextX);
        }
        recursive(switchs, count, idx + 1);
    }
}

void cal() {
    recursive(switchs, 0, 0);
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