#include<iostream>
#include<queue>
#include<stack>

using namespace std;

int t;

deque<int> q;
string inputCommand;
int numSize;
string numArray;

char x;

void input(){
    cin>>t;
}

void cal(){
    cin>>inputCommand;
    cin>>numSize;

    
    cin>>x;
    for(int i=0;i<numSize;i++){
        int tmp;
        cin>>tmp;
        q.push_back(tmp);
        if(i!=numSize-1) cin>>x;
    }
    cin>>x;

    bool flag=true; // true 면 정상 false 면 반대

    for(int i=0;i<inputCommand.length();i++){
        if(inputCommand[i]=='R'){
            flag=!flag;
        }else{
            if(numSize==0){
                cout<<"error\n";
                return;
            }else{
                if(flag){
                    q.pop_front();
                }else{
                    q.pop_back();
                }
                numSize--;
            }
        }
    }
    cout<<'[';
    if(flag){
        for(int i=0;i<numSize;i++){
            int tmp=q.front();
            q.pop_front();
            cout<<tmp;
            if(i!=numSize-1) cout<<',';
        }
    }else{
        for(int i=0;i<numSize;i++){
            int tmp=q.back();
            q.pop_back();
            cout<<tmp;
            if(i!=numSize-1) cout<<',';
        }
    }
    
    cout<<']'<<'\n';
}

void start(){
    input();
    for(int i=0;i<t;i++){
        cal();
    }
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    start();
}