#include<iostream>
#include<string>

using namespace std;

string str1;
string str2;
int result=0;

int check[1010][1010]={0};

void input(){
    cin>>str1>>str2;
}

void output(){
    cout<<result<<'\n';
}

void cal(){
    for(int i=1;i<=str1.length(); i++){
        for(int j=1;j<=str2.length();j++){
            if(str1[i-1]==str2[j-1]){
                check[i][j]=check[i-1][j-1]+1; // 같기 때문에, 이전에 가장 큰 부분 수열에서 +1 해서 값을 저장함, (i-1, j-1)의 값이 해당 범위에서의 가장 큰 부분수열임
            }else{
                check[i][j]=max(check[i-1][j],check[i][j-1]); // 다르기에 이전에 가장 큰 부분 수열의 크기를 따라감
            }
        }
    }

    int i = str1.length();
    int j = str2.length();

    

    while(i>0 && j>0){
        if(check[i][j]==check[i-1][j]){
            i--;
        }
        if(check[i][j]==check[i][j-1]){
            j--;
        }
        if(check[i][j]==check[i-1][j-1]+1){
            result++;
            i--;
            j--;
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