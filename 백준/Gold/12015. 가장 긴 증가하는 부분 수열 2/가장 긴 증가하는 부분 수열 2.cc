#include<iostream>
#include<set>
using namespace std;

multiset<int> ary;
int check[1000100];
int n;
int result=0;

void input(){
    cin>>n;
    for(int i=0;i<n;i++){
        int num;
        cin>>num;
        if(ary.empty()){
            ary.insert(num);
            continue;
        }
        auto iter = --ary.end();
        if(*iter < num){
            ary.insert(num);
        }else{
            iter = ary.lower_bound(num);
            ary.erase(iter);
            ary.insert(num);
        }
    }
    fill_n(check,n,0);
}

void output(){
    cout<<ary.size()<<'\n';
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