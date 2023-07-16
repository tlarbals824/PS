#include<iostream>

using namespace std;

int n;
int ary[110000];
int dpIncrease[110000][2];
int maxSum=-10e8;

void input(){
    cin>>n;
    for(int i =0;i<n;i++){
        cin>>ary[i];
    }
}

void output(){
    cout<<maxSum<<'\n';
}

void cal(){
    dpIncrease[0][0]=ary[0];
    dpIncrease[0][1]=0;
    maxSum=max(dpIncrease[0][0],maxSum);
    for(int i=1;i<n;i++){
        dpIncrease[i][0]=max(ary[i], dpIncrease[i-1][0]+ary[i]);
        maxSum=max(dpIncrease[i][0],maxSum);

        dpIncrease[i][1]=max(dpIncrease[i-1][0], dpIncrease[i-1][1]+ary[i]);
        maxSum=max(dpIncrease[i][1],maxSum);
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