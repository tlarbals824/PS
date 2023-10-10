#include<iostream>
#include<vector>
#include<algorithm>
#include<math.h>
using namespace std;

int n;

int ary[100010]={0};
int check[1000100]={0};
int score[1000100]={0};
int MAX=1000001;

void input(){
    cin>>n;
    for(int i=1;i<=n;i++){
        int card;
        cin>>card;
        ary[i]=card;
        check[card]=1;
    }
    
}

void output(){
    for(int i=1;i<=n;i++){
        cout<<score[ary[i]]<<' ';
    }
    cout<<'\n';
}

void cal(){
    for(int i=1;i<=n;i++){
        for(int j=2*ary[i];j<1000001;j+=ary[i]){
            if(check[j]){
                score[ary[i]]++;
                score[j]--;
            }
        }
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