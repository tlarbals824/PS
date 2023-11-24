#include <string>
#include <vector>
#include<algorithm>
#include<sstream>
#include<iostream>
#include<map>

using namespace std;

int year;
int month;
int day;
int tmpYear;
int tmpMonth;
int tmpDay;
int termMonth;

vector<string> split(string str, char delimiter){
    vector<string> result;
    stringstream ss(str);
    string tmp;
    
    while(getline(ss, tmp, delimiter)){
        result.push_back(tmp);
    }
    
    return result;
}

vector<int> solution(string today, vector<string> terms, vector<string> privacies) {
    vector<int> answer;
    vector<string> tmp = split(today,'.');
    year = stoi(tmp[0]);
    month=stoi(tmp[1]);
    day=stoi(tmp[2]);
    map<string,int> termMap;
    for(int i=0;i<terms.size();i++){
        termMap[terms[i].substr(0,1)]=stoi(terms[i].substr(2));
    }
    for(int i=0;i<privacies.size();i++){
        string term = privacies[i].substr(11,1);
        string date = privacies[i].substr(0,10);
        tmpYear = stoi(date.substr(0,4));
        tmpMonth = stoi(date.substr(5,2));
        tmpDay = stoi(date.substr(8,2));
        termMonth = termMap[term];
        
        tmpMonth+=termMonth;
        tmpYear+=((tmpMonth-1)/12);
        tmpMonth=(tmpMonth-1)%12+1;
        
        // cout<<tmpYear<<' '<<tmpMonth<<' '<<tmpDay<<'\n';
        
        if(year > tmpYear){
            answer.push_back(i+1);
        }else if(year == tmpYear){
            if(month>tmpMonth){
                answer.push_back(i+1);
            }else if(month==tmpMonth){
                if(day>=tmpDay){
                    answer.push_back(i+1);
                }
            }
        }
    }
    return answer;
}