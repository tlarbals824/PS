#include<iostream>
#include<string>
#include<stack>
#include<vector>
using namespace std;

string ary;
string result;

void input(){
    cin>>ary;
}

void output(){

}

bool isMultiDiv(char target){
    return (target=='*'||target=='/');
}

bool isPlusMinus(char target){
    return (target=='+'||target=='-');
}

void cal(){
    stack<char> signStack;
    for(int i=0;i<ary.size();i++){
        if(ary[i]=='('){
            signStack.push(ary[i]);
        }else if(ary[i]==')'){
            while(true){
                char sign = signStack.top();
                signStack.pop();
                if(sign=='(') break;
                cout<<sign;
            }
        }else if(isPlusMinus(ary[i])){
            while(true){
                if(signStack.empty()) break;
                char tmp = signStack.top();
                if(tmp=='(') break;
                else{
                    cout<<tmp;
                    signStack.pop();
                }
            }
            signStack.push(ary[i]);
        }else if(isMultiDiv(ary[i])){
            if(!signStack.empty()){
                if(isMultiDiv(signStack.top())){
                    cout<<signStack.top();
                    signStack.pop();
                }
            }
            signStack.push(ary[i]);
        }else{
            cout<<ary[i];
        }
    }
    while(!signStack.empty()){
        cout<<signStack.top();
        signStack.pop();
    }
    cout<<'\n';
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