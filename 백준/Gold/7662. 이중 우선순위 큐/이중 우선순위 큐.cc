#include<iostream>
#include<algorithm>
#include<set>
using namespace std;

int t;

void input(){
    cin>>t;
}

void cal(){
    for(int i=0;i<t;i++){
        int k;
        cin>>k;
        multiset<int> ms;
        for(int j=0;j<k;j++){
            char com;
            int num;
            cin>>com>>num;
            if(com == 'I'){
                ms.insert(num);
            }
            if(com == 'D'){
                if(ms.size()==0) continue;
                if(num==1){
                    auto tmp = ms.end();
                    ms.erase((--tmp));
                }
                if(num==-1){
                    ms.erase(ms.begin());
                }
            }
        }


        if(ms.empty()) cout<<"EMPTY\n";
        else{
            auto tmp = ms.end();
            tmp--;
            cout<<*tmp<<' '<<*ms.begin()<<'\n';
        }
    }
}

void start(){
    input();
    cal();
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    start();
}