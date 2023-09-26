#include<iostream>
#include<map>
#include<vector>

using namespace std;

vector<int> ary;


void input(){
    while(true){
        int num;
        cin>>num;
        if(cin.eof()==1)break;
        ary.push_back(num);
    }
}

void postOrder(int startIdx,int endIdx){
    if(startIdx>=endIdx) return;
    if(startIdx==endIdx){
        cout<<ary[startIdx]<<'\n';
    }
    int idx = startIdx+1;
    while(idx<endIdx){
        if(ary[startIdx]<ary[idx])
            break;
        idx++;
    }
    postOrder(startIdx+1, idx);
    postOrder(idx,endIdx);
    cout<<ary[startIdx]<<'\n';
}

void output(){
    postOrder(0, ary.size());
}

void start(){
    input();
    output();
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    start();
}