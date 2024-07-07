import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] input = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        Arrays.sort(input);

        // 1 1 2 3 6 7 30
        // 1 2 3 6 7 30
        // 2 4 7 13 20
        int maxValue = 0;
        for(int i=0;i<n;i++){
            int target = input[i];

            if(target > maxValue+1){
                break;
            }else{
                maxValue+=target;
            }
        }
        System.out.println(maxValue+1);
    }

}
