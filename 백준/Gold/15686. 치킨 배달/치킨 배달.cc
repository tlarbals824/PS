#include<iostream>
#include<algorithm>

using namespace std;

int n,m;
int city[60][60]={0};
int result=10e8;

vector<pair<int,int>> chicken;
vector<pair<int,int>> house;

void input(){
    cin>>n>>m;
    for(int i =1;i<=n;i++){
        for(int j=1;j<=n;j++){
            cin>>city[i][j];
            if(city[i][j]==1){
                house.push_back({i,j});
            }
            if(city[i][j]==2){
                chicken.push_back({i,j});
            }
        }
    }
}

void output(){
    cout<<result<<'\n';
}

int minimumDistance(vector<pair<int,int>> selected){
    int sum=0;
    for(int i=0;i<house.size();i++){
        int tmp=10e8;
        for(int j=0;j<selected.size();j++){
            int distance = abs(house[i].first-selected[j].first)+abs(house[i].second-selected[j].second);
            tmp=min(distance, tmp);
        }
        sum+=tmp;
    }
    return sum;
}

void recursive(int cnt, vector<pair<int,int>> selected){
    if(cnt>chicken.size()) return;
    if(selected.size() <= m&& selected.size()>0 &&cnt == chicken.size()){
        result=min(result,minimumDistance(selected));
    }else{
        recursive(cnt+1, selected);
        vector<pair<int,int>> tmp(selected.size());
        copy(selected.begin(), selected.end(), tmp.begin());
        tmp.push_back(chicken[cnt]);
        recursive(cnt+1, tmp);
    }
}

void cal(){
    recursive(0, vector<pair<int,int>>());
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