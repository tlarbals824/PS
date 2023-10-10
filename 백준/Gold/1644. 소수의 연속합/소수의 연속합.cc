#include<iostream>
#include<set>
#include<math.h>
using namespace std;

int n;
int primeCheck[4000010]={0};
set<int> primeSet;
int result=0;

void input(){
    cin>>n;
}

void output(){
    cout<<result<<'\n';
}

void prime(){
    primeCheck[1]=1;
    for(int i=2;i<=sqrt(n);i++){
        for(int j=2*i;j<=n;j+=i){
            primeCheck[j]=1;
        }
    }
    for(int i=2;i<=n;i++){
        if(primeCheck[i]==0){
            primeSet.insert(i);
        }
    }
}

void cal(){
    auto tail=primeSet.begin();
    auto head=primeSet.begin();
    int sum = *tail;
    while(head!=primeSet.end()){
        if(sum>n){
            if(tail==head){
                tail=++head;
                sum=*tail;
            }else{
                sum-=*tail;
                tail++;
            }
        }else if(sum<n){
            head++;
            sum+=*head;
        }else if(sum==n){
            result++;
            sum+=*(++head);
        }
    }
}

void start(){
    input();
    prime();
    cal();
    output();
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    start();
}