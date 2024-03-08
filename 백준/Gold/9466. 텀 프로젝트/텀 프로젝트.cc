#include<iostream>
#include<set>
#include<map>
#include<vector>

using namespace std;

int t,n;
int ary[100010]={0};
int check[100010]={0};
int result=0;

vector<int> findLoopNums(int idx, int endIdx){
    vector<int> loopNums;
    int currentIdx = idx;
    loopNums.push_back(currentIdx);
    while(true){
        int nextIdx = ary[currentIdx];

        if(nextIdx==endIdx){
            return loopNums;
        }

        currentIdx=nextIdx;
        loopNums.push_back(currentIdx);
    }
}

int find(int idx){
    set<int> nums;
    
    int currentIdx = idx;
    nums.insert(idx);
    while(true){
        int nextIdx = ary[currentIdx];
        if(check[nextIdx]==1){
            for(auto iter = nums.begin(); iter!=nums.end(); iter++){
                check[*iter]=1;
            }
            return nums.size();
        }
        if(nextIdx==currentIdx){
            for(auto iter = nums.begin(); iter!=nums.end(); iter++){
                check[*iter]=1;
            }
            return nums.size()-1;
        }

        auto findIdx = nums.find(nextIdx);
        if(findIdx!=nums.end()){
            for(auto iter = nums.begin(); iter!=nums.end(); iter++){
                check[*iter]=1;
            }
            return nums.size()-findLoopNums(nextIdx, currentIdx).size()-1;
        }

        currentIdx=nextIdx;
        nums.insert(currentIdx);
    }
}

void cal(){
    for(int i=1;i<=n;i++){
        // cout<<i<<' ';
        if(check[i]==1) continue;
        result+=find(i);
        // cout<<result<<'\n';
        // for(int j=1;j<=n;j++){
        //     cout<<check[j]<<' ';
        // }
        // cout<<"\n";
    }
}

void input(){
    cin>>t;
    for(int i=0;i<t;i++){
        cin>>n;
        fill_n(check,100010,0);
        fill_n(ary,100010,0);
        for(int j=1;j<=n;j++){
            cin>>ary[j];
        }
        cal();
        cout<<result<<'\n';
        result=0;
    }
}

void start(){
    input();
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    start();
}