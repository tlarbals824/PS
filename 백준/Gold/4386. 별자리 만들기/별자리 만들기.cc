#include<iostream>
#include<vector>
#include<algorithm>
#include<queue>
#include<set>
#include<cmath>
using namespace std;

int n;
vector<pair<double, double>> stars;
vector<vector<pair<int,double>>> edge;

double result=0;

void input(){
    cin>>n;
    edge.assign(n+1, vector<pair<int,double>>());
    for(int i=0;i<n;i++){
        double y,x;
        cin>>y>>x;
        stars.push_back({y,x});
    }
}

void output(){
    printf("%.2lf\n",result);
}

double calDistance(int idx1, int idx2){
    double fromY=stars[idx1].first, fromX=stars[idx1].second;
    double toY=stars[idx2].first, toX=stars[idx2].second;

    return sqrt((fromY-toY)*(fromY-toY)+(fromX-toX)*(fromX-toX));
}

void cal(){
    for(int i=0;i<n;i++){
        for(int j=i+1;j<n;j++){
            double distance = calDistance(i,j);
            edge[i].push_back({j,distance});
            edge[j].push_back({i,distance});
        }
    }

    set<int> starIdxs;
    priority_queue<pair<double,int>> pq;
    pq.push({0,0});
    while(!pq.empty()){
        auto top = pq.top();
        pq.pop();
        if(starIdxs.find(top.second)!=starIdxs.end()) continue;
        starIdxs.insert(top.second);
        result-=top.first;

        if(starIdxs.size()==n) break;

        for(int i=0;i<edge[top.second].size();i++){
            if(starIdxs.find(edge[top.second][i].first)==starIdxs.end()){
                pq.push({-edge[top.second][i].second,edge[top.second][i].first});
            }
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