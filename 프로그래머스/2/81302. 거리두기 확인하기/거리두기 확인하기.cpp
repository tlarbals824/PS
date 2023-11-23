#include <string>
#include <vector>
#include <queue>

using namespace std;

struct people{
    int y;
    int x;
    int distance;
    int startY;
    int startX;
};

struct compare{
    bool operator()(people a, people b){
        return a.distance < b.distance;
    }
};

int row=5,col=5;

int dirY[4]={-1,0,1,0};
int dirX[4]={0,-1,0,1};

int checkValidDistance(vector<string> place){
    priority_queue<people,vector<people>,compare> pq;
    for(int i=0;i<5;i++){
        for(int j=0;j<5;j++){
            if(place[i][j]=='P'){
                pq.push(people{i,j,0,i,j});
            }
        }
    }
    
    while(!pq.empty()){
        auto top = pq.top();
        pq.pop();
        
        int currentY = top.y;
        int currentX = top.x;
        int distance = top.distance;
        int startY = top.startY;
        int startX = top.startX;
        
        if(distance==2) continue;
        
        for(int i=0;i<4;i++){
            int moveY = currentY+dirY[i];
            int moveX = currentX+dirX[i];
            if(moveY<0||moveY>=5) continue;
            if(moveX<0||moveX>=5) continue;
            if(moveY==startY&&moveX==startX) continue;
            if(place[moveY][moveX]=='P'){
                if(distance < 2) return 0;
            }
            if(place[moveY][moveX]=='O'){
                pq.push(people{moveY,moveX,distance+1,startY,startX});
            }
        }
    }
    
    return 1;
}

vector<int> solution(vector<vector<string>> places) {
    vector<int> answer;
    for(int i=0;i<5;i++){
        answer.push_back(checkValidDistance(places[i]));
    }
    return answer;
}