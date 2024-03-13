#include <iostream>
#include <map>
#include <set>
#include <vector>

using namespace std;

int t;
int aCount, bCount;
vector<int> numsA(1010);
vector<int> numsB(1010);

long long result = 0;
int aSum=0;
int bSum=0;

map<int, long long> aSubSetValue;
map<int, long long> bSubSetValue;

void input() {
    cin >> t;
    cin >> aCount;
    for (int i = 1; i <= aCount; i++) {
        cin >> numsA[i];
        aSum+=numsA[i];
    }

    cin >> bCount;
    for (int i = 1; i <= bCount; i++) {
        cin >> numsB[i];
        bSum+=numsB[i];
    }

}

void output() {
    cout << result << '\n';
}

void recursive(int start, int end, int sum, vector<int> &nums, map<int, long long> &subSetValue, bool back) {
    if(start > end) return;

    

    auto value = subSetValue.find(sum);
    if (value != subSetValue.end()) {
        value->second++;
    } else {
        subSetValue.insert({sum, 1});
    }

    if(!back) recursive(start+1, end, sum-nums[start], nums,subSetValue, false);
    recursive(start, end-1, sum-nums[end], nums,subSetValue, true);
}

void cal() {
    recursive(1, aCount, aSum, numsA, aSubSetValue,false);
    recursive(1, bCount, bSum, numsB, bSubSetValue,false);

    for (auto iter = aSubSetValue.begin(); iter != aSubSetValue.end(); iter++) {
        int key = iter->first;
        long long value = iter->second;

        auto target = bSubSetValue.lower_bound(t - key);
        if (target != bSubSetValue.end() && target->first == t - key) {
            result += (value * target->second);
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