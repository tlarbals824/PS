#include <string>
#include <vector>
#include<algorithm>

#include<iostream>

using namespace std;

int check[200][200]={0};

int solution(int alp, int cop, vector<vector<int>> problems) {
    int current_alp =  alp;
    int current_cop = cop;
    int max_alp=alp;
    int max_cop=cop;
    
    for(int i=0;i<problems.size();i++){
        max_alp=max(problems[i][0],max_alp);
        max_cop=max(problems[i][1],max_cop);
    }
    
    for(int i=current_alp;i<=max_alp;i++){
        for(int j=current_cop;j<=max_cop;j++){
            check[i][j]=10e8;
        }
    }
    
    if(current_alp>=max_alp&&current_cop>=max_cop) return 0;
    
    check[current_alp][current_cop]=0;

    for(int i=current_alp;i<=max_alp;i++){
        for(int j=current_cop;j<=max_cop;j++){

            check[i+1][j]=min(check[i+1][j],check[i][j]+1);
            
            check[i][j+1]=min(check[i][j+1],check[i][j]+1);
            
            for(int k=0;k<problems.size();k++){
                if(i>=problems[k][0]&&j>=problems[k][1]){

                    int nextAlp = min(max_alp,i+problems[k][2]);
                    int nextCop = min(max_cop,j+problems[k][3]);

                    
                    check[nextAlp][nextCop]=min(check[nextAlp][nextCop], check[i][j]+problems[k][4]);
                }
            }            
        }
    }
    
    return check[max_alp][max_cop];
}