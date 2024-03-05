#include<iostream>
#include<vector>
#include<queue>

using namespace std;


vector<int> student[40000];
int inDegree[40000]={0};
vector<int> result;
int n,m;


void input(){
    cin >> n >> m;
    for(int i=0;i<m;i++){
        int first,second;
        cin >> first >> second;
        student[first].push_back(second);
        inDegree[second]++;
    }
}

void output(){
    
}

void cal(){
    queue<int> q;
    for(int i=1;i<=n;i++){
        if(inDegree[i]==0){
            q.push(i);
        }
    }

    while(!q.empty()){
        int front = q.front();
        cout<< front <<' ';
        q.pop();

        for(int i=0;i<student[front].size();i++){
            if(--inDegree[student[front][i]]==0)
                q.push(student[front][i]);
        }
    }
}

void start(){
    input();
    cal();
    output();
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    start();
}