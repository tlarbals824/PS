#include<iostream>
#include<set>
#include<algorithm>

using namespace std;

vector<int> nums;
vector<int> idx;
vector<int> originalNums;
long n;

void input(){
    cin>>n;
    for(int i=0;i<n;i++){
        long num;
        cin>>num;
        originalNums.push_back(num);
        // cout<<num<<'\n';
        if(nums.size()==0){
            nums.push_back(num);
            idx.push_back(0);
            continue;
        }
        auto iter = --nums.end();

        if(*iter < num){
            nums.push_back(num);
            idx.push_back(nums.size()-1);
        }else{
            int findIdx = lower_bound(nums.begin(), nums.end(), num)-nums.begin();
            nums[findIdx]=num;
            idx.push_back(findIdx);
            // cout<<num<<' '<<findIdx<<'\n';
        }
    }
}

void output(){
    cout<<nums.size()<<'\n';
    int count=nums.size()-1;
    vector<int> tmp;
    for(int i=n-1;i>=0;i--){
        if(count<0) break;
        if(idx[i]==count){
            count--;
            tmp.push_back(originalNums[i]);
        }
    }
    for(int i=nums.size()-1;i>=0;i--){
        cout<<tmp[i]<<' ';
    }
    cout<<'\n';
}

void start(){
    input();
    output();
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    start();
}