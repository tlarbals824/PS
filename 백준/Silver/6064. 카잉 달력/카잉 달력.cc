#include<iostream>

using namespace std;

int t;
int n,m,x,y;
// n*a+x = m*b+y

int cal(){
    for(int i = 0 ; i < n ;i++){
        if((m*i+y-x)%n==0){
            return m*i+y;
        }
    }
    return -1;
}

void input(){
    cin>>t;
    for(int i = 0 ; i<t;i++){
        cin>>n>>m>>x>>y;
        cout<<cal()<<'\n';    
    }
}

void start(){
    input();
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    start();
}