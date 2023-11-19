#include <string>
#include <vector>
#include<queue>
#include<set>
#include<algorithm>

using namespace std;

set<queue<int>> cache;
queue<int> q1;
queue<int> q2;
long queue1Sum = 0;
long queue2Sum = 0;
long total;

int solution(vector<int> queue1, vector<int> queue2) {
    int answer = 0;
    
    for(int i =0;i<queue1.size();i++){
        queue1Sum +=queue1[i];
        q1.push(queue1[i]);
    }
    for(int i =0;i<queue2.size();i++){
        queue2Sum +=queue2[i];
        q2.push(queue2[i]);
    }
    
    cache.insert(q1);
    cache.insert(q2);
    
    if((queue1Sum+queue2Sum)%2!=0){
        return -1;
    }
    
    total = queue1Sum + queue2Sum;
    int maxCnt = max(queue1.size(),queue2.size());
    
    while(total/2 != queue1Sum){
        if(answer >= maxCnt*3){
            return -1;
        }
        if(total/2 < queue1Sum){
            int tmp = q1.front();
            q1.pop();
            q2.push(tmp);
            queue1Sum=queue1Sum-tmp;
        }else{
            int tmp = q2.front();
            q2.pop();
            q1.push(tmp);
            queue1Sum=queue1Sum+tmp;
        }
        answer++;
        if(q1.size()==0||q2.size()==0){
            return -1;
        }
        
        if(cache.find(q1)!=cache.end()){
            answer=-1;
            break;
        }
    }
    return answer;
}