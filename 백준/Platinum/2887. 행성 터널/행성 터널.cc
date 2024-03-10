#include <algorithm>
#include <iostream>
#include <queue>
#include <set>
#include <vector>

using namespace std;

struct Star {
    int value;
    int idx;
};

struct Edge{
    int distance;
    int v1;
    int v2;
};

struct compareStar{
    bool operator()(Star s1, Star s2){
        return s1.value < s2.value;
    }
};

struct compareEdge{
    bool operator()(Edge e1, Edge e2){
        return e1.distance > e2.distance;
    }
};

int n;
int result = 0;

priority_queue<Star, vector<Star>, compareStar> xPq;
priority_queue<Star, vector<Star>, compareStar> yPq;
priority_queue<Star, vector<Star>, compareStar> zPq;

priority_queue<Edge, vector<Edge>, compareEdge> pq;

int check[100100]={0};


void input() {
    cin >> n;
    for (int i = 0; i < n; i++) {
        int x, y, z;
        cin >> x >> y >> z;
        xPq.push({x, i});
        yPq.push({y, i});
        zPq.push({z, i});
    }
}

void output() {
    cout << result << '\n';
}

void addDistance(priority_queue<Star, vector<Star>, compareStar> starPq){
    Star prevStar=starPq.top();
    starPq.pop();
    while(!starPq.empty()){
        Star top = starPq.top();
        starPq.pop();

        pq.push({abs(prevStar.value-top.value), min(prevStar.idx, top.idx), max(prevStar.idx, top.idx)});
        prevStar=top;
    }
}

int getRoot(int x){
    if(check[x]==x) return x;
    return getRoot(check[x]);
}

void unionRoot(int a,int b){
    int rootA = getRoot(a);
    int rootB = getRoot(b);
    if(rootA < rootB) check[rootA] = rootB;
    else check[rootB] = rootA;
}

bool findRoot(int a, int b){
    return getRoot(a) == getRoot(b);
}

void cal() {
    addDistance(xPq);
    addDistance(yPq);
    addDistance(zPq);

    for(int i=0;i<n;i++){
        check[i]=i;
    }

    int count=0;
    while(count!=n-1){
        Edge top = pq.top();
        pq.pop();


        if(!findRoot(top.v1, top.v2)){
            unionRoot(top.v1, top.v2);
            result+=top.distance;
            count++;
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