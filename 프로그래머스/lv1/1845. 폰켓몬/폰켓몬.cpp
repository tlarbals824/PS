#include <vector>
#include<set>
using namespace std;

int solution(vector<int> nums)
{
    int totalNum = nums.size();
    set<int> prune;
    for(int i=0;i<totalNum;i++){
        prune.insert(nums[i]);
    }
    return prune.size() > totalNum/2 ? totalNum/2 : prune.size();
}