class Solution {
    
    private int[] dirY = {-1,0,1,0};
    private int[] dirX = {0,-1,0,1};
    
    public int solution(String[][] board, int h, int w) {
        
        int answer=0;
        
        for(int i=0;i<4;i++){
            int currentY = h+dirY[i];
            int currentX = w+dirX[i];
            
            if(currentY < 0 || currentY >= board.length) continue;
            if(currentX < 0 || currentX >= board[0].length) continue;
            
            if(board[currentY][currentX].equals(board[h][w]))
                answer++;
        }
        
        return answer;
    }
}