import java.util.*;

class Solution {
    Map<Integer, Integer> oilSize = new HashMap<>();
    int[][] check;
    
    int count=0;
    int maxY;
    int maxX;
    
    int[] dirY = {-1,0,1,0};
    int[] dirX = {0,-1,0,1};
    
    public int solution(int[][] land) {
        maxY = land.length;
        maxX = land[0].length;
        check=new int[maxY][maxX];
        
        for(int i=0;i<maxY;i++){
            for(int j=0;j<maxX;j++){
                if(check[i][j]!=0) continue;
                if(land[i][j]==0) continue;
                checkOil(i,j,++count, land);
            }
        }
        
        int answer = 0;
        
        for(int i=0;i<maxX;i++){
            Set<Integer> countSet = new HashSet<>();
            for(int j=0;j<maxY;j++){
                if(check[j][i]!=0) {
                    countSet.add(check[j][i]);
                }
            }
            
            int tmpSum=countSet.stream()
                .reduce(0, (a,b) -> a+oilSize.get(b));
            
            
            answer = Math.max(answer, tmpSum);
        }
        
        
        return answer;
    }
    
    void checkOil(int y,int x, int count, int[][] land){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(y,x));
        
        int size=0;
        
        while(!q.isEmpty()){
            Node node = q.peek();
            q.remove();
            int currentY = node.y;
            int currentX = node.x;
            
            if(check[currentY][currentX]!=0) continue;
            
            size++;
            
            check[currentY][currentX]=count;
            
            for(int i=0;i<4;i++){
                int nextY = currentY + dirY[i];
                int nextX = currentX + dirX[i];
                
                if(nextY < 0 || nextY >= maxY) continue;
                if(nextX < 0 || nextX >= maxX) continue;
                if(check[nextY][nextX]!=0) continue;
                if(land[nextY][nextX]==0) continue;
                
                q.add(new Node(nextY, nextX));
            }
            
        }
        
        
        oilSize.put(count, size);
    }
    
    class Node{
        int y;
        int x;
        
        Node(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}