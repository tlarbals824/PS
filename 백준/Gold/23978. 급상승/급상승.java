import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        long[] nk = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();
        long k = nk[1];
        long[] a = Arrays.stream(br.readLine().split(" ")).mapToLong(Long::parseLong).toArray();

        List<Long> disList = new ArrayList<>();
        for (int i = 1; i < a.length; i++) {
            disList.add(a[i] - a[i - 1]);
        }

        /**
         * move 2배씩 늘리다 k 넘어서는 순간
         * 그 이전값에서 다시 move =1로 시작
         */

        long prev = 0;
        long move = 1;
        long result = 0;
        while (true) {
            if (calTotal(disList, result + move) >= k) {
                if (move == 1) {
                    result++;
                    break;
                }
                result += prev;
                move = 1;
            } else {
                prev = move;
                move *= 2;
            }
        }

        System.out.println(result);
    }

    private static long calTotal(List<Long> dis, long x) {
        long result = 0;
        for (int i = 0; i < dis.size(); i++) {
            result += cal(dis.get(i), x);
        }
        return result + cal(x, x);
    }

    private static long cal(long dis, long x) {
        return ((x * (x + 1)) / 2) - (x > dis ? (((x - dis) * (x - dis + 1)) / 2) : 0);
    }

}
