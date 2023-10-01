#include<iostream>
#include<set>
using namespace std;

int n,m;
int knowPeopleCount;
set<int> knowPeople;
set<int> people[60];
set<int> result;

void input(){
    cin>>n>>m;
    cin>>knowPeopleCount;
    for(int i=0;i<knowPeopleCount;i++){
        int peopleNum;
        cin>>peopleNum;
        knowPeople.insert(peopleNum);
    }
    for(int i=1;i<=m;i++){
        int count;
        cin>>count;
        set<int> tmp;
        for(int j=0;j<count;j++){
            int peopleNum;
            cin>>peopleNum;
            people[peopleNum].insert(i);
        }
    }
}

void output(){
    cout<<result.size()<<'\n';
}

bool hasParty(int idx, set<int> excludeParty){
    for(auto iter = excludeParty.begin(); iter!=excludeParty.end(); iter++){
        if(idx == *iter) return true;
    }
    return false;
}

void cal(){
    // for(int i=1;i<=n;i++){
    //     cout<<i<<"번째 사람: ";
    //     for(auto iter = people[i].begin(); iter!=people[i].end();iter++){
    //         cout<<*iter<<' ';
    //     }
    //     cout<<'\n';
    // }
    set<int> excludeParty;
    for(auto iter = knowPeople.begin(); iter!=knowPeople.end();iter++){
        for(auto iter2 = people[*iter].begin(); iter2!=people[*iter].end();iter2++){
            excludeParty.insert(*iter2);
        }
    }
    for(int j=1;j<=n;j++){
        for(int i=1;i<=n;i++){
            bool flag = true;
            for(auto iter = people[i].begin();iter!=people[i].end();iter++){
                if(hasParty(*iter, excludeParty)){
                    flag = false;
                    break;
                }
            }
            if(!flag){
                for(auto iter = people[i].begin();iter!=people[i].end();iter++){
                    excludeParty.insert(*iter);
                }
            }
        }
    }
    for(int i=1;i<=n;i++){
        bool flag = true;
        for(auto iter = people[i].begin();iter!=people[i].end();iter++){
            if(hasParty(*iter, excludeParty)){
                flag = false;
                break;
            }
        }
        if(flag){
            for(auto iter = people[i].begin();iter!=people[i].end();iter++){
                result.insert(*iter);
            }
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