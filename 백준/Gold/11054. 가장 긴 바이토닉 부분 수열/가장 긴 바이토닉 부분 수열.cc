#include<iostream>

using namespace std;

int n;
int ary[1100];
int dpIncrease[1100];
int dpDecrease[1100];
int longestLength = 0;


void input(){
    cin>>n;
    for(int i =0;i<n;i++){
        cin>>ary[i];
    }
}

void output(){
    cout<<longestLength<<'\n';
}

void cal(){
    for(int i=0;i<n;i++){
        dpIncrease[i]=1;
        for(int j=0;j<i;j++){
            if(ary[j]<ary[i]){
                dpIncrease[i]=max(dpIncrease[j]+1, dpIncrease[i]);
            }
        }
    }

    for(int i=n-1;i>=0;i--){
        dpDecrease[i]=1;
        for(int j=n-1;j>i;j--){
            if(ary[j]<ary[i]){
                dpDecrease[i]=max(dpDecrease[j]+1, dpDecrease[i]);
            }
        }
    }

    for(int i =0;i<n;i++){
        longestLength=max(dpIncrease[i]+dpDecrease[i]-1, longestLength);
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