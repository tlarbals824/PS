#include<iostream>
#include<vector>
#include<queue>

using namespace std;

int k;
int v,e;
int check[20200];

void input(){
    cin>>k;
}

void checkBipartiteGraph(vector<int> *graph, int idx){
    if(!check[idx]){
        check[idx]=1;
    }

    for(int i=0;i<graph[idx].size();i++){
        if(!check[graph[idx][i]]){
            check[graph[idx][i]]=-check[idx];
            checkBipartiteGraph(graph, graph[idx][i]);
        }
    }
}

void cal(){
    for(int i=0;i<k;i++){
        cin>>v>>e;
        vector<int> graph[20200];
        for(int j=0;j<e;j++){
            int from,to;
            cin>>from>>to;
            graph[from].push_back(to);
            graph[to].push_back(from);
        }
        for(int j=1;j<=v;j++){
            if(!check[j]){
                checkBipartiteGraph(graph,j);
            }
        }

        bool flag=true;
        for(int x=1;x<=v;x++){
            for(int y=0;y<graph[x].size();y++){
                if(check[x]==check[graph[x][y]]){
                    cout<<"NO\n";
                    flag=false;
                    break;
                }
            }
            if(!flag) break;
        }

        if(flag) cout<<"YES\n";

        for(int x=0;x<=20010;x++){
            check[x]=0;
        }
    }
}

void start(){
    input();
    cal();
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    start();
}