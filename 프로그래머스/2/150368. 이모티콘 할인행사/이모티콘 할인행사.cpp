#include <string>
#include <vector>
#include<map>
#include<queue>
#include<algorithm>
#include<iostream>

using namespace std;

struct node{
    int current;
    int plusCnt;
    int cost;
    vector<int> userMoney;
};

struct compare{
    bool operator()(node& a, node& b){
        if(a.plusCnt==b.plusCnt){
            return a.cost > b.cost;
        }
        return a.plusCnt > b.plusCnt;
    }
};

vector<int> answer;

int resultPlus=0;
int resultCost=0;
vector<int> userMoney(101);

void cal(vector<int>& sell, vector<vector<int>>& users, vector<int>& emoticons){
    for(int i=0;i<users.size();i++){
        userMoney[i]=0;
    }
    int plusCnt=0;
    int cost=0;
    for(int i=0;i<emoticons.size();i++){
        int percent = sell[i]*10;
        int emoticonCost = emoticons[i]*(10-sell[i])/10;
        for(int j=0;j<users.size();j++){
            if(userMoney[j]==-1) continue;
            if(users[j][0]<=percent){
                userMoney[j]+=emoticonCost;
                cost+=emoticonCost;
                if(userMoney[j]>=users[j][1]){
                    cost-=userMoney[j];
                    userMoney[j]=-1;
                    plusCnt++;
                }
            }
        }
    }
    if(resultPlus < plusCnt){
        resultPlus=plusCnt;
        resultCost=cost;
    }else if(resultPlus== plusCnt){
        resultCost = max(cost,resultCost);
    }
}

void recursive(vector<int>& sell, vector<vector<int>>& users, vector<int>& emoticons){
    if(sell.size()==emoticons.size()){
        cal(sell, users, emoticons);
    }else{
        for(int i=0;i<4;i++){
            sell.push_back(i+1);
            recursive(sell,users,emoticons);
            sell.pop_back();
        }
    }
}

void cal(vector<vector<int>>& users, vector<int>& emoticons){
    vector<int> sell;
    recursive(sell,users,emoticons);
}

vector<int> solution(vector<vector<int>> users, vector<int> emoticons) {
    
    cal(users,emoticons);
    
    vector<int> answer;
    answer.push_back(resultPlus);
    answer.push_back(resultCost);
    
    
    return answer;
}