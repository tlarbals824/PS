import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception{
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int[] nm = Arrays.stream(br.readLine().split(" "))
            .mapToInt(str -> Integer.parseInt(str))
            .toArray();

        Set<String> strHash = new HashSet<>();
        for(int i=0;i<nm[0];i++){
            strHash.add(br.readLine());
        }

        for(int i=0;i<nm[1];i++){
            Arrays.stream(br.readLine().split(","))
                .forEach(str -> {
                    if(strHash.contains(str)){
                        strHash.remove(str);
                    }
                });
                bw.write(strHash.size()+"\n");
        }
        bw.flush();
    }

    static int bs(int[] arr, int a){
        int left = 0;
        int right = arr.length-1;

        while(left<right){
            int mid = (left+right) /2;

            if(arr[mid]==a) return mid;

            if(arr[mid] < a){
                left=mid+1;
            }else{
                right = mid;
            }
        }

        return -1;
    }
    
}
