#include<iostream>
#include<map>
#include<vector>
using namespace std;

long n,b;
vector<vector<int>> ary(6,vector<int>(6));
vector<vector<int>> result(6,vector<int>(6));
vector<vector<int>> tmp1(6,vector<int>(6));
vector<vector<int>> tmp2(6,vector<int>(6));
map<long,vector<vector<int>>> record;

void input(){
    cin>>n>>b;
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            cin>>ary[i][j];
            result[i][j]=ary[i][j];
        }
    }
}

void output(){
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){
            cout<<result[i][j]%1000<<' ';
        }
        cout<<'\n';
    }
}

vector<vector<int>> matrixMuliple(vector<vector<int>> target1, vector<vector<int>> target2){
    copy(target1.begin(), target1.end(), tmp1.begin());
    copy(target2.begin(), target2.end(), tmp2.begin());
    for(int i=1;i<=n;i++){
        for(int j=1;j<=n;j++){ 
            int y=1;
            int x=1;
            int sum=0;
            while(y<=n&&x<=n){
                sum+=(tmp1[i][x]*tmp2[y][j])%1000;
                y++;
                x++;
            }
            target1[i][j]=sum%1000;
        }
    }
    return target1;
}

vector<vector<int>> recursive(long size){
    if(size==1){
        return ary;
    }else if(size==2){
        return record.find(2)->second;
    }else{
        long mid=size/2;
        auto iter = record.find(size);
        if(iter!=record.end()){
            return iter->second;
        }else{
            auto tmp = matrixMuliple(recursive(mid),recursive(size-mid));
            record.insert({size, tmp});
            return tmp;
        }
    }
}

void cal(){
    record.insert({2,matrixMuliple(ary,ary)});
    auto tmp = recursive(b);
    copy(tmp.begin(), tmp.end(), result.begin());
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