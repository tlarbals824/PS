import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        Map<String, Integer> playerMap = new HashMap<String,Integer>();
        for(int i=0;i<players.length;i++){
            playerMap.put(players[i],i);
        }
        for(int i=0;i<callings.length;i++){
            int idx = playerMap.get(callings[i]);
            swap(idx-1, idx, players);
            playerMap.put(players[idx], idx);
            playerMap.put(players[idx-1], idx-1);
        }
        
        return players;
    }
    
    void swap(int idx1, int idx2, String[] players){
        String tmp = players[idx1];
        players[idx1]=players[idx2];
        players[idx2]=tmp;
    }
}