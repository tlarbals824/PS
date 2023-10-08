#include<iostream>
#include<vector>
using namespace std;

int n,s;
vector<int> ary;
int result=10e8;


void input(){
    cin>>n>>s;
    for(int i=0;i<n;i++){
        int num;
        cin>>num;
        ary.push_back(num);
    }
}

void output(){
    if(result==10e8){
        cout<<"0\n";
    }else{
        cout<<result<<'\n';
    }
}

void cal(){
    int tail=0;
    int head=0;
    int sum = ary[0];
    while(head<n){
        if(sum>=s){
            // cout<<tail<<' '<<head<<' '<<sum<<'\n';
            result=min(result,head-tail+1);
            sum=sum-ary[tail];
            if(tail==head){
                tail=head=head+1;
                sum=ary[tail];
            }else{
                tail++;
            }
        }
        if(sum<s){
            sum+=ary[++head];
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