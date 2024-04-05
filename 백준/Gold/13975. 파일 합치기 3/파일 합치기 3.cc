#include<iostream>
#include<set>
#include<queue>

using namespace std;

int t;

void cal(){
    int k;
    priority_queue<long long, vector<long long>, greater<long long>> pq;
    cin>>k;
    for(int i=0;i<k;i++){
        long long page;
        cin>>page;
        pq.push(page);
    }
    long long result=0;

    while(pq.size()>1){
        long long num1 = pq.top();
        pq.pop();
        long long num2 = pq.top();
        pq.pop();

        result+=(num1+num2);
        pq.push(num1+num2);
    }

    // while(pq.size()>1){
        
    //     vector<int> tmp;
    //     while(pq.size()>1){
    //         int num1 = pq.top();
    //         pq.pop();
    //         int num2 = pq.top();
    //         pq.pop();

    //         result+=(num1+num2);
    //         tmp.push_back(num1+num2);
    //     }

    //     if(pq.size()==1){
    //         tmp.push_back(pq.top());
    //         pq.pop();
    //     }

    //     for(int i=0;i<tmp.size();i++){
    //         pq.push(tmp[i]);
    //     }

    // }

    cout<<result<<'\n';
}

void input(){
    cin >> t; 
    for(int i=0;i<t;i++) 
        cal();
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    input();
}