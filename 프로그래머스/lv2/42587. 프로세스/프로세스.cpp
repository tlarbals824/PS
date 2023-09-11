#include <string>
#include <vector>
#include<queue>

using namespace std;

int solution(vector<int> priorities, int location) {
    queue<int> q;
    for(int i=0;i<priorities.size();i++){
        q.push(i);
    }
    
    int process=1;
    
    for(int i =0;i<priorities.size();i++){
        int startIdx = q.front();
        q.pop();
        q.push(startIdx);
        int maxIdx=startIdx;
        while(true){
            int idx = q.front();
        
            if(idx==startIdx) break;
        
            q.pop();
            if(priorities[maxIdx]<priorities[idx]){
                maxIdx=idx;
            }
            q.push(idx);
        }
    
        priorities[maxIdx]=0;
        if(location==maxIdx){
            return process;
        }else{
            process++;
            while(true){
                int idx = q.front();
                q.pop();
                if(idx==maxIdx) break;
                
                q.push(idx);
            }
        }
    }
    
    return process;
}