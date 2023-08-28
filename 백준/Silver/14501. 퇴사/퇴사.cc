#include<iostream>

using namespace std;

/**
 *  0 0 0 0 0 0 0
 *  0 0 10 10 10 10 10
 *  0 0 10 10 20 20 20
 *  0 0 10 10 20 20 20
 *  0 0 10 30 30 30 30
 *  0 0 10 30 30 45 45
 *  0 0 10 30 30 45 45
 * 
 * value = max(value(idx-t)+P,value)
 */

int n;
int inputT[2000000]={0};
int inputP[2000000]={0};
int result[2000000]={0};


void input(){
    cin>>n;
    for(int i=1;i<=n;i++){
        cin>>inputT[i]>>inputP[i];
    }
}

void output(){
    cout<<result[n]<<'\n';
}

void cal(){
    for(int i=1;i<=n;i++){
        if(i+inputT[i]-1>n) continue;
        if(result[i+inputT[i]-1]<result[i-1]+inputP[i]){
            result[i+inputT[i]-1]=result[i-1]+inputP[i];
            for(int j=i+inputT[i];j<=n;j++){
                result[j]=max(result[j],result[j-1]);
            }
        }
        // for(int j=1;j<=n;j++){
        //     cout<<result[j]<<' ';
        // }
        // cout<<'\n';
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