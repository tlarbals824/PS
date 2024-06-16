import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int dp[] = new int[50001];

        Arrays.fill(dp, 4);

        dp[n]=0;

        // dp[n] = dp[a] + dp[b] + dp[c] + dp[d];
        // dp[n] = min(dp[n-a^2]+1, dp[n]);
        
        Queue<Integer> pq = new LinkedList<>();
        pq.add(n);
        while(!pq.isEmpty()){
            int number = pq.poll();
            int count = dp[number];
            if(count==4) continue;

            int sqrtNumber = (int)Math.sqrt(number);
            for(int i=1;i<=sqrtNumber;i++){
                int nextNumber = number-(int)Math.pow(i,2);
                if(dp[nextNumber] > count+1){
                    pq.add(nextNumber);
                    dp[nextNumber]=count+1;
                }
            }
        }

        System.out.println(dp[0]);
    }

}
