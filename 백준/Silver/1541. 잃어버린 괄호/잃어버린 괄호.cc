#include <iostream>
#include <string>
using namespace std;

string inputStr;
int result = 0;

void input() {
    cin >> inputStr;
}

void output() {
    cout << result << '\n';
}

void cal() {
    string num = "";
    int tmpSum = 0;
    bool isMinus = false;
    bool isFirst = true;
    for (int i = 0; i < inputStr.length(); i++) {
        char tmp = inputStr[i];

        if (tmp == '-') {
            if(isMinus){
                result-=(tmpSum+atoi(num.c_str()));
            }else{
                result+=atoi(num.c_str());
                isMinus=true;
            }
            tmpSum = 0;
            num = "";
        } else if (tmp == '+') {
            if(isMinus) tmpSum+=atoi(num.c_str());
            else result+=atoi(num.c_str());
            num = "";
        } else {
            num += tmp;
        }
    }
    if(isMinus) result-=(tmpSum+atoi(num.c_str()));
    else result+=(tmpSum+atoi(num.c_str()));
}

void start() {
    input();
    cal();
    output();
}

int main() {
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    start();
}