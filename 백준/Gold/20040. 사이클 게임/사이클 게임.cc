#include<iostream>
#include<set>
using namespace std;

int n,m;

int check[1000100]={0};
int result=0;

int findRoot(int idx){
    if(check[idx]!=idx){
        check[idx]=findRoot(check[idx]);
    }
    return check[idx];
}

void input(){
    cin>>n>>m;
    for(int i=0;i<n;i++){
        check[i]=i;
    }
    for(int i=0;i<m;i++){
        int v1,v2;
        cin>>v1>>v2;
        int rootV1 = findRoot(v1);
        int rootV2 = findRoot(v2);
        if(rootV1==rootV2){
            result=i+1;
            break;
        }else{
            check[rootV1]=rootV2;
        }
    }
}

void output(){
    cout<<result<<'\n';
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