#include <string>
#include <vector>
#include<algorithm>
#include<queue>

using namespace std;

struct node{
    int current;
    int trap;
    int time;
};

struct compare{
    bool operator()(node& a, node& b){
        return a.time > b.time;      
    }
};

int maze[1010][1010]={0};
int trapMap[1010]={0};
int check[1010]={0};
int total=0;
int result=10e8;

bool isExist(int& bits, int& targetIdx){
    return bits&targetIdx;
}

int checkBit(int& bits, int& targetIdx){
    return bits|targetIdx;
}

int eraseBit(int& bits, int& targetIdx){
    return targetIdx^bits;
}

void cal(int start, int end, vector<int>& traps){
    for(int i=0;i<traps.size();i++){
        trapMap[traps[i]]=(1<<i);
    }

    priority_queue<node,vector<node>,compare> pq;
    pq.push(node{start,0,0});
    while(!pq.empty()){
        auto top = pq.top();
        pq.pop();
        
        int current = top.current;
        int trap = top.trap;
        int time = top.time; 

        if(current==end){
            result=time;
            return;
        }
        

        bool isCurrentVia=isExist(trap, trapMap[current]);
        
        for(int i=1;i<=total;i++){
            if(current==i) continue;
            if(check[i]>4) continue;
            if(maze[current][i]==10e8&&maze[i][current]==10e8) continue;
            bool isTargetVia=isExist(trap,trapMap[i]);
            
            int moveTime = 0;
            if(isCurrentVia==isTargetVia){ // maze
                if(maze[current][i]==10e8) continue;
                moveTime=maze[current][i];
            }else{ // reverse
                if(maze[i][current]==10e8) continue;
                moveTime=maze[i][current];
            }
            
            moveTime+=time;
            
            int nextBit = trap;
            
            if(trapMap[i]!=0){
                check[i]++;
                if(isTargetVia){
                    nextBit=eraseBit(trap,trapMap[i]);
                }else{
                    nextBit=checkBit(trap,trapMap[i]);
                }
            }
            
            pq.push(node{i,nextBit,moveTime});
        }
    }
}

int solution(int n, int start, int end, vector<vector<int>> roads, vector<int> traps) {
    total = n;
    
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            maze[i][j]=10e8;
        }
    }
    
    for(int i=0;i<roads.size();i++){
        maze[roads[i][0]][roads[i][1]]=min(roads[i][2],maze[roads[i][0]][roads[i][1]]);
    }
       
    cal(start,end,traps);
    
    return result;
}