#include<iostream>
#include<algorithm>

using namespace std;

int n;
int tmp[6]={0};

void input(){
    cin>>n;
    for(int i=1;i<=n;i++){
        int tmp1,tmp2,tmp3;
        cin>>tmp1>>tmp2>>tmp3;
        int tmpNum[6];
        for(int i=0;i<6;i++){
            tmpNum[i]=tmp[i];
        }
        tmp[0]=max(tmpNum[0],tmpNum[1])+tmp1;
        tmp[1]=max(tmpNum[0],max(tmpNum[1],tmpNum[2]))+tmp2;
        tmp[2]=max(tmpNum[1],tmpNum[2])+tmp3;
        tmp[3]=min(tmpNum[3],tmpNum[4])+tmp1;
        tmp[4]=min(tmpNum[3],min(tmpNum[4],tmpNum[5]))+tmp2;
        tmp[5]=min(tmpNum[4],tmpNum[5])+tmp3;
    }
    
    cout<<max(tmp[0],max(tmp[1],tmp[2]))<<' ';
    cout<<min(tmp[3],min(tmp[4],tmp[5]))<<'\n';
}

void start(){
    input();
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    start();
}