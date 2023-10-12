#include<iostream>
#include<vector>
#include<algorithm>
#include<bitset>
#include<map>
using namespace std;

int n;
int ary[20][20];
map<int,int> check[20];
int result=10e8;

void input(){
    cin>>n;
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            cin>>ary[i][j];
        }
    }
}

void output(){
    cout<<result<<'\n';
}

int shitf(int bit, int cnt){
    return bit | 1 << cnt;
}

bool checkBit(int bit, int idx){
    return bit & 1 << idx;
}


void calculateAry(int bit){
    for(int i=2;i<=n;i++){
        if(!checkBit(bit, i)){
            int tmpResult=10e8;
            for(int j=2;j<=n;j++){
                if(i==j) continue;
                if(checkBit(bit,j)){
                    if(ary[i][j]==0) continue;
                    int tmpBit = bit ^ 1 << j;
                    if(check[j].find(tmpBit)==check[j].end()) continue;
                    if(tmpResult>ary[i][j]+check[j].find(tmpBit)->second){
                        tmpResult=ary[i][j]+check[j].find(tmpBit)->second;
                    }
                }
            }
            check[i].insert({bit,tmpResult});
        }
    }
}

void recursive(int k, int idx, int bit,int cnt){
    if(cnt==k){
        calculateAry(bit);
    }else{
        if(idx>n) return;
        recursive(k,idx+1,bit,cnt);
        recursive(k,idx+1,shitf(bit,idx),cnt+1);
    }
}

int initialize(){
    int tmpBit=0;
    for(int i=2;i<=n;i++){
        tmpBit=shitf(tmpBit,i);
    }
    return tmpBit;
}

void cal(){
    for(int i=2;i<=n;i++){
        if(ary[i][1]==0) continue;
        check[i].insert({0,ary[i][1]});
    }
    for(int k=1;k<=n-2;k++){
        recursive(k, 2, 0,0);
    }
    for(int i=2;i<=n;i++){
        if(ary[1][i]==0) continue;
        int tmpBit = initialize();
        tmpBit = tmpBit ^ 1 << i;
        result=min(result,ary[1][i]+check[i].find(tmpBit)->second);
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