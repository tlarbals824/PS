#include<iostream>

using namespace std;

int n,m,r;
int ary[50][50][4];
int tmp[50][50][4];
bool flag=false;

void input(){
    cin>>n>>m>>r;
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            int y=i%(n/2);
            int x=j%(m/2);
            int cnt = 2*(i/(n/2))+(j/(m/2));
            cin>>ary[y][x][cnt];
        }
    }
}

void output(){
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            int y=i%(n/2);
            int x=j%(m/2);
            int cnt = 2*(i/(n/2))+(j/(m/2));
            cout<<ary[y][x][cnt]<<' ';
        }
        cout<<'\n';
    }
    cout<<'\n';
}

void copyAry(){
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            int y=i%(n/2);
            int x=j%(m/2);
            int cnt = 2*(i/(n/2))+(j/(m/2));
            ary[y][x][cnt]=tmp[y][x][cnt];
        }
    }
}

void cal1(){
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            int y=i%(n/2);
            int x=j%(m/2);
            int cntY = 2*(i/(n/2));
            int cntX = (j/(m/2));
            tmp[(n/2-1-y)][x][(2-cntY+cntX)]=ary[y][x][cntY+cntX];
        }
    }
}

void cal2(){
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            int y=i%(n/2);
            int x=j%(m/2);
            int cntY = 2*(i/(n/2));
            int cntX = (j/(m/2));
            tmp[y][(m/2-1-x)][(cntY+1-cntX)]=ary[y][x][cntY+cntX];
        }
    }
}

void cal3(){
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            int y=i%(n/2);
            int x=j%(m/2);
            int cntY = 2*(i/(n/2));
            int cntX = (j/(m/2));
            tmp[x][(n/2)-1-y][1-cntY/2+2*cntX]=ary[y][x][cntY+cntX];
        }
    }
    flag=true;
}

void cal4(){
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            int y=i%(n/2);
            int x=j%(m/2);
            int cntY = 2*(i/(n/2));
            int cntX = (j/(m/2));
            tmp[(m/2)-1-x][y][cntY/2+2-2*cntX]=ary[y][x][cntY+cntX];
        }
    }
    flag=true;
}

void cal5(){
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            int y=i%(n/2);
            int x=j%(m/2);
            int cntY = 2*(i/(n/2));
            int cntX = (j/(m/2));
            tmp[y][x][1-cntY/2+2*cntX]=ary[y][x][cntY+cntX];
        }
    }
}

void cal6(){
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            int y=i%(n/2);
            int x=j%(m/2);
            int cntY = 2*(i/(n/2));
            int cntX = (j/(m/2));
            tmp[y][x][cntY/2+2-2*cntX]=ary[y][x][cntY+cntX];
        }
    }
}

void cal(){
    for(int i=0;i<r;i++){
        int num;
        cin>>num;
        if(num==1) cal1();
        if(num==2) cal2();
        if(num==3) cal3();
        if(num==4) cal4();
        if(num==5) cal5();
        if(num==6) cal6();
        if(flag){
            swap(n,m);
            flag=false;
        }
        copyAry();
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