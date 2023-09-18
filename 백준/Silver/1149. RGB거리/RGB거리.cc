#include<iostream>
#include<queue>
#include<algorithm>

using namespace std;

int n;
vector<vector<pair<int,int>>> v;
int result=10e8;

void input(){
    cin>>n;
    for(int i=0;i<n;i++){
        int col1,col2,col3;
        cin>>col1>>col2>>col3;
        vector<pair<int,int>> tmp;
        tmp.push_back({col1,1});
        tmp.push_back({col2,2});
        tmp.push_back({col3,3});
        v.push_back(tmp);
    }
}

void output(){
    cout<<result<<'\n';
}

void cal(){
    int check[1500][3]={0};
    check[0][0]=v[0][0].first;
    check[0][1]=v[0][1].first;
    check[0][2]=v[0][2].first;
    for(int i=1;i<n;i++){
        check[i][0]=v[i][0].first+min(check[i-1][1],check[i-1][2]);
        check[i][1]=v[i][1].first+min(check[i-1][0],check[i-1][2]);
        check[i][2]=v[i][2].first+min(check[i-1][0],check[i-1][1]);
    }
    result = min(check[n-1][0],min(check[n-1][1],check[n-1][2]));
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