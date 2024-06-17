import java.util.*;
import java.util.stream.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int maxNumber = 36;

        // a   b  c  d  e  f  g  h  i  j  k  l  m  n  o  p  q  r  s  t  u  v  w  x  y  z
        // 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31 32 33 34 35

        String ab[] = br.readLine().split(" ");
        int a[] = convertInteger(ab[0]);
        int b[] = convertInteger(ab[1]);

        int maxValueA = maxValue(a);
        int maxValueB = maxValue(b);

        long x = -1;
        int aNumber = 0;
        int bNumber = 0;

        int count = 0;

        for (int i = maxValueA+1; i <= maxNumber; i++) {
            long calAX = calX(a, i);
            if(calAX < 0) continue;
            for (int j = maxValueB+1; j <= maxNumber; j++) {
                if(i==j) continue;
                long calBX = calX(b, j);
                if(calBX < 0) continue;

                if (calAX == calBX) {
                    count++;
                    x = calAX;
                    aNumber = i;
                    bNumber = j;
                }
            }
        }

        if (count > 1) {
            System.out.println("Multiple");
        } else if (x < 0 || aNumber == bNumber) {
            System.out.println("Impossible");
        } else {
            System.out.println(x + " " + aNumber + " " + bNumber);
        }
    }

    static int[] convertInteger(String number) {
        return number.chars()
                .map(num -> {
                    if (num >= 'a') {
                        return 10 + (num - 'a');
                    } else {
                        return num - '0';
                    }
                })
                .toArray();
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
        for (int i = 0; i < arr.length; i++) {
            result += pow(baseNumber, i) * arr[arr.length - i - 1];
            if (result < 0)
                return -1;
        }
        return result;
    }

    static long pow(int number, int pow) {
        long result = 1;
        for (int i = 0; i < pow; i++) {
            result *= number;
        }
        return result;
    }

}
