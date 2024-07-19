import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        char[] input = br.readLine().toCharArray();

        int size = input.length;
        int[][] check = new int[size][size];

        for (int i = 0; i < size; i++) {
            check[i][i] = 1;
        }
        for (int i = 0; i < size - 1; i++) {
            if (input[i] == input[i + 1]) {
                check[i][i + 1] = 1;
            }
        }
        for (int i = 2; i < size; i++) {
            for (int j = 0; j < size - i; j++) {
                if (input[j] == input[j + i] && check[j + 1][j + i - 1] == 1) {
                    check[j][j + i] = 1;
                }
            }
        }

        int[] dp = new int[size];
        for(int i=0;i<size;i++){
            dp[i]=i+1;
        }
        for (int i = 1; i < size; i++) {
            for (int j = 0; j <= i; j++) {
                if (check[j][i] == 1) {
                    if(j==0){
                        dp[i]=1;
                    }else{
                        dp[i] = Math.min(dp[i], dp[j-1] + 1);
                    }
                }
            }
        }
        System.out.println(dp[size - 1]);
    }

}
