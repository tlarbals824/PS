import java.util.*;
import java.io.*;

class Main {

    static String[] input;
    static int[] count = new int[26];

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        input = new String[n];
        for(int i=0;i<n;i++){
            input[i]=br.readLine();
            int pow = 1;
            for(int j=input[i].length()-1;j>=0;j--){
                count[input[i].charAt(j)-'A']+=pow;
                pow*=10;
            }
        }

        Arrays.sort(count);
        int result = 0;
        for(int i=0;i<10;i++){
            if(count[26-i-1]==0) break;
            result+=count[26-i-1]*(9-i);
        }
        System.out.println(result);
    }


}
