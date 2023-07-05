#include<iostream>
#include<algorithm>

using namespace std;

int height[10];
int result[10];
bool finish = false;

void input(){
    for(int i = 0; i < 9; i++){
        cin>>height[i];
    }
}

void output(){
    sort(result, result+7);
    for(int i =0;i < 7;i++){
        cout<<result[i]<<'\n';
    }
}

void bruteForce(int cnt, int idx, int total){
    if(cnt > 9 || total > 100 || idx > 7) return;
    if(finish) return;
    if(total == 100 && idx == 7){
        finish = true;
        return;
    }else{
        result[idx] = height[cnt];
        bruteForce(cnt+1, idx + 1, total + height[cnt]);
        bruteForce(cnt+1, idx, total);
    }
}

void start(){
    input();
    bruteForce(0,0,0);
    output();
}

int main(){
    start();
}