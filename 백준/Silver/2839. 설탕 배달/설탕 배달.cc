#include<iostream>

using namespace std;

int num;
int result=0;
void input(){
    cin>>num;
}

void output(){
    cout<<result<<'\n';
}

void cal(){
    while(num>=0){
        if(num%5==0){
            result += (num/5);
            return;
        }
        num-=3;
        result++;
    }
    if(num<0) result=-1;
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