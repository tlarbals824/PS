#include<iostream>
#include<vector>
#include<algorithm>

using namespace std;

int l,c;
vector<char> inputAry;
vector<char> rcvTmp;

void input(){
    cin>>l>>c;
    for(int i=0;i<c;i++){
        char tmp;
        cin>>tmp;
        inputAry.push_back(tmp);
    }
    sort(inputAry.begin(), inputAry.end());
}

bool checkChar(char input){
    return (input=='a'||input=='e'||input=='i'||input=='o'||input=='u');
}

void cal(int idx,int cnt){
    if(idx>c) return;
    if(rcvTmp.size()==l&&cnt>0&&rcvTmp.size()-cnt>1){
        for(int i=0;i<l;i++){
            cout<<rcvTmp[i];
        }
        cout<<'\n';
    }else{
        rcvTmp.push_back(inputAry[idx]);
        if(checkChar(inputAry[idx])){
            cal(idx+1,cnt+1);
        }else{
            cal(idx+1,cnt);
        }
        rcvTmp.pop_back();
        cal(idx+1,cnt);
    }
}

void start(){
    input();
    cal(0,0);
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    start();
}