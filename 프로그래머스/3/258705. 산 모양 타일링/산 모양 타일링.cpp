#include <string>
#include <vector>
#include<iostream>

using namespace std;

long long check[200200][2]={0};

int solution(int n, vector<int> tops) {    
    check[0][0]=1;
    check[0][1]=0;
    
    for(int i=1;i<2*n+1;i++){
        check[i][0]=(check[i-1][0]+check[i-1][1])%10007;
        if(i%2==1 && tops[(i-1)/2]==1){
            check[i][1]=((2*check[i-1][0])%10007+check[i-1][1])%10007;
        }else{
            check[i][1]=check[i-1][0];
        }
    }
    return (check[2*n][0]+check[2*n][1])%10007;
}