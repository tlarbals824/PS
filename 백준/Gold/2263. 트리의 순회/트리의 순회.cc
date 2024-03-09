#include<iostream>
#include<vector>


using namespace std;

int n;
int inOrder[100100]={0}; // left, root, right
int postOrder[100100]={0}; // left , right, root, 프리오더 마지막은 무조건 root idx;
int idx[100100]={0};

void input(){
    cin>>n;
    for(int i=0;i<n;i++){
        int num;
        cin>>num;
        inOrder[i]=num;
        idx[num]=i;
    }

    for(int i=0;i<n;i++){
        cin>>postOrder[i];
    }
}

void divide(int inStart, int inEnd, int pStart, int pEnd){
    if(inStart > inEnd || pStart > pEnd) return;
    int root = postOrder[pEnd];
    cout<<root<<' ';
    int inOrderRootIdx = idx[root];
    divide(inStart, inOrderRootIdx-1, pStart, pEnd+inOrderRootIdx-inEnd-1);
    divide(inOrderRootIdx+1, inEnd,pStart+inOrderRootIdx-inStart,pEnd-1);
}

void cal(){
    divide(0, n-1,0,n-1);
    cout<<'\n';
}

void start(){
    input();
    cal();
}

int main(){
    ios_base::sync_with_stdio(0);
    cin.tie(0);

    start();
}