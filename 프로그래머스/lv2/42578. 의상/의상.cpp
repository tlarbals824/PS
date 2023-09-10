#include <string>
#include <vector>
#include<map>
#include<iostream>

using namespace std;

map<string, vector<string>> tmpMap;

int solution(vector<vector<string>> clothes) {
    
    for(int i=0;i<clothes.size();i++){
        auto tmp = tmpMap.find(clothes[i][1]);
        if(tmp != tmpMap.end()){
            tmp->second.push_back(clothes[i][0]);
        }else{
            vector<string> tmpVector;
            tmpVector.push_back(clothes[i][0]);
            tmpMap.insert(make_pair(clothes[i][1],tmpVector));
        }
    }
    
    int result = 1;
    for(auto i = tmpMap.begin(); i!=tmpMap.end(); i++){
        result*=(i->second.size()+1);
    }
    
    return result-1;
}