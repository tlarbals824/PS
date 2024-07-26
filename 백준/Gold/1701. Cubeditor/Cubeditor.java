import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine();

        int result = 0;
        int[] check;
        for (int i = 0; i < input.length(); i++) {
            String tmp = input.substring(i);
            check = new int[tmp.length()];
            Arrays.fill(check, -1);
            for (int j = 1; j < tmp.length(); j++) {
                check[j] = findSameCharacter(j, tmp, check);
                result = Math.max(result, check[j] + 1);
            }
            // System.out.println(Arrays.toString(check));
        }
        System.out.println(result);
    }

    private static int findSameCharacter(int index, String input, int[] check) {
        int prev = index - 1;
        while (prev > -1) {
            if (input.charAt(index) == input.charAt(check[prev] + 1)) {
                prev = check[prev] + 1;
                break;
            } else {
                prev = check[prev];
            }
        }
        return prev;
    }

}