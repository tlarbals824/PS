#include <iostream>
#include <string>

using namespace std;

string str1;
string str2;
string str3;

int result = 0;

int check[200][200][200] = {0};

void input() {
    cin >> str1 >> str2 >> str3;
}

void output() {
    cout << result << '\n';
}

void cal() {
    for (int i = 1; i <= str1.length(); i++) {
        for (int j = 1; j <= str2.length(); j++) {
            for (int k = 1; k <= str3.length(); k++) {
                if (str1[i - 1] == str2[j - 1] && str2[j - 1] == str3[k - 1]) {
                    check[i][j][k] = check[i - 1][j - 1][k - 1] + 1;
                } else {
                    check[i][j][k] = max(check[i - 1][j][k], max(check[i][j - 1][k], check[i][j][k - 1]));
                }
            }
        }
    }

    int i = str1.length();
    int j = str2.length();
    int k = str3.length();

    while (i != 0 && j != 0 && k != 0) {
        if (check[i][j][k] == check[i - 1][j][k]) {
            i--;
        }else if (check[i][j][k] == check[i][j - 1][k]) {
            j--;
        }else if (check[i][j][k] == check[i][j][k - 1]) {
            k--;
        } else if (check[i][j][k] == check[i - 1][j - 1][k - 1] + 1) {
            i--;
            j--;
            k--;
            result++;
        }
    }
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