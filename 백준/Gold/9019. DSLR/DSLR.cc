#include<iostream>
#include<vector>
#include<string>
#include<queue>

using namespace std;


/**
 * D : (2n%10000)
 * S : (n-1==0)?9999:n-1
 * L : d1 d2 d3 d4 -> d2 d3 d4 d1
 * R : d1 d2 d3 d4 -> d4 d1 d2 d3
 */

int t;
vector<pair<int,int> > cases;

void input(){
    cin>>t;
    for(int i=0;i<t;i++){
        int a,b;
        cin>>a>>b;
        cases.push_back(make_pair(a,b));
    }
}

int calD(int num){
    return (2*num)%10000;
}
int calS(int num){
    return (num==0)?9999:num-1;
}
int calL(int num){
    return (num%1000)*10+(num/1000);
}
int calR(int num){
    return (num/10)+(num%10)*1000;
}


void cal(){
    for(int i=0;i<t;i++){
        int check[10000]={0};

        queue<pair<int, string > > q; // 현재 수, 이동
        int matchNum = cases[i].second;
        q.push(make_pair(cases[i].first,""));
        
        while(!q.empty()){
            int numTmp = q.front().first;
            string moveTmp = q.front().second;
            int cnt = moveTmp.size();
            q.pop();

            if(numTmp==matchNum){
                cout<<moveTmp<<'\n';
                break;
            }else{
                int tmpNum;
                tmpNum=calD(numTmp);
                if(check[tmpNum]==0){
                    check[tmpNum]=1;
                    q.push(make_pair(tmpNum, moveTmp+"D"));
                }

                tmpNum=calS(numTmp);
                if(check[tmpNum]==0){
                    check[tmpNum]=1;
                    q.push(make_pair(tmpNum, moveTmp+"S"));
                }

                tmpNum=calL(numTmp);
                if(check[tmpNum]==0){
                    check[tmpNum]=1;
                    q.push(make_pair(tmpNum, moveTmp+"L"));
                }
        
                tmpNum=calR(numTmp);
                if(check[tmpNum]==0){
                    check[tmpNum]=1;
                    q.push(make_pair(tmpNum, moveTmp+"R"));
                }
            }
        }
    }
}

void start(){
    input();
    cal();
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    start();
}