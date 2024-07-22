import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        char[] input = br.readLine().toCharArray();
        char[] target = br.readLine().toCharArray();

        if (input[0] == target[0] && input[n - 1] == target[n - 1]) {
            var sbInput = new StringBuilder();
            var sbTarget = new StringBuilder();

            Map<Character, Integer> charMap = new HashMap<>();
            for (int i = 0; i < n; i++) {
                charMap.put(input[i], charMap.getOrDefault(input[i], 0) + 1);
                if (!check(input[i]))
                    sbInput.append(input[i]);
            }

            for (int i = 0; i < n; i++) {
                charMap.put(target[i], charMap.getOrDefault(target[i], 0) - 1);
                if (!check(target[i]))
                    sbTarget.append(target[i]);
            }

            if (!sbInput.toString().equals(sbTarget.toString())) {
                System.out.println("NO");
                return;
            }

            for (char key : charMap.keySet()) {
                if (charMap.get(key) != 0) {
                    System.out.println("NO");
                    return;
                }
            }
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    private static boolean check(char target) {
        return (target == 'a') || (target == 'e') || (target == 'i') || (target == 'o') || (target == 'u');
    }

}
