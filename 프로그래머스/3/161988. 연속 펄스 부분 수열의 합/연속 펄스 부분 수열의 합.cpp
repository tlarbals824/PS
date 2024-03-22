#include <string>
#include <vector>
#include<iostream>

using namespace std;

long long calculate(vector<int> sequence, int parse){
    long long check[500100][2]={0};
    
    long long maxNum=-1;
    
    for(int i=1;i<=sequence.size();i++){
        check[i][0]=sequence[i-1]*parse;
        check[i][1]=max(check[i-1][0], check[i-1][1])+sequence[i-1]*parse;
        parse*=-1;
        
        maxNum=max(check[i][0],max(check[i][1],maxNum));
    }
    
    return maxNum;
}

long long solution(vector<int> sequence) {
    return max(calculate(sequence, 1), calculate(sequence,-1));
}