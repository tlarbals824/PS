import java.util.*;
import java.io.*;

class Main {

    private static int[] pCheck;
    private static char[] p;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        char[] t = br.readLine().toCharArray();
        p = br.readLine().toCharArray();

        /**
         * pCheck[i]는 이전에 중복됐던 단어의 위치를 나타냄
         */
        pCheck = new int[p.length];
        Arrays.fill(pCheck, -1);
        for (int i = 1; i < p.length; i++) {
            pCheck[i] = findSameCharacterIndex(p[i], i);
        }
        // System.out.println(Arrays.toString(pCheck));

        int result = 0;
        int count = 0;
        List<Integer> resultIndexes = new ArrayList<>();
        for (int i = 0; i < t.length; i++) {
            if (t[i] == p[count]) {
                count++;
                if (count == p.length) {
                    result++;
                    resultIndexes.add(i + 2 - p.length);
                    int sameIdx = findSameCharacterIndex(t[i], p.length - 1);
                    count = sameIdx == -1 ? 0 : sameIdx + 1;
                }
            } else {
                if (count == 0)
                    continue;
                int sameIdx = findSameCharacterIndex(t[i], count);
                count = sameIdx == -1 ? 0 : sameIdx + 1;
            }
        }
        System.out.println(result);
        for (int num : resultIndexes) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    private static int findSameCharacterIndex(char target, int currentIdx) {
        int prev = currentIdx - 1;
        while (true) {
            if (prev == -1)
                break;
            if (target == p[pCheck[prev] + 1]) {
                prev = pCheck[prev] + 1;
                break;
            } else {
                prev = pCheck[prev];
            }
        }
        return prev; // -1 not found, 0 < prev exist index
    }

}
