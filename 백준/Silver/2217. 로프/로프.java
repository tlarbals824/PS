import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[] input = new int[n];
        for(int i=0;i<n;i++){
            input[i]=Integer.parseInt(br.readLine());
        }

        Arrays.sort(input);

        int count=0;
        int minValue=100000;

        int result=0;

        for(int i=n-1;i>=0;i--){
            if(input[i]<minValue){
                minValue = input[i];
            }
            count++;
            result=Math.max(result, count*minValue);
        }
        System.out.println(result);
    }

}
