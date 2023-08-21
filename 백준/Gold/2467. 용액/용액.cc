#include<iostream>
#include<vector>
using namespace std;

int n;
int ary[200000];
vector<int> plusItem;
vector<int> minusItem;
vector<int> result;

void input(){
    cin>>n;
    for(int i=0;i<n;i++){
        cin>>ary[i];
    }
}

void output(){
    for(int i=0;i<result.size();i++){
        cout<<result[i]<<' ';
    }
    cout<<'\n';
}

void findNum(){
    int left = 0;
    int right = n-1;
    int tmp = abs(ary[left]+ary[right]);
    result.push_back(ary[left]);
    result.push_back(ary[right]);
    while(left<right){
        int sum = ary[left]+ary[right];
        if(abs(sum)<tmp){
            tmp = abs(sum);
            result.clear();
            result.push_back(ary[left]);
            result.push_back(ary[right]);
        }
        if(sum<0){
            left++;
        }
        if(sum>0){
            right--;
        }
        if(sum == 0){
            break;
        }
    }
}

void cal(){
    findNum();
}

void start(){
    input();
    cal();
    output();
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    start();
}