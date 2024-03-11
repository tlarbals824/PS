#include<iostream>
#include<queue>

using namespace std;

int n;
priority_queue<int, vector<int>, greater<int>> pq;
int result=0;

void input(){
    cin>>n;
    for(int i=0;i<n;i++){
        int num;
        cin>>num;
        pq.push(num);
    }
}

void output(){
    cout<<result<<'\n';
}

void cal(){
    int sum=0;
    while(!pq.empty()){
        int top = pq.top();
        pq.pop();

        sum+=top;
        result+=sum;
    }
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