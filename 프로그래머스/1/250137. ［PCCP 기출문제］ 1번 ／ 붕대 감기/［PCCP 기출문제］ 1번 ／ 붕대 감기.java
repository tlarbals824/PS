

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int currentHealth = health;
        int currentTime = 0;
        int currentAddHealth = 0;
        int currentCount=0;
        final int maxTime = attacks[attacks.length-1][0];
        int nextAttackIdx = 0;
        while(currentTime<=maxTime){
            if(currentTime == attacks[nextAttackIdx][0]){
                currentHealth = (currentHealth+currentAddHealth > health ? health : currentHealth+currentAddHealth) -attacks[nextAttackIdx][1];
                nextAttackIdx++;
                currentCount=0;
                currentAddHealth=0;
                System.out.println(currentHealth);
                if(currentHealth <= 0) return -1;
            }else{
                currentAddHealth+=bandage[1];
                currentCount++;
                if(currentCount==bandage[0]){
                    currentCount=0;
                    currentAddHealth+=bandage[2];
                }
            }
            currentTime++;
        }
        
        return currentHealth;
    }
    
    
    
}