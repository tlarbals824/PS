#include <string>
#include <vector>
#include<iostream>

using namespace std;

vector<int> tmp;

vector<int> solution(vector<int> progresses, vector<int> speeds) {
    for(int i=0;i<progresses.size();i++){
        int div = (100-progresses[i])/speeds[i];
        div += (100-progresses[i])%speeds[i]==0 ? 0 : 1;
        tmp.push_back(div);
    }
    vector<int> answer;
    int previousIdx=0;
    for(int i =0;i<tmp.size();i++){
        if(tmp[i]>tmp[previousIdx]){
            answer.push_back(i-previousIdx);
            previousIdx=i;
        }
        if(i+1==tmp.size()){
            answer.push_back(i-previousIdx+1);            
        }
    }
    return answer;
}