#include<iostream>

using namespace std;

int n,m;
int paper[10][10];
int tmpPaper[10][10]; //1이면 가로, -1이면 세로
int result=0;

void input(){
    cin>>n>>m;
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            char tmp;
            cin>>tmp;
            paper[i][j]=tmp-'0';
        }
    }
}

void output(){
    cout<<result<<'\n';
}

void recursive(int y,int x){
    if(y==n&&x==0){
        int tmpResult=0;
        int check[10][10]={0};
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(check[i][j]==0){
                    int sum=paper[i][j];
                    check[i][j]=1;
                    if(tmpPaper[i][j]==1){
                        for(int a=j+1;a<m;a++){
                            if(tmpPaper[i][a]==1){
                                check[i][a]=1;
                                sum=sum*10+paper[i][a];
                            }else{
                                break;
                            }
                        }
                    }else if(tmpPaper[i][j]==-1){
                        for(int a=i+1;a<n;a++){
                            if(tmpPaper[a][j]==-1){
                                check[a][j]=1;
                                sum=sum*10+paper[a][j];
                            }else{
                               break;
                            }
                        }
                    }
                    tmpResult+=sum;
                }
            }
        }
        result=max(tmpResult, result);
    }else{
        tmpPaper[y][x]=1;
        if(x+1==m){
            recursive(y+1,0);
        }else{
            recursive(y,x+1);
        }

        tmpPaper[y][x]=-1;
        if(x+1==m){
            recursive(y+1,0);
        }else{
            recursive(y,x+1);
        }
    }
}

void cal(){
    recursive(0,0);
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