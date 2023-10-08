#include<iostream>
#include<map>

using namespace std;

long long n;

long long result=0;

map<long long, long> tmp;

long divNum = 1000000007;

void input(){
    cin>>n;
}

void output(){
    cout<<result<<'\n';
}

long divide(long long num){
    if(tmp.find(num)!=tmp.end()){
        return tmp.find(num)->second;
    }
    if(num==0) return 0;
    if(num==1) return 1;
    if(num==2) return 1;
    if(num%2==0){
        long long k = num/2;
        long result = divide(k)*(divide(k+1)+divide(k-1))%divNum;
        tmp.insert({num, result});
        return result;
    }else{
        long long k = (num+1)/2;
        long num1 = divide(k);
        long num2 = divide(k-1);
        long result = (num1*num1+num2*num2)%divNum;
        tmp.insert({num, result});
        return result;
    }
}

void cal(){
    result=divide(n);
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