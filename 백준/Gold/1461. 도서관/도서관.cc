#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

int n,m;
int moveCnt = 0;
vector<int> plusAry= vector<int>();
vector<int> minusAry = vector<int>();

void input(){
    cin>>n>>m;
    for(int i=0;i<n;i++){
        int tmp;
        cin>>tmp;
        if(tmp>0) plusAry.push_back(-tmp);
        else minusAry.push_back(tmp);
    }
    if(!plusAry.empty()){
        sort(plusAry.begin(), plusAry.end());
    }

    if(!minusAry.empty()){
        sort(minusAry.begin(), minusAry.end());
    }
}

void output(){
    cout<<moveCnt<<'\n';
}
void cal(){
    for(int i=0;i<plusAry.size();i=i+m){
        moveCnt=moveCnt-plusAry[i]*2;
    }
    for(int i=0;i<minusAry.size();i=i+m){
        moveCnt=moveCnt-minusAry[i]*2;        
    }

    if(plusAry.size()==0){
        moveCnt=moveCnt+minusAry[0];
    }
    else if(minusAry.size()==0){
        moveCnt=moveCnt+plusAry[0];
    }else if(plusAry[0]>minusAry[0]){
        moveCnt=moveCnt+minusAry[0];
    }else{
        moveCnt=moveCnt+plusAry[0];
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