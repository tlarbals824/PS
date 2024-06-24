import java.util.*;
import java.io.*;

class Main{

    public static void main(String[] args) throws Exception{
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[4][n];

        for(int i=0;i<n;i++){
            int[] tmp =Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
            for(int j=0;j<4;j++){
                arr[j][i]=tmp[j];
            }
        }

        int[][] sortArr = new int[2][n*n];

        for(int i =0;i<n;i++){
            for(int j=0;j<n;j++){
                sortArr[0][i*n+j]=arr[0][i]+arr[1][j];
                sortArr[1][i*n+j]=arr[2][i]+arr[3][j];
            }
        }

        Arrays.sort(sortArr[0]);
        Arrays.sort(sortArr[1]);

        long result=0;

        for(int i=0;i<n*n;i++){
            int lower = lower_bound(sortArr[0], sortArr[1][i]);
            int upper = upper_bound(sortArr[0],sortArr[1][i]);
            if(lower==upper){
                continue;
            }else{
                if(sortArr[0][lower]==sortArr[0][upper]) result+=1;
                result+=(upper-lower);
            }
            
        }

        System.out.println(result);

    }

    static int lower_bound(int[] arr, int target){
        int left = 0;
        int right=arr.length-1;

        int mid = (left+right)/2;

        while(left < right){
            mid = (left+right)/2;

            if(arr[mid] < -target){
                left = mid+1;
            }else{
                right = mid;
            }
        }
        return right;
    }

    static int upper_bound(int[] arr, int target){
        int left = 0;
        int right=arr.length-1;

        int mid = (left+right)/2;

        while(left < right){
            mid = (left+right)/2;

            if(arr[mid] > -target){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return right;
    }
}