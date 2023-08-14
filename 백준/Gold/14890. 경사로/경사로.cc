#include<iostream>

using namespace std;

int n, l;
int dp[120][120];
int result = 0;

void input(){
    cin>>n>>l;
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            cin>>dp[i][j];
        }
    }
}

void output(){
    cout<<result<<'\n';
}

void cal(){
    for(int i=0;i<n;i++){
        int check[120]={0};
        int previous = dp[i][0];
        bool flag = true;
        for(int j=0;j<n;j++){
            int current = dp[i][j];
            if(previous!=current){
                if(previous-current==1){
                    for(int z=0;z<l;z++){
                        if((current!=dp[i][j+z])||(check[j+z]!=0)||(j+z>=n)){
                            flag=false;
                            break;
                        }
                    }
                    if(flag){
                        for(int z=0;z<l;z++)
                        check[j+z]=1;
                    }
                }
                if(current-previous==1){
                    int tmp = previous;
                    for(int z=1;z<=l;z++){
                        if((tmp!=dp[i][j-z])||(check[j-z]!=0)||(j-z<0)){
                            flag=false;
                            break;
                        }
                    }
                    if(flag){
                        for(int z=1;z<=l;z++)
                        check[j-z]=1;
                    }
                }
                if(abs(previous-current)>1){
                    flag=false;
                    break;
                }
            }
            if(!flag) break;
            previous=current;
        }
        if(flag) result++;
    }

    for(int i=0;i<n;i++){
        int check[120]={0};
        int previous = dp[0][i];
        bool flag = true;
        for(int j=0;j<n;j++){
            int current = dp[j][i];
            if(previous!=current){
                if(previous-current==1){
                    int tmp = current;
                    for(int z=0;z<l;z++){
                        if((tmp!=dp[j+z][i])||(check[j+z]!=0)||(j+z>=n)){
                            flag=false;
                            break;
                        }
                    }
                    if(flag){
                        for(int z=0;z<l;z++)
                        check[j+z]=1;
                    }
                }
                if(current-previous==1){
                    int tmp = previous;
                    for(int z=1;z<=l;z++){
                        if((tmp!=dp[j-z][i])||(check[j-z]!=0)||(j-z<0)){
                            flag=false;
                            break;
                        }
                    }
                    if(flag){
                        for(int z=1;z<=l;z++)
                        check[j-z]=1;
                    }
                }
                if(abs(previous-current)>1){
                    flag=false;
                    break;
                }
            }
            if(!flag) break;
            previous=current;
        }
        if(flag) result++;
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