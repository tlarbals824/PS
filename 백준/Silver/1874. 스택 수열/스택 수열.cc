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
}

void cal(){
    for(int i = 1 ; i<=n;i++){
        stackPush(i);
        if(stackNum.top() == result[cnt]){
            stackPop();
            for(int j = stackNum.size(); j>0;j--){
                if( stackNum.top() == result[cnt]){
                    stackPop();
                }else{
                    break;
                }
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

// 1 2 3 4 5 6 7 8 

// 1 2 3 4 : 
// 1 2 : 4 3
// 1 2 5 6 : 4 3
// 1 2 5 : 4 3 6
// 1 2 5 7 8 : 4 3 6
// 1 2 5 : 4 3 6 8 7
// 4 3 6 8 7 5 2 1

// 4 3 6 8 7 5 2 1

// push 시작
// 입력 수가 수열 순서의 수와 같음 : 스택 pop
// pop이 됐으면 상위 수와 수열의 수가 같은지 확인 -> 같으면 pop (반복)
// 다르면 push 시작