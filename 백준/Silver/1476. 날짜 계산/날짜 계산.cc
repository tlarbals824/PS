#include<iostream>
#include<algorithm>

using namespace std;

/**
 * 15*e + E = 28*s + S = 19*m + M = result
 * 15*e = result - E
 * 28*s = result - S
 * 19*m = result - M
 */

int E, S, M;
int result;
int MAX = 10e8;

void input(){
    cin>>E>>S>>M;
}

void output(){
    cout<<result<<'\n';
}

bool cal(int num, int calTypeNum, int cycle){
    num = num - calTypeNum;
    if(num % cycle == 0) return true;
    return false;
}

bool checkNum(int num){
    return cal(num, E, 15)&&cal(num, S, 28)&&cal(num, M, 19);
}

void bruteForce(){
    int maxNum = max(E,S);
    maxNum = max(maxNum, M);
    for(int i = maxNum ; i< MAX; i++){
        if(checkNum(i)){
            result = i;
            break;
        }
    }
}

void start(){
    input();
    bruteForce();
    output();
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    start();
}