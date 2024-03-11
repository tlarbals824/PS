#include<iostream>
#include<set>
using namespace std;

set<int> coins;
int n,k;
int result=0;

void input(){
    cin>>n>>k;
    for(int i=0;i<n;i++){
        int value;
        cin>>value;
        coins.insert(-value);
    }
}

void output(){
    cout<<result<<'\n';
}

void cal(){
    while(k>0){
        int value = -*coins.lower_bound(-k);
        result++;
        k-=value;
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