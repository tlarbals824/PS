import java.util.*;
import java.io.*;

class Main {

    private static int[][] dp = new int[1000][1000];

    public static void main(String[] args) throws Exception{
        var br = new BufferedReader(new InputStreamReader(System.in));

        char[] input = br.readLine().toCharArray();

        for(int i=0;i<input.length;i++){ // 길이 0
            dp[i][i]=1;
        }
        
        for(int i=1;i<input.length;i++){ // 길이 1 ~ input 길이 만큼
            for(int j=0;j<input.length-i;j++){
                dp[j][j+i]=dp[j][j+i-1]+dp[j+1][j+i]-dp[j+1][j+i-1];
                if(input[j]==input[j+i]){
                    dp[j][j+i]+=dp[j+1][j+i-1]+1;
                }
            }
        }
        System.out.println(dp[0][input.length-1]);
    }
    // aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
}
