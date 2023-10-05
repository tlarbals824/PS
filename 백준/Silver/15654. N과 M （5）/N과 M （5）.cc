#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;

int n,m;

vector<int> ary;
vector<vector<int>> tmp;

void input(){
    cin>>n>>m;
    for(int i=0;i<n;i++){
        int num;
        cin>>num;
        ary.push_back(num);
    }
    sort(ary.begin(),ary.end());
}

bool check(vector<int> tmp, int idx){
    for(int i=0;i<tmp.size();i++){
        if(tmp[i]==idx) return false;
    }
    return true;
}

void recursive(vector<int> tmp, int cnt, int idx){
    if(cnt>m) return;
    if(cnt==m) {
        for(int i=0;i<tmp.size();i++){
            cout<<tmp[i]<<' ';
        }
        cout<<'\n';
    }else if(idx==n){
        return;
    }else{
        if(check(tmp, ary[idx])){
            tmp.push_back(ary[idx]);
            recursive(tmp, cnt+1, 0);
            tmp.pop_back();
        }
        recursive(tmp, cnt, idx+1);
    }
}

void start(){
    input();
    recursive(vector<int>(),0,0);
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    start();
}