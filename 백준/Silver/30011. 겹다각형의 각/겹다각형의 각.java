import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int arr[] = new int[n];
        for(int i=0;i<n;i++){
            arr[i]=scanner.nextInt();
        }

        // 360 + 180*3
        // n 내 합 + (n+1)의 변의 수*180

        int result = (arr[0]-2)*180;

        for(int i=1;i<n;i++){
            result+=(arr[i]*180);
        }
        System.out.println(result);
    }
    
}
