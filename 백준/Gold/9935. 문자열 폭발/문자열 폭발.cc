#include<iostream>
#include<string>
#include<queue>
using namespace std;

string ary;
string invokeString;
string tmp="";


void input(){
    cin>>ary;
    cin>>invokeString;
}

void output(){
    if(tmp.length()==0){
        cout<<"FRULA\n";
    }else{
        cout<<tmp<<'\n';
    }
}

void cal(){
    
    for(int i=0;i<ary.length();i++){
        tmp+=ary[i];
        if(tmp.length()>=invokeString.length()){
            bool flag=false;
            int cnt=0;
            for(int j=invokeString.length()-1;j>=0;j--){
                if(tmp[tmp.length()-j-1]!=invokeString[invokeString.length()-j-1]){
                    flag=true;
                }
            }
            if(!flag){
                tmp.erase(tmp.end()-invokeString.length(),tmp.end());
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
    cout.tie(0);

    start();
}