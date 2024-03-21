#include<iostream>
#include<queue>
#include<set>

using namespace std;

struct Node {
    int y;
    int x;
    int count;

    bool operator < (Node other) const{
        return this->count > other.count;
    }
};

priority_queue<Node> pq;

set<pair<int,int>> result;

int n,m;

char MAP[1010][1010];

int check[1010][1010]={0};



void input(){
    cin>>n>>m;
    int count=1;
    for(int i=1;i<=n;i++){
        for(int j=1;j<=m;j++){
            cin>>MAP[i][j];
            pq.push({i,j,count++});
        }
    }
}

void output(){
    cout<<result.size()<<'\n';
}

pair<int,int> move(int y,int x){
    if(MAP[y][x]=='U'){
        return {y-1,x};
    }else if(MAP[y][x]=='D'){
        return {y+1,x};
    }else if(MAP[y][x]=='L'){
        return {y,x-1};
    }else if(MAP[y][x]=='R'){
        return {y,x+1};
    }
    return {0,0};
}

void cal(){
    while(!pq.empty()){
        auto top = pq.top();
        pq.pop();

        // cout<<top.y<<' '<<top.x<<' '<<top.count<<'\n';

        // 다음에 갈 곳이 이미 도착한 곳이다?
        //      지금까지 온 길 중 포함된건가? 자신의 count와 같을때
        //          -1로 표시
        //      이미 지나온 길 인가? 자신의 count와 다를떄
        //          끝
        // 아니면 계속

        if(check[top.y][top.x]!=0) continue;

        auto nextPoint = move(top.y,top.x);

        if(check[nextPoint.first][nextPoint.second]!=0){
            if(check[nextPoint.first][nextPoint.second]==top.count){
                result.insert({top.y,top.x});
            }
        }else{
            pq.push({nextPoint.first, nextPoint.second, top.count});
        }
        check[top.y][top.x]=top.count;
        
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