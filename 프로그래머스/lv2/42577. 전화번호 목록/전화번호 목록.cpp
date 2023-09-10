#include <string>
#include <vector>
#include<algorithm>
#include<iostream>

using namespace std;

vector<string> tmpString[30];
int maxLength = 0;

bool compare(string first, string second){
    return first.size() < second.size();
}

bool solution(vector<string> phone_book) {
    sort(phone_book.begin(), phone_book.end(), compare); // nlogn
    for(int i=0;i<phone_book.size();i++){
        tmpString[phone_book[i].size()].push_back(phone_book[i]); // n
        maxLength=max(maxLength,(int)phone_book[i].length());
    }
    for(int i=0;i<phone_book.size();i++){
        for(int j=phone_book[i].size()+1;j<=maxLength;j++){
            for(int k = 0; k<tmpString[j].size();k++){
                for(int x = 0;x<tmpString[j][k].size();x++){
                    if(tmpString[j][k][x]!=phone_book[i][x]) break;
                    if(x+1==phone_book[i].size()) return false;
                }
            }
        }
    }
    return true;
}