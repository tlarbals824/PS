#include <string>
#include <vector>
#include<queue>
#include<algorithm>
#include<iostream>

using namespace std;

vector<pair<string, int>> genresScore;
vector<pair<string, priority_queue<pair<int,int>>>> playsScore;

bool compare(pair<string,int> input1, pair<string,int> input2){
    return input1.second > input2.second;
}

int findIdxGenres(string value){
    for(int i=0;i<genresScore.size();i++){
        if(genresScore[i].first==value) return i;
    }
    return -1;
}

int findIdxPlay(string value){
    for(int i=0;i<playsScore.size();i++){
        if(playsScore[i].first==value) return i;
    }
    return -1;
}

vector<int> solution(vector<string> genres, vector<int> plays) {
    vector<int> answer;
    for(int i=0;i<genres.size();i++){
        int genresIdx = findIdxGenres(genres[i]);
        int playsIdx = findIdxPlay(genres[i]);
        
        if(genresIdx != -1){
            genresScore[genresIdx].second += plays[i];
            playsScore[playsIdx].second.push(make_pair(plays[i],-i));
        }else{
            genresScore.push_back(make_pair(genres[i],plays[i]));
            priority_queue<pair<int,int>> tmp;
            tmp.push(make_pair(plays[i],-i));
            playsScore.push_back(make_pair(genres[i], tmp));
        }
    }
    
    sort(genresScore.begin(), genresScore.end(), compare);
    for(int i = 0 ; i < genresScore.size() ; i++){
        // cout<<genresScore[i].first<<' '<<genresScore[i].second<<"\n";
        int idx = findIdxPlay(genresScore[i].first);
        auto tmp = playsScore[idx].second;
        int tmpSize = tmp.size();
        for(int j=0; j< min(2, tmpSize) ;j++){
            auto qTmp = tmp.top();
            answer.push_back(-qTmp.second);
            cout<<qTmp.first<<' '<<-qTmp.second<<"\n";
            tmp.pop();
        }
    }
    return answer;
}