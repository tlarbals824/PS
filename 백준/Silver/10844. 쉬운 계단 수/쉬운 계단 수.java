import java.util.*;
import java.io.*;

class Main {

    static int DIV = 1_000_000_000;

    public static void main(String[] main) throws Exception{
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int[n+1][10];

        for(int i=1;i<10;i++){
            dp[1][i]=1;
        }

        // 1~9 0
        // 1~10 1
        // 
        for(int i=2;i<=n;i++){
            for(int j=0;j<10;j++){
                dp[i][j]=((j-1 >= 0 ? dp[i-1][j-1] : 0) + (j+1 <= 9 ? dp[i-1][j+1] : 0))%DIV;
            }
        }

        int result = 0;
        for(int i=0;i<10;i++){
            result+=dp[n][i];
            result=result%DIV;
        }
        System.out.println(result);

        // long tmp = 0;
        // for(int i=1;i<=n;i++){
        //     System.out.println(Arrays.toString(dp[i]) + Arrays.stream(dp[i]).sum());
        //     tmp+=Arrays.stream(dp[i]).sum();
        // }
        // System.out.println(tmp);
    }
    
}
