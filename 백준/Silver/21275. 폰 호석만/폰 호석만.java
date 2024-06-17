import java.util.*;
import java.util.stream.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        // a   b  c  d  e  f  g  h  i  j  k  l  m  n  o  p  q  r  s  t  u  v  w  x  y  z
        // 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35
        int maxNumber = 35;

        String ab[] = br.readLine().split(" ");
        int a[] = convertInteger(ab[0]);
        int b[] = convertInteger(ab[1]);

        int maxValueA = maxValue(a);
        int maxValueB = maxValue(b);

        long x = -1;
        int aNumber = 0;
        int bNumber = 0;

        for (int i = maxValueA; i <= maxNumber; i++) {
            long calAX = calX(a, i);
            for (int j = maxValueB; j <= maxNumber; j++) {
                long calBX = calX(b, j);

                if (calAX == calBX) {
                    if (x != -1) {
                        System.out.println("Multiple");
                        return;
                    } else {
                        if(i==j) continue;
                        x = calAX;
                        aNumber = i;
                        bNumber = j;
                    }
                }
            }
        }

        if(x==-1 || aNumber==bNumber){
            System.out.println("Impossible");
        }else{
            System.out.println(x+" "+aNumber+" "+bNumber);
        }

        //
    }

    static int[] convertInteger(String number) {
        return number.chars()
                .map(num -> {
                    if (num >= 'a') {
                        return 10 + (num - 'a');
                    } else {
                        return num - '0';
                    }
                }).toArray();
    }

    static int maxValue(int arr[]) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result = Math.max(result, arr[i]);
        }
        return result;
    }

    static long calX(int arr[], int baseNumber) {
        long result = 0;
        for(int i=0;i<arr.length;i++){
            result+=((long)Math.pow(baseNumber, i)*arr[arr.length-i-1]);
        }
        return result;
    }

}
