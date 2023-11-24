#include <string>
#include <vector>
#include<iostream>

using namespace std;

long long solution(int cap, int n, vector<int> deliveries, vector<int> pickups) {
    long long answer = 0;
    int maxDeliveriesIdx = n-1;
    int maxPickupsIdx = n-1;
    while(true){
        int tmpCap=0;
        int lastIdx=-1;
        for(int i=maxDeliveriesIdx;i>=0;i--){
            if(deliveries[i]==0) continue;
            int cnt = deliveries[i];
            lastIdx=max(lastIdx,(i+1));   
            if(tmpCap+cnt>cap){
                deliveries[i]=tmpCap+cnt-cap;
                break;
            }else{
                tmpCap+=cnt;
                deliveries[i]=0;
                maxDeliveriesIdx=i;
            }
        }
        tmpCap=0;
        for(int i=maxPickupsIdx;i>=0;i--){
            if(pickups[i]==0) continue;
            int cnt = pickups[i];
            lastIdx=max(lastIdx,(i+1));  
            if(tmpCap+cnt>cap){
                pickups[i]=tmpCap+cnt-cap;
                break;
            }else{
                tmpCap+=cnt;
                pickups[i]=0;
                maxPickupsIdx=i-1;
            }
        }
        if(lastIdx==-1) break;
        answer+=(2*lastIdx);
    }
    return answer;
}