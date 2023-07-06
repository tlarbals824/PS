#include<iostream>
#include<stack>
#include<vector>

using namespace std;

int n;
int cnt = 0;
int result[100100];
stack<int> stackNum;
vector<char> stackOp;



void input(){
    cin>>n;
    for(int i = 0 ; i < n ; i++){
        cin>>result[i];
    }
}

void output(){
    if(cnt!=n) cout<<"NO\n";
    else{
        for(int i = 0 ; i < stackOp.size(); i++){
            cout<<stackOp[i]<<'\n';
        }
    }
}

void stackPush(int i){
    stackNum.push(i);
    stackOp.push_back('+');
}

void stackPop(){
    cnt++;
    stackNum.pop();
    stackOp.push_back('-');
    if(stackNum.size() > 0){
        if(stackNum.top() == result[cnt]){
            stackPop();
        }
    }
}

void cal(){
    for(int i = 1 ; i<=n;i++){
        stackPush(i);
        if(stackNum.top() == result[cnt]){
            stackPop();
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