#include<iostream>
#include<vector>
using namespace std;

vector<int> command;
int check[100010][5][5]={0};
int result=0;

void input(){
    while(true){
        int com;
        cin>>com;
        if(com==0) break;
        command.push_back(com);
    }
}

void output(){
    cout<<result<<'\n';
}

int calculatePower(int currentIdx, int moveIdx){
    if(currentIdx==0) return 2;
    if(currentIdx==moveIdx){
        return 1;
    }else if(currentIdx%2==moveIdx%2){
        return 4;
    }else{
        return 3;
    }
}

void cal(){
    if(command.size()==0) return;
    for(int i=0;i<=command.size();i++){
        for(int j=0;j<5;j++){
            for(int k=0;k<5;k++){
                check[i][j][k]=10e8;
            }
        }
    }
    check[0][0][0]=0;
    for(int i=1;i<=command.size();i++){
        for(int j=0;j<5;j++){
            for(int k=0;k<5;k++){
                if(check[i-1][j][k]==10e8) continue;
                if(check[i][command[i-1]][k]>check[i-1][j][k]+calculatePower(j,command[i-1])){
                    check[i][command[i-1]][k]=check[i-1][j][k]+calculatePower(j,command[i-1]);
                }
                if(check[i][j][command[i-1]]>check[i-1][j][k]+calculatePower(k,command[i-1])){
                    check[i][j][command[i-1]]=check[i-1][j][k]+calculatePower(k,command[i-1]);
                }
            }
        }
    }
    result=10e8;
    for(int j=0;j<5;j++){
        for(int k=0;k<5;k++){
            result=min(check[command.size()][j][k],result);
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