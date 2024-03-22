#include <string>
#include <vector>
#include<iostream>

using namespace std;

long long calculate(vector<int> sequence, int parse){
    long long check[500100]={0};
    
    long long maxNum=-1;
    
    for(int i=1;i<=sequence.size();i++){
        check[i]=max((long long)sequence[i-2]*-1*parse, check[i-1])+sequence[i-1]*parse;
        parse*=-1;
        
        maxNum=max(check[i],maxNum);
    }
    
    return maxNum;
}

long long solution(vector<int> sequence) {
    return max(calculate(sequence, 1), calculate(sequence,-1));
}