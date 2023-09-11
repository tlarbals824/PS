#include <string>
#include <vector>

using namespace std;

int ary[100010]={0};

vector<int> solution(vector<int> prices) {
    vector<int> answer;
    for(int i = prices.size()-2;i>=0;i--){
        int maxIdx;
        for(maxIdx=i+1;maxIdx<prices.size();maxIdx++){
            if(prices[i]>prices[maxIdx]){
                break;
            }
        }
        ary[i]= maxIdx-i;
        ary[i]-= (maxIdx==prices.size()?1:0);
    }
    for(int i=0;i<prices.size();i++){
        answer.push_back(ary[i]);
    }
    return answer;
}