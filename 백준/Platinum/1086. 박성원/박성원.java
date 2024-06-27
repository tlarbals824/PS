import java.util.*;
import java.io.*;

class Main {

    static int[] tenModular;
    static int[] modul;
    static String[] num;
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        num = new String[n + 1];

        for (int i = 0; i < n; i++) {
            var input = br.readLine();
            num[i] = input;
        }

        int k = Integer.parseInt(br.readLine());

        tenModular = new int[1000];
        tenModular[0] = 1;
        for (int i = 1; i < 1000; i++) {
            tenModular[i] = (tenModular[i - 1] * 10) % k;
        }

        modul = new int[n];

        for (int i = 0; i < n; i++) {
            var numArray = num[i].toCharArray();
            int tmp = 0;
            for (int j = numArray.length - 1; j >= 0; j--) {
                tmp = (tmp + (tenModular[numArray.length - 1 - j] * (numArray[j] - '0')) % k) % k;
            }
            modul[i] = tmp;
        }

        long[] factorial = new long[n + 1];
        Arrays.fill(factorial, 0);
        long total = fact(factorial, n);

        dp = new long[1 << n][k];
        for(int i=0;i<n;i++){
            dp[mark(0, i)][modul[i]]=1;
        }
        
        for(int i=2;i<=n;i++){ // n
            for(int bit=1;bit<(1<<n);bit++){ // 2^n
                int count = count(bit, n);
                if(count!=i-1) continue;
                for(int j=0;j<n;j++){ // n
                    if(isMark(bit, j)) continue;
                    int marked = mark(bit, j);
                    int length = length(bit, n);
                    for(int x=0;x<k;x++){ // k
                        if(dp[bit][x]==0) continue;
                        int last = (x+modul[j]*tenModular[length]%k)%k;
                        dp[marked][last]+=dp[bit][x];
                    }
                }
            }
        }


        long result = dp[(1<<n)-1][0];

        if (result == 0) {
            System.out.println("0/1");
        } else if (result == total) {
            System.out.println("1/1");
        } else {
            long gcd = gcd(total, result);
            System.out.println(result / gcd + "/" + total / gcd);
        }

    }

    static long gcd(long num1, long num2) {
        if (num1 % num2 == 0) {
            return num2;
        }
        return gcd(num2, num1 % num2);
    }

    static void cal(int bit, int count, int last, int k) {
        if (count == modul.length / 2 + 1) {
            return;
        } else {
            int length = length(bit, modul.length);
            for (int i = 0; i < modul.length; i++) {
                if (isMark(bit, i))
                    continue;
                int marked = mark(bit, i);
                int nextLast = (last + modul[i] * tenModular[length] % k) % k;
                dp[marked][nextLast]++;
                cal(marked, count + 1, nextLast, k);
            }
        }
    }

    static int mark(int target, int index) {
        return target | 1 << index;
    }

    static int unmark(int target, int index) {
        return target ^ (1 << index);
    }

    static boolean isMark(int target, int index) {
        int tmp = target & 1 << index;
        return tmp > 0;
    }

    static int reverse(int bit, int size) {
        int allCheck = (1 << size) - 1;
        return (allCheck ^ bit) & allCheck;
    }

    static long fact(long[] factorial, int num) {
        if (factorial[num] != 0)
            return factorial[num];
        if (num == 0 || num == 1) {
            return factorial[num] = 1;
        }
        if (num == 2) {
            return factorial[num] = 2;
        }
        return factorial[num] = num * fact(factorial, num - 1);
    }

    static int length(int bit, int n) {
        int tmp = 0;
        for (int i = 0; i < n; i++) {
            if ((bit & 1 << i) > 0) {
                tmp += num[i].length();
            }
        }
        return tmp;
    }

    static int count(int bit, int n){
        int tmp = 0;
        for(int i=0;i<n;i++){
            if(isMark(bit, i))
                tmp++;
        }
        return tmp;
    }

}
