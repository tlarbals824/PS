import java.util.*;
import java.io.*;

class Main {

    private static char[] ad;
    private static int[] check;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int l = Integer.parseInt(br.readLine());
        ad = br.readLine().toCharArray();
        check = new int[l];
        Arrays.fill(check, -1);

        for (int i = 1; i < l; i++) {
            check[i] = findSameCharacter(ad[i], i);
        }

        // System.out.println(Arrays.toString(check));

        int count = 0;
        for (int i = 1; i < l; i++) {
            if (check[i] == 1) {
                count = i - 1;
            }
            if (check[i] == 0) {
                count = i;
            }
            if (check[i] == -1) {
                count = i + 1;
            }
        }

        System.out.println(count == 0 ? 1 : count);
    }

    private static int findSameCharacter(char target, int index) {
        int prev = index - 1;
        while (prev != -1) {
            if (target == ad[check[prev] + 1]) {
                prev = check[prev] + 1;
                break;
            } else {
                prev = check[prev];
            }
        }
        return prev;
    }

}
