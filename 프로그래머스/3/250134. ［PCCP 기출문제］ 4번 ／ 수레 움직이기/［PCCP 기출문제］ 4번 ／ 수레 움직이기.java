import java.util.*;

class Solution {
    int maxY,maxX;
    
    int[] dirY = {-1,0,1,0};
    int[] dirX = {0,-1,0,1};
    
    int[][] redCheck,blueCheck;
    
    int[][] map;
    
    int result=0;
    
    public int solution(int[][] maze) {
        map=maze;
        maxY = maze.length;
        maxX = maze[0].length;
        redCheck=new int[maxY][maxX];
        blueCheck=new int[maxY][maxX];
        
        int redY=0,redX=0,blueY=0,blueX=0;
        
        for(int i=0;i<maxY;i++){
            for(int j=0;j<maxX;j++){
                if(maze[i][j]==1){
                    redY=i;
                    redX=j;
                }
                if(maze[i][j]==2){
                    blueY=i;
                    blueX=j;
                }
            }
        }
        redCheck[redY][redX]=1;
        blueCheck[blueY][blueX]=1;
        recursive(redY,redX,blueY,blueX,0);
        
        return result;
    }
    
    void recursive(int redY, int redX, int blueY, int blueX, int count){
        if(map[redY][redX]==3&&map[blueY][blueX]==4){
            if(result==0){
                result=count;
            }else{
                result=Math.min(result,count);
            }
        }else{
            for(int i=0;i<4;i++){
                int nextRedY = redY + dirY[i];
                int nextRedX = redX + dirX[i];
                
                if(map[redY][redX]==3){
                    nextRedY=redY;
                    nextRedX=redX;
                }else{
                    if(nextRedY < 0||nextRedY>=maxY) continue;
                    if(nextRedX < 0||nextRedX>=maxX) continue;
                    if(map[nextRedY][nextRedX]==5) continue;
                    if(redCheck[nextRedY][nextRedX]!=0) continue; 
                }
                
                redCheck[nextRedY][nextRedX]=1;
                for(int j=0;j<4;j++){
                    int nextBlueY = blueY + dirY[j];
                    int nextBlueX = blueX + dirX[j];
                    
                    if(map[blueY][blueX]==4){
                        if(blueY==nextRedY && blueX==nextRedX) continue;
                        recursive(nextRedY,nextRedX,blueY,blueX, count+1);
                    }else{
                        if(nextBlueY < 0||nextBlueY>=maxY) continue;
                        if(nextBlueX < 0||nextBlueX>=maxX) continue;
                        if(map[nextBlueY][nextBlueX]==5) continue;
                        if(blueCheck[nextBlueY][nextBlueX]!=0) continue;
                    
                        if(nextBlueY==nextRedY && nextBlueX==nextRedX) continue;
                        if(nextBlueY==redY&&nextBlueX==redX&&nextRedY==blueY&&nextRedX==blueX) continue;
                        
                        blueCheck[nextBlueY][nextBlueX]=1;
                        recursive(nextRedY,nextRedX,nextBlueY,nextBlueX, count+1);
                        blueCheck[nextBlueY][nextBlueX]=0;  
                    }
                }
                redCheck[nextRedY][nextRedX]=0;
            }
        }
    }

}