#include <string>
#include <vector>
#include<queue>

using namespace std;

int solution(vector<int> scoville, int K) {
    priority_queue<int> pq;
    for(int i=0;i<scoville.size();i++){
        pq.push(-scoville[i]);
    }
    
    int time=0;
    
    while(true){
        int first = -pq.top();
        pq.pop();
        if(first>=K) break;
        
        if(pq.empty()) return -1;
        int second = -pq.top();
        pq.pop();
        
        pq.push(-(first+second*2));
        time++;
    }
    
    return time;
}