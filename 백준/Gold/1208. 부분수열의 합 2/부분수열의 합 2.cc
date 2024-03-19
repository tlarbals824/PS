#include <algorithm>
#include <iostream>
#include <set>
#include<map>
#include <vector>

using namespace std;

int n, s;

vector<int> nums;

long long result = 0;

map<int,int> subSetA, subSetB;

void input() { // logn
    cin >> n >> s;
    nums = vector<int>(n + 1);
    for (int i = 0; i < n; i++) {
        cin >> nums[i];
    }
}

void output() {
    cout << result << '\n';
}

void recursive(int end, int currentIdx, int sum, map<int,int> &subSet) {
    if (currentIdx == end) {
        subSet[sum]++;
    } else {
        recursive(end, currentIdx + 1, sum + nums[currentIdx], subSet);
        recursive(end, currentIdx + 1, sum, subSet);
    }
}

void cal() {
    int mid = n / 2;
    recursive(mid, 0, 0, subSetA);
    recursive(n, mid, 0, subSetB);

    int startA = 0, startB = (n-mid-1);

    for(auto iter = subSetA.begin(); iter!=subSetA.end(); iter++){
        auto tmp = subSetB.find(s-iter->first);
        if(tmp!=subSetB.end()){
            result+=((long long)tmp->second*(long long)iter->second);
        }
    }

    if(s==0) result--;
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