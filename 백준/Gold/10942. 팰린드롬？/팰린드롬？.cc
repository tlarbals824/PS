#include <iostream>
#include <map>

using namespace std;

int n, m;
int nums[2010] = {0};
int ableRange[2010] = {0};
int disableRange[2010] = {0};

map<pair<int, int>, int> ableRangeMap;
map<pair<int, int>, int> disableRangeMap;

int calEven(int start, int end) {
    int mid = (start + end) / 2;

    if (ableRange[mid] != 0) {
        if (ableRange[mid] >= (end - mid)) {
            return 1;
        } else {
            if (disableRange[mid] != 0) {
                if (disableRange[mid] <= (end - mid)) {
                    return 0;
                }
            }
        }
    }

    for (int i = start; i < mid; i++) {
        if (nums[i] != nums[end + start - i]) {
            if (disableRange[mid] == 0) {
                disableRange[mid] = (end - mid);
            } else {
                disableRange[mid] = min(disableRange[mid], (end - mid));
            }
            return 0;
        }
    }
    ableRange[mid] = max(ableRange[mid], (end - mid));
    return 1;
}

int calOdd(int start, int end) {
    int mid1 = (start + end) / 2;
    int mid2 = (start + end) / 2 + 1;

    auto able = ableRangeMap.find({mid1, mid2});
    auto disable = disableRangeMap.find({mid1, mid2});

    if (able != ableRangeMap.end()) {
        if (able->second >= (end - mid2)) {
            return 1;
        } else {
            if (disable != disableRangeMap.end()) {
                if (disable->second <= (end - mid2)) {
                    return 0;
                }
            }
        }
    }

    for (int i = start; i <= mid1; i++) {
        if (nums[i] != nums[end + start - i]) {
            return 0;
            if (disable != disableRangeMap.end()) {
                disable->second=min(disable->second, (end-mid2));
            } else {
                disableRangeMap.insert({{mid1, mid2}, (end-mid2)});
            }
        }
    }
    if(able != ableRangeMap.end()){
        able->second = max(able->second, (end - mid2));
    }else{
        ableRangeMap.insert({{mid1,mid2},(end-mid2)});
    }

    return 1;
}

int isPalindrome(int start, int end) {
    return (start + end) % 2 == 0 ? calEven(start, end) : calOdd(start, end);
}

void input() {
    cin >> n;
    for (int i = 1; i <= n; i++) {
        cin >> nums[i];
    }
    cin >> m;
    for (int i = 0; i < m; i++) {
        int start, end;
        cin >> start >> end;
        cout << isPalindrome(start, end) << '\n';
    }
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    input();
}