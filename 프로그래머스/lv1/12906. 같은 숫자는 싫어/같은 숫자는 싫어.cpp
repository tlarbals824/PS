#include <vector>
#include<set>

using namespace std;

vector<int> solution(vector<int> arr) 
{
    vector<int> answer;
    int previous=-1;
    for(int i=0;i<arr.size();i++){
        if(arr[i]!=previous){
            previous=arr[i];
            answer.push_back(arr[i]);
        }
    }
    
    return answer;
}