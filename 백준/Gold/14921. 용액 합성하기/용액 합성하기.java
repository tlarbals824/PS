import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args)throws Exception{
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] solution = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int start = 0;
        int end = n-1;
        int result = Integer.MAX_VALUE;
        while(start<end){
            int sum = solution[start]+solution[end];
            if(Math.abs(result) > Math.abs(sum)){
                result = sum;
            }
            if(sum > 0){
                end--;
            }else{
                start++;
            }
        }

        System.out.println(result);
    }
    
}
