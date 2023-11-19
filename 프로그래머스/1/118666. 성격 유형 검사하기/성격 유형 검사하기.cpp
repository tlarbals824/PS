#include <string>
#include <vector>

using namespace std;

int result[4] = {0};

char surveyType[4][2] = {{'R','T'},
                           {'C','F'},
                           {'J','M'},
                           {'A','N'}};

string solution(vector<string> survey, vector<int> choices) {
    string answer = "";
    for(int i = 0 ;i<survey.size();i++){
        string tmpSurvey = survey[i];
        int tmpChoice = choices[i];
        for(int j=0;j<4;j++){
            if(tmpSurvey[0]==surveyType[j][0]){
                result[j]+=(tmpChoice-4);
            }else if(tmpSurvey[0]==surveyType[j][1]){
                result[j]-=(tmpChoice-4);
            }
        }
    }
    for(int i=0;i<4;i++){
        answer+=(result[i]<=0?surveyType[i][0]:surveyType[i][1]);
    }
    return answer;
}