import java.util.*;
import java.io.*;

class Main {

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String input;
        Queue<Character> q = new LinkedList<>();
        while (true) {
            input = br.readLine();
            if (input.equals("#")) {
                break;
            }

            boolean hasVowel = false;
            for (int i = 0; i < input.length(); i++) {
                if (isVowel(input.charAt(i))) {
                    hasVowel = true;
                }
                q.add(input.charAt(i));
            }

            if (hasVowel) {
                while (!isVowel(q.peek())) {
                    var top = q.poll();
                    q.add(top);
                }
            }
            var sb = new StringBuilder();
            while (!q.isEmpty()) {
                sb.append(q.poll());
            }
            sb.append("ay");
            bw.write(sb.toString() + "\n");
        }
        bw.flush();
    }

    private static boolean isVowel(char target) {
        return (target == 'a') || (target == 'e') || (target == 'i') || (target == 'o') || (target == 'u');
    }

}
