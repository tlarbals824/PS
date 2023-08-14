#include<iostream>
#include<deque>
#include<algorithm>
using namespace std;

int n, m, r;
int depth;
int ary[500][500];

int dirY[4]={0,1,0,-1};
int dirX[4]={1,0,-1,0};

void input(){
    cin>>n>>m>>r;
    depth = min(n,m)/2;
    for(int i=1;i<=n;i++){
        for(int j=1;j<=m;j++){
            cin>>ary[i][j];
        }
    }
}

void output(){
    for(int i=1;i<=n;i++){
        for(int j=1;j<=m;j++){
            cout<<ary[i][j]<<' ';
        }
        cout<<'\n';
    }
}


void cal(){
    for(int i=1;i<=depth;i++){
        deque<int> dq;
        
        int startY = i;
        int startX = i;
        int k =0;
        while(k<4){
            int tmpY = startY + dirY[k];
            int tmpX = startX + dirX[k];

            if(tmpY == i && tmpX == i){
                dq.push_back(ary[i][i]);
                break;
            }

            if((tmpY>=i&&tmpY<=n-i+1)&&(tmpX>=i&&tmpX<=m-i+1)){
                dq.push_back(ary[tmpY][tmpX]);
                startY=tmpY;
                startX=tmpX;
            }else k++;
        }

        for(int j=0;j<(r%(2*(n+m)+4-8*i));j++){
            int tmp = dq.front();
            dq.pop_front();
            dq.push_back(tmp);
        }

        startY = i;
        startX = i;

        k =0;
        while(k<4){
            int tmpY = startY + dirY[k];
            int tmpX = startX + dirX[k];

            if(tmpY == i && tmpX == i){
                ary[i][i]=dq.front();
                dq.pop_front();
                break;
            }

            if((tmpY>=i&&tmpY<=n-i+1)&&(tmpX>=i&&tmpX<=m-i+1)){
                
                ary[tmpY][tmpX]=dq.front();
                dq.pop_front();

                startY=tmpY;
                startX=tmpX;
            }else k++;
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